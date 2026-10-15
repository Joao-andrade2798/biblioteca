package com.andrade.bibliotecaApi.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(
        @Schema(description = "Nome do usuário", example = "João Silva")
        @NotBlank(message = "Nome obrigatório para cadastro")
        String nome,
        @Schema(description = "E-mail do usuário", example = "joao.silva@email.com")
        @NotBlank(message = "E-mail obrigatório para cadastro")
        @Email(message = "Formato do e-mail inválido")
        String email,
        @Schema(description = "Telefone do usuário", example = "(11) 99999-8888")
        String telefone
) {
}
