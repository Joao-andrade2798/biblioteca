package com.andrade.bibliotecaApi.dto.response;
import io.swagger.v3.oas.annotations.media.Schema;
public record AutorDTO(
        @Schema(description = "Nome do autor", example = "Machado de Assis") String nome,
        @Schema(description = "Nacionalidade do autor", example = "Brasileira") String nacionalidade
) {
}
