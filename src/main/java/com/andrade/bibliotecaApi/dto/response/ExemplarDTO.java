package com.andrade.bibliotecaApi.dto.response;

import com.andrade.bibliotecaApi.enums.StatusExemplarEnum;

public record ExemplarDTO(
        Integer codigo,
        LivroDTO livro,
        StatusExemplarEnum statusExemplarEnum
) {
}
