package com.votacao.pauta.repository;

import com.votacao.pauta.model.Usuario;
import com.votacao.pauta.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
