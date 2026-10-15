package com.andrade.bibliotecaApi.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ExemplarRequest(
        @Schema(description = "Id do livro", example = "b3f1c2a4-5d6e-4f7a-9b8c-1d2e3f4a5b6c") @NotBlank(message = "idLivro não pode ser nulo") String idLivro,
        @Schema(description = "Quantidade de exemplares a cadastrar", example = "3")
        @NotNull(message = "Quantidade é obrigatória")
        @Min(value = 1, message = "Quantidade mínima é 1") Integer quantidade
) {
}
