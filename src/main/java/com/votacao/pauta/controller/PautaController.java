package com.votacao.pauta.controller;

import com.votacao.pauta.model.Pauta;
import com.votacao.pauta.model.Usuario;
import com.votacao.pauta.service.PautaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/buscar-pauta")
public class PautaController {
    private PautaService pautaService;
    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @GetMapping("/{id}")
    public Pauta buscarPauta(@PathVariable Long id) {
        return pautaService.buscarPauta(id);
    }

    @PostMapping
    public Pauta inserirPauta(@RequestBody Pauta pauta) {
    return pautaService.inserirPauta(pauta);
    }
}
//TODO: CRIAR UM MÉTODO GET PARA BUSCAR UM USUÁRIO E UM POST PARA INSERIR UM USUÁRIO