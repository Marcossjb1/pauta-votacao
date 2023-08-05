package com.votacao.pauta.service;

import com.votacao.pauta.model.Pauta;
import com.votacao.pauta.dto.ResultadoVotacao;
import com.votacao.pauta.model.Voto;
import com.votacao.pauta.repository.PautaRepository;
import com.votacao.pauta.repository.UsuarioRepository;
import com.votacao.pauta.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VotoService {
    @Autowired
    private VotoRepository votoRepository;
    @Autowired
    private PautaRepository pautaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Voto inserirVoto(Voto voto) {
        LocalDateTime date = LocalDateTime.now();
        Optional<Pauta> pauta = pautaRepository.findById(voto.getIdPauta());
        if (pautaRepository.existsById(voto.getIdPauta()) && usuarioRepository.existsById(voto.getIdUsuario())) {
            Voto votos = votoRepository.findByIdUsuarioAndIdPauta(voto.getIdUsuario(), voto.getIdPauta());
            if (votos == null) {
                if (date.isBefore(pauta.get().getPrazo())) {
                    return votoRepository.save(voto);
                }
                throw new RuntimeException("A Pauta está fechada, você não pode mais votar!");
            }
            throw new RuntimeException("Você não pode votar novamente.");
        }
        throw new RuntimeException("Dados incorretos, para votar é preciso que o Usuário e Pauta estejam corretos.");
    }

    public Voto buscarVoto(Long id) {
        Optional<Voto> voto = votoRepository.findById(id);
        if (voto.isPresent()) {
            return voto.get();
        }
        throw new RuntimeException("Não foi possível encontrar o voto na base de dados.");
    }

    public ResultadoVotacao resultadoVotacao(Long idPauta) {
        LocalDateTime data = LocalDateTime.now();
        Optional<Pauta> pauta = pautaRepository.findById(idPauta);
        List<Voto> votos = votoRepository.findByIdPauta(idPauta);
        if (data.isBefore(pauta.get().getPrazo())) {
            throw new RuntimeException("A votação está em andamento!");
        }
        int sim = 0;
        int nao = 0;
        String resultado = "Resultado";

        for (int contador = 0; contador < votos.size(); contador++) {
            if (votos.get(contador).getVoto()){
                sim++;
            } else {
                nao++;
            }
        }
        if (sim>nao){
            resultado = "A pauta foi aprovada!";
        } else {
            resultado = "A pauta foi reprovada!";
        }
        return new ResultadoVotacao(idPauta,sim,nao,resultado);
    }
}