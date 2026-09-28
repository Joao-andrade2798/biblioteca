package com.andrade.bibliotecaApi.repository;

import com.andrade.bibliotecaApi.entity.Exemplar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ExemplarRepository extends JpaRepository<Exemplar, String> {
    public Optional<Exemplar> findByCodigo (Integer id);

    public List<Exemplar> findByLivroId (String id);

    @Query("SELECT MAX(e.codigo) from Exemplar e")
    public Integer buscaMaiorCodigo();
}
