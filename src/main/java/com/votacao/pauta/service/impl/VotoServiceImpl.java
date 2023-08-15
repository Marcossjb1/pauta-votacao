package com.votacao.pauta.service.impl;

import com.votacao.pauta.model.Pauta;
import com.votacao.pauta.dto.ResultadoVotacao;
import com.votacao.pauta.model.Voto;
import com.votacao.pauta.repository.PautaRepository;
import com.votacao.pauta.repository.UsuarioRepository;
import com.votacao.pauta.repository.VotoRepository;
import com.votacao.pauta.service.VotoService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VotoServiceImpl implements VotoService {
    @Autowired
    private VotoRepository votoRepository;
    @Autowired
    private PautaRepository pautaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Voto inserirVoto(Voto voto) {
        if (validarDadosParaVotar(voto)) {
            Voto votos = votoRepository.findByIdUsuarioAndIdPauta(voto.getIdUsuario(), voto.getIdPauta());
            if (votos == null) {
                Pauta pauta = getPautaById(voto.getIdPauta());
                verificarPrazoParaVotar(pauta);
                return votoRepository.save(voto);
            }
            throw new RuntimeException("Você não pode votar novamente.");
        }
        throw new RuntimeException("Dados incorretos, para votar é preciso que o Usuário e Pauta estejam corretos.");
    }

    @Override
    public Voto buscarVoto(Long id) {
        Optional<Voto> voto = votoRepository.findById(id);
        if (voto.isPresent()) {
            return voto.get();
        }
        throw new ObjectNotFoundException(id, Voto.class.getSimpleName());
    }

    @Override
    public ResultadoVotacao resultadoVotacao(Long idPauta) {
        LocalDateTime data = LocalDateTime.now();
        Optional<Pauta> pauta = pautaRepository.findById(idPauta);
        List<Voto> votos = votoRepository.findByIdPauta(idPauta);
        verificarVotacaoEmAndamento(data, pauta);
        int sim = contarVotosSim(votos);
        int nao = contarVotosNao(votos);
        String resultado = calcularResultado(sim, nao);
        return new ResultadoVotacao(idPauta, sim, nao, resultado);
    }

    private String calcularResultado (int sim, int nao) {
        if (sim > nao) {
            return "Pauta aprovada!";
        } else {
            return "Pauta reprovada!";
        }
    }

    private boolean validarDadosParaVotar(Voto voto) {
        return pautaRepository.existsById(voto.getIdPauta()) && usuarioRepository.existsById(voto.getIdUsuario());
    }

    private Pauta getPautaById(Long idPauta) {
        Optional<Pauta> pauta = pautaRepository.findById(idPauta);
        if (pauta.isPresent()) {
            return pauta.get();
        } else {
            throw new ObjectNotFoundException(idPauta, Pauta.class.getSimpleName());
        }
    }

    private void verificarPrazoParaVotar(Pauta pauta) {
        LocalDateTime date = LocalDateTime.now();
        if (date.isBefore(pauta.getPrazo())) {
            throw new RuntimeException("A Pauta está fechada, você não pode mais votar!");
        }
    }

    private void verificarVotacaoEmAndamento(LocalDateTime data, Optional<Pauta> pauta) {

        if (data.isBefore(pauta.get().getPrazo())) {
            throw new RuntimeException("A votação está em andamento!");
        }
    }

    private int contarVotosSim(List<Voto> votos) {
        int sim = 0;
        for (Voto voto : votos) {
            if (voto.getVoto()) {
                sim++;
            }
        }
        return sim;
    }

    private int contarVotosNao(List<Voto> votos) {
        int nao = 0;
        for (Voto voto : votos) {
            if (!voto.getVoto()) {
                nao++;
            }
        }
        return nao;
    }
}