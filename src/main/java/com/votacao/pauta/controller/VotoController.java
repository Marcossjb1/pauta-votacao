package com.votacao.pauta.controller;

import com.votacao.pauta.dto.ResultadoVotacao;
import com.votacao.pauta.model.Voto;
import com.votacao.pauta.service.impl.VotoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/voto")
public class VotoController {

    @Autowired
    private VotoServiceImpl votoServiceImpl;

    @GetMapping("/{id}")
    public Voto buscarVoto(@PathVariable Long id){
        return votoServiceImpl.buscarVoto(id);
    }

    @PostMapping("/inserir")
    public Voto inserirVoto(@RequestBody Voto voto){
        return votoServiceImpl.inserirVoto(voto);
    }

    @GetMapping("resultado/{id}")
    public ResultadoVotacao buscarResultado(@PathVariable Long id){
        return votoServiceImpl.resultadoVotacao(id);
    }


}
