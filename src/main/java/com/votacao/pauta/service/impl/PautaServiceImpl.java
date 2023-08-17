package com.votacao.pauta.service.impl;

import com.votacao.pauta.exception.BadRequestException;
import com.votacao.pauta.model.Pauta;
import com.votacao.pauta.repository.PautaRepository;
import com.votacao.pauta.service.PautaService;

import java.time.Instant;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.servlet.function.ServerRequest.Headers;

@Service
public class PautaServiceImpl implements PautaService {

    @Autowired
    private PautaRepository pautaRepository;

    @Override
    public Pauta inserirPauta(Pauta pauta) {
        if (!pauta.getDescricao().isEmpty()) {
            return pautaRepository.save(pauta);
        }
        throw new BadRequestException("É necessário inserir uma descrição na pauta.");
    }

    @Override
    public Pauta inserirSessao(Pauta pauta) {
        Optional<Pauta> pautaDB = pautaRepository.findById(pauta.getId());
        if (pautaDB.isEmpty()) {
            throw new ObjectNotFoundException(pauta.getId(), Pauta.class.getSimpleName());
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
        throw new ObjectNotFoundException(id, Pauta.class.getSimpleName());
    }

    private void alterarPautaComPrazo(Pauta pauta, LocalDateTime prazo) {
        LocalDateTime date = LocalDateTime.now();
        LocalDateTime novoPrazo = (prazo != null) ? prazo : date.plusMinutes(1);
        pauta.setPrazo(novoPrazo);
    }
}