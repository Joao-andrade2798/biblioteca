package com.andrade.bibliotecaApi.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ExemplarRequest(
        @NotBlank(message = "idLivro não pode ser nulo") String idLivro,
        @NotNull(message = "Quantidade é obrigatória")
        @Min(value = 1, message = "Quantidade mínima é 1") Integer quantidade
) {
}
