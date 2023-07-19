package com.votacao.pauta.service;

import com.votacao.pauta.model.Voto;
import com.votacao.pauta.repository.VotoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VotoService {
    private final VotoRepository votoRepository;

    public VotoService(VotoRepository votoRepository) {
        this.votoRepository = votoRepository;
    }

    public Voto inserirVoto(Voto voto) {
        return votoRepository.save(voto);
    }

    public Voto buscarVoto(Long id) {
        Optional<Voto> voto = votoRepository.findById(id);
        if (voto.isPresent()) {
            return voto.get();
        }
        throw new RuntimeException("Não foi possível encontrar o voto na base de dados.");
    }
}
