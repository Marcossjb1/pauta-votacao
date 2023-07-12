package com.votacao.pauta.service;

import com.votacao.pauta.model.Pauta;
import com.votacao.pauta.repository.PautaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PautaService {
    private PautaRepository pautaRepository;

    public PautaService(PautaRepository pauta) {
        this.pautaRepository = pauta;
    }
@Transactional
    public Pauta inserirPauta(Pauta pauta){
        return pautaRepository.save(pauta);
    }
}
