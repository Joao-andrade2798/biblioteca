package com.andrade.bibliotecaApi.repository;

import com.andrade.bibliotecaApi.dto.response.EmprestimoDTO;
import com.andrade.bibliotecaApi.entity.Emprestimo;
import com.andrade.bibliotecaApi.entity.Exemplar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, String> {

    /**
     * Consulta os empréstimos ativos (ainda não devolvidos) de um usuário para um livro,
     * ordenados do mais recente para o mais antigo.
     *
     * @param idUsuario id do usuário
     * @param idLivro   id do livro
     * @return lista de empréstimos ativos; vazia se não houver nenhum
     */
@Query("""
        SELECT e FROM Emprestimo e
        WHERE e.usuario.id = :idUsuario
        AND e.exemplar.livro.id = :idLivro
        AND e.dataDevolucao IS NULL
        ORDER BY e.dataEmprestimo DESC
        """)
public List<Emprestimo> consultaEmprestimo(@Param("idUsuario") String idUsuario, @Param("idLivro") String idLivro);

    @Query("""
            SELECT e FROM Emprestimo e 
            JOIN FETCH e.usuario 
            JOIN FETCH e.exemplar ex
            JOIN FETCH ex.livro
            ORDER BY e.dataEmprestimo DESC, e.usuario.id
            """)
    public List<Emprestimo> buscarTodosEmprestimos();

    @Query("""
        SELECT e FROM Emprestimo e
        WHERE e.usuario.id = :idUsuario
        AND e.dataDevolucao IS NULL
        ORDER BY e.dataEmprestimo DESC, e.exemplar.livro.titulo
        """)
    public List<Emprestimo> buscarEmprestimosAtivosPorUsuario(@Param("idUsuario") String idUsuario);
}
