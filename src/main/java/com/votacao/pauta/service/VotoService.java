package com.votacao.pauta.service;

import com.votacao.pauta.dto.ResultadoVotacao;
import com.votacao.pauta.model.Voto;

public interface VotoService {
    Voto inserirVoto(Voto voto);
    Voto buscarVoto(Long id);
    ResultadoVotacao resultadoVotacao(Long idPauta);

}
