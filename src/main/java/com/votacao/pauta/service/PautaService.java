package com.votacao.pauta.service;

import com.votacao.pauta.model.Pauta;
import com.votacao.pauta.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PautaService {

    @Autowired
    private PautaRepository pautaRepository;

    public Pauta inserirPauta(Pauta pauta) {
        return pautaRepository.save(pauta);
    }

    public Pauta buscarPauta(Long id){
        Optional<Pauta> pauta = pautaRepository.findById(id);
        if (pauta.isPresent()){
            return pauta.get();
        }
        throw new RuntimeException("Pauta não encontrada na base de dados");
    }


}
