package com.andrade.bibliotecaApi.repository;

import com.andrade.bibliotecaApi.entity.Autor;
import com.andrade.bibliotecaApi.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, String> {
    Optional<Livro> findByTitulo(String titulo);
}
