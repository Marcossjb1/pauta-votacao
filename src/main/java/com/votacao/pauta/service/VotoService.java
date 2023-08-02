package com.votacao.pauta.service;

import com.votacao.pauta.model.Pauta;
import com.votacao.pauta.model.ResultadoVotacao;
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
        Date data = new Date();
        LocalDateTime date = LocalDateTime.now();
        Optional<Pauta> pauta = pautaRepository.findById(voto.getIdPauta());
        //Na linha abaixo estamos indo no banco de dados e vendo se o id da pauta existe
        //Se a pauta existir, salva o voto, se não mostramos uma mensagem de erro na tela
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
        //Na linha abaixo estamos indo no banco de dados e buscando o voto por id
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
            resultado = "Pauta aprovada!";
        } else {
            resultado = " Pauta reprovada!";
        }
        return new ResultadoVotacao(idPauta,sim,nao,resultado);
    }
}

//TODO: USUÁRIO NÃO PODE INSERIR VOTO EM UMA PAUTA INEXISTENTE [X]
//TODO: USUÁRIO NÃO PODE VOTAR MAIS DE UMA VEZ [X]
//TODO: USUÁRIO NÃO PODE VOTAR SE A PAUTA ESTIVER FECHADA [X]
//TODO: USUÁRIO NÃO PODE VOTAR SE ELE NÃO ESTIVER CRIADO NA BASE DE DADOS [X]