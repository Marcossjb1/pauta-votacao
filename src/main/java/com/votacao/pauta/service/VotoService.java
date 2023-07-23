package com.votacao.pauta.service;

import com.votacao.pauta.model.Pauta;
import com.votacao.pauta.model.Voto;
import com.votacao.pauta.repository.PautaRepository;
import com.votacao.pauta.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VotoService {

    @Autowired
    private VotoRepository votoRepository;
    @Autowired
    private PautaRepository pautaRepository;

    public Voto inserirVoto(Voto voto) {

        //Na linha abaixo estamos indo no banco de dados e vendo se o id da pauta existe
        //Se a pauta existir, salva o voto, se não mostramos uma mensagem de erro na tela
        if (pautaRepository.existsById(voto.getIdPauta())){
            return votoRepository.save(voto);
        }
        throw new RuntimeException("Pauta não encontrada na base de dados");
    }

    public Voto buscarVoto(Long id) {
        //Na linha abaixo estamos indo no banco de dados e buscando o voto por id
        Optional<Voto> voto = votoRepository.findById(id);
        if (voto.isPresent()) {
            return voto.get();
        }
        throw new RuntimeException("Não foi possível encontrar o voto na base de dados.");
    }
}

//TODO: USUÁRIO NÃO PODE INSERIR VOTO EM UMA PAUTA INEXISTENTE
//TODO: USUÁRIO NÃO PODE VOTAR MAIS DE UMA VEZ
//TODO: USUÁRIO NÃO PODE VOTAR SE A PAUTA ESTIVER FECHADA
//TODO: USUÁRIO NÃO PODE VOTAR SE ELE NÃO ESTIVER CRIADO NA BASE DE DADOS