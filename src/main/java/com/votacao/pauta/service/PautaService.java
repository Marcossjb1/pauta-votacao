package com.votacao.pauta.service;

import com.votacao.pauta.model.Pauta;
import com.votacao.pauta.repository.PautaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PautaService {
    private PautaRepository pautaRepository;

    public PautaService(PautaRepository pauta) {
        this.pautaRepository = pauta;
    }

    public Pauta inserirPauta(Pauta pauta) {
        return pautaRepository.save(pauta);
    }

    public Pauta buscarPauta(Long id){
        Optional<Pauta> resposta = pautaRepository.findById(id);
        if (resposta.isPresent()){
            return resposta.get();
        }
        throw new RuntimeException("Pauta não encontrada na base de dados");
    }


}
