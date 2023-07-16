package com.votacao.pauta.repository;

import com.votacao.pauta.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
//estender JpeRepository para salvar os dados no banco
public interface PautaRepository extends JpaRepository<Pauta,Long> {
}
