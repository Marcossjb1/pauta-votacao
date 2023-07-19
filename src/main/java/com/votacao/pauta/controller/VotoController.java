package com.votacao.pauta.controller;

import com.votacao.pauta.model.Voto;
import com.votacao.pauta.service.VotoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/voto")
public class VotoController {
    private final VotoService votoService;

    public VotoController(VotoService votoService) {
        this.votoService = votoService;
    }

    @GetMapping("/{id}")
    public Voto buscarVoto(@PathVariable Long id){
        return votoService.buscarVoto(id);
    }

    @PostMapping("/inserir")
    public Voto inserirVoto(@RequestBody Voto voto){
        return votoService.inserirVoto(voto);
    }

}
