package com.votacao.pauta.service;

import com.votacao.pauta.model.Pauta;
import com.votacao.pauta.model.Voto;
import com.votacao.pauta.repository.PautaRepository;
import com.votacao.pauta.repository.VotoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VotoService {
    private final VotoRepository votoRepository;
    private final PautaRepository pautaRepository;

    public VotoService(VotoRepository votoRepository, PautaRepository pautaRepository) {
        this.votoRepository = votoRepository;
        this.pautaRepository = pautaRepository;
    }

    public Voto inserirVoto(Voto voto) {
        Optional<Pauta> pauta=pautaRepository.findById(voto.getIdPauta());
        if (pautaRepository.existsById(voto.getIdPauta())){
            return votoRepository.save(voto);
        }
        throw new RuntimeException("Pauta não encontrada na base de dados");
    }
    //TODO: USUÁRIO NÃO PODE INSERIR VOTO EM UMA PAUTA INEXISTENTE
    //TODO: USUÁRIO NÃO PODE VOTAR MAIS DE UMA VEZ
    //TODO: USUÁRIO NÃO PODE VOTAR SE A PAUTA ESTIVER FECHADA
    //TODO: USUÁRIO NÃO PODE VOTAR SE ELE NÃO ESTIVER CRIADO NA BASE DE DADOS

    public Voto buscarVoto(Long id) {
        Optional<Voto> voto = votoRepository.findById(id);
        if (voto.isPresent()) {
            return voto.get();
        }
        throw new RuntimeException("Não foi possível encontrar o voto na base de dados.");
    }
}
