package com.votacao.pauta.service.impl;

import com.votacao.pauta.model.Pauta;
import com.votacao.pauta.repository.PautaRepository;
import com.votacao.pauta.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PautaServiceImpl implements PautaService {

    @Autowired
    private PautaRepository pautaRepository;

    @Override
    public Pauta inserirPauta(Pauta pauta) {
        return pautaRepository.save(pauta);
    }

    @Override
    public Pauta inserirSessao(Pauta pauta) {
        Optional<Pauta> pautaDB = pautaRepository.findById(pauta.getId());
        if (pautaDB.isEmpty()) {
            throw new RuntimeException("A pauta não existe na base de dados!");
        }
        if (pautaDB.get().getPrazo() == null) {
            alterarPautaComPrazo(pautaDB.get(), pauta.getPrazo());
            return pautaRepository.save(pautaDB.get());
        } else {
            throw new RuntimeException("A pauta está fechada!");
        }
    }

    @Override
    public Pauta buscarPauta(Long id) {
        Optional<Pauta> pauta = pautaRepository.findById(id);
        if (pauta.isPresent()) {
            return pauta.get();
        }
        throw new RuntimeException("Pauta não encontrada na base de dados");
    }

    public void alterarPautaComPrazo(Pauta pauta, LocalDateTime prazo) {
        LocalDateTime date = LocalDateTime.now();
        LocalDateTime novoPrazo = (prazo != null) ? prazo : date.plusMinutes(1);
        pauta.setPrazo(novoPrazo);
    }
}