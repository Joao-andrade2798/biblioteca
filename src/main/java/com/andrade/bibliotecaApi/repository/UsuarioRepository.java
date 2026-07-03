package com.andrade.bibliotecaApi.repository;

import com.andrade.bibliotecaApi.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository <Usuario, String > {
    Optional<Usuario> findByNome(String nome);
}
