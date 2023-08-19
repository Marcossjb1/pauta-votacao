package com.votacao.pauta.repository;

import com.votacao.pauta.model.Usuario;
import com.votacao.pauta.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByNome (String nome);
}
