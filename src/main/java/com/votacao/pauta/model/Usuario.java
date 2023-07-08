package com.votacao.pauta.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;

public class Usuario {
    @Id //Serve para identificar que a variável id vai ser um identificador único
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Essa anotação gera um identificador único para a nossa entidade
    private Long id;
    private String nome;

}
