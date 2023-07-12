package com.votacao.pauta.controller;

import com.votacao.pauta.model.Pauta;
import com.votacao.pauta.model.Usuario;
import com.votacao.pauta.service.PautaService;
import org.springframework.web.bind.annotation.*;

@RestController // É uma anotação que vai dizer que a classe vai lidar com requisições http
@RequestMapping("/buscar-pauta")
// Uma forma de mapear URL's de requisições HTTP para métodos específicos em uma controller
public class PautaController {
    private PautaService pautaService;
    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @GetMapping("/buscar")
    public String buscarPauta() {
        return "buscar pauta";
    }

    @PostMapping //Essa anotação serve para informar que essa função vai inserir um usuário
    //@ResquetBody - Serve para dizer ao framework que desejamos vincular os dados enviados no corpo da
    //solicitacao http a um objeto específico do lado do servidor
    public Pauta inserirPauta(@RequestBody Pauta pauta) {
    return pautaService.inserirPauta(pauta);
    }
}
