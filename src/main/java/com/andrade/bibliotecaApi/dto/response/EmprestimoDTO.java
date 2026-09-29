package com.andrade.bibliotecaApi.dto.response;

import jakarta.validation.constraints.NotNull;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;

public record EmprestimoDTO(
        @NotNull(message = "Necessário informar usuário para realizar o empréstimo") String idUsuario,
        @NotNull(message = "Necessário informar o livro para realizar o empréstimo") String idLivro,
        LocalDate dataEmprestimo,
        @NotNull Local dataDevolução
) {
    public EmprestimoDTO(Emprestimo)
}
