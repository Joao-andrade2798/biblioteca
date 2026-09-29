package com.andrade.bibliotecaApi.dto.response;

public record ApiResponse<T>(
        boolean sucesso,
        String mensagem,
        T dados
) {
}
