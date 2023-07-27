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

    public Pauta inserirSessao(Pauta pauta) {
        Optional<Pauta> pautaDB = pautaRepository.findById(pauta.getId());
        if (pautaDB.isPresent()) {
            if (pauta.getPrazo() != null) {
                pautaDB.get().setPrazo(pauta.getPrazo());
                return pautaRepository.save(pauta);
            }
        }
        throw new RuntimeException("A pauta não existe na base de dados!");
    }

    public Pauta buscarPauta(Long id) {
        Optional<Pauta> pauta = pautaRepository.findById(id);
        if (pauta.isPresent()) {
            return pauta.get();
        }
        throw new RuntimeException("Pauta não encontrada na base de dados");
    }
}
