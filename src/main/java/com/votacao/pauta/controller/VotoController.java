package com.votacao.pauta.controller;

import com.votacao.pauta.model.ResultadoVotacao;
import com.votacao.pauta.model.Voto;
import com.votacao.pauta.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/voto")
public class VotoController {

    @Autowired
    private VotoService votoService;

    @GetMapping("/{id}")
    public Voto buscarVoto(@PathVariable Long id){
        return votoService.buscarVoto(id);
    }

    @PostMapping("/inserir")
    public Voto inserirVoto(@RequestBody Voto voto){
        return votoService.inserirVoto(voto);
    }
    @GetMapping("resultado/{id}")
    public ResultadoVotacao buscarResultado(@PathVariable Long id){
        return votoService.resultadoVotacao(id);
    }


}
