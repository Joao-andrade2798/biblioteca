package com.andrade.bibliotecaApi.repository;

import com.andrade.bibliotecaApi.dto.response.EmprestimoDTO;
import com.andrade.bibliotecaApi.entity.Emprestimo;
import com.andrade.bibliotecaApi.entity.Exemplar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, String> {

@Query("""
        SELECT e FROM Emprestimo e
        WHERE e.usuario.id = :idUsuario
        AND e.exemplar.livro.id = :idLivro
        AND e.dataDevolucao IS NULL
        """)
public List<Emprestimo> consultaEmprestimo(@Param("idUsuario") String idUsuario, @Param("idLivro") String idLivro);

    @Query("""
            SELECT e FROM Emprestimo e 
            JOIN FETCH e.usuario 
            JOIN FETCH e.exemplar ex
            JOIN FETCH ex.livro
            """)
    List<Emprestimo> buscarTodosEmprestimos();
}
