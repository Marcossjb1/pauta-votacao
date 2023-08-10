package com.votacao.pauta.service;

import com.votacao.pauta.model.Pauta;

public interface PautaService {
    Pauta inserirPauta(Pauta pauta);
    Pauta inserirSessao(Pauta pauta);
    Pauta buscarPauta(Long id);

}
