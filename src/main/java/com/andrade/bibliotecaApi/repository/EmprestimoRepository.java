package com.andrade.bibliotecaApi.repository;

import com.andrade.bibliotecaApi.entity.Emprestimo;
import com.andrade.bibliotecaApi.entity.Exemplar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, String> {
}
