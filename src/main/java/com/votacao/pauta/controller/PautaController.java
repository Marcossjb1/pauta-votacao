package com.votacao.pauta.controller;

import com.votacao.pauta.model.Pauta;
import com.votacao.pauta.service.PautaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pauta")
public class PautaController {
    private final PautaService pautaService;

    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @GetMapping("/{id}")
    public Pauta buscarPauta(@PathVariable Long id) {
        return pautaService.buscarPauta(id);
    }

    @PostMapping("/inserir")
    public Pauta inserirPauta(@RequestBody Pauta pauta) {
    return pautaService.inserirPauta(pauta);
    }
}
