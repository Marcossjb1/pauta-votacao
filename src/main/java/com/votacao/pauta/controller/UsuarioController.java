package com.votacao.pauta.controller;

import com.votacao.pauta.model.Pauta;
import com.votacao.pauta.model.Usuario;
import com.votacao.pauta.service.PautaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/buscar-usuario")
public class UsuarioController {
    private PautaService pautaService;

    public UsuarioController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @GetMapping("/usuario")
    public String buscarUsuario() {
        return "buscar usuário";
    }


}
