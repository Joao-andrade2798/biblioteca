package com.andrade.bibliotecaApi.dto.response;

import com.andrade.bibliotecaApi.enums.StatusExemplarEnum;
import io.swagger.v3.oas.annotations.media.Schema;

public record ExemplarDTO(
        @Schema(description = "Código do exemplar", example = "1") Integer codigo,
        @Schema(description = "Livro do exemplar") LivroDTO livro,
        @Schema(description = "Status do exemplar", example = "DISPONIVEL") StatusExemplarEnum statusExemplarEnum
) {
}
