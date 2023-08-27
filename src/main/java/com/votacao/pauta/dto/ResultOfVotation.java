package com.votacao.pauta.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResultOfVotation {
    private Long idSchedule;
    private int yes;
    private int no;
    private String result;
}
