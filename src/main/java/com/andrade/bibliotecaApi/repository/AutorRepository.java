package com.andrade.bibliotecaApi.repository;

import com.andrade.bibliotecaApi.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, String> {

 Optional<Autor> findByNome(String nome);
}
