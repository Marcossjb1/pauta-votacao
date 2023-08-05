package com.votacao.pauta.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResultadoVotacao {
    private Long idPauta;
    private int sim;
    private int nao;
    private String resultado;
}
