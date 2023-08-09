package com.votacao.pauta.controller;

import com.votacao.pauta.model.Pauta;
import com.votacao.pauta.service.impl.PautaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pauta")
public class PautaController {

    @Autowired
    private PautaServiceImpl pautaServiceImpl;

    @GetMapping("/{id}")
    public Pauta buscarPauta(@PathVariable Long id) {
        return pautaServiceImpl.buscarPauta(id);
    }

    @PostMapping("/inserir")
    public Pauta inserirPauta(@RequestBody Pauta pauta) {
        return pautaServiceImpl.inserirPauta(pauta);
    }

    @PostMapping("/sessao")
    public Pauta inserirSessao(@RequestBody Pauta pauta) {
        return pautaServiceImpl.inserirSessao(pauta);
    }
}



