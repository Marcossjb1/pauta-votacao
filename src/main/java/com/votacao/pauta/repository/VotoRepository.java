package com.votacao.pauta.repository;

import com.votacao.pauta.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {
    Voto findByIdUsuarioAndIdPauta(Long idUser,Long idPauta);

    List<Voto> findByIdPauta(Long idPauta);
}
