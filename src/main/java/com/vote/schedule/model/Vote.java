package com.vote.schedule.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "VOTO")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ID_PAUTA")
    private Long idSchedule;
    @Column(name = "ID_USUARIO")
    private Long idUser;
    @Column(name = "VOTO")
    private Boolean vote;
}
