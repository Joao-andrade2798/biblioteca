package com.andrade.bibliotecaApi.dto.response;

public record LivroDTO(
        String titulo,
        Integer ano,
        String genero,
        AutorDTO autor
) {
}
