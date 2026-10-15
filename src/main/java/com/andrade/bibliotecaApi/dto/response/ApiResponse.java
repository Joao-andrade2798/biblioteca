package com.andrade.bibliotecaApi.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record ApiResponse<T>(
        @Schema(description = "Indica se a operação foi bem-sucedida", example = "true") boolean sucesso,
        @Schema(description = "Mensagem da operação", example = "Operação realizada com sucesso") String mensagem,
        @Schema(description = "Dados retornados") T dados
) {
}
