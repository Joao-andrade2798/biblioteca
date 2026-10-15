package com.andrade.bibliotecaApi.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;

public record EmprestimoDTO(
        @Schema(description = "Id do empréstimo", example = "9a7d8b6c-5e4f-4a3b-8c9d-0e1f2a3b4c5d") String idEmprestimo,
        @Schema(description = "Id do usuário", example = "b3f1c2a4-5d6e-4f7a-9b8c-1d2e3f4a5b6c")
        @NotNull(message = "Necessário informar usuário para realizar o empréstimo") String idUsuario,
        @Schema(description = "Id do livro", example = "c4d2e3b5-6f7a-4b8c-9d0e-2f3a4b5c6d7e")
        @NotNull(message = "Necessário informar o livro para realizar o empréstimo") String idLivro,
        @Schema(description = "Data do empréstimo", example = "2026-10-15") LocalDate dataEmprestimo,
        @Schema(description = "Data de devolução", example = "2026-10-22") LocalDate dataDevolução
) {
}
