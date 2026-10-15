package com.andrade.bibliotecaApi.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record LivroDTO(
        @Schema(description = "Título do livro", example = "Dom Casmurro") String titulo,
        @Schema(description = "Ano de publicação", example = "1899") Integer ano,
        @Schema(description = "Gênero do livro", example = "Romance") String genero,
        @Schema(description = "Autor do livro") AutorDTO autor
) {
}
