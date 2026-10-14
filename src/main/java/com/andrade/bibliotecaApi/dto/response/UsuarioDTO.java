package com.andrade.bibliotecaApi.dto.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(
        @NotBlank(message = "Nome obrigatório para cadastro")
        String nome,
        @NotBlank(message = "E-mail obrigatório para cadastro")
        @Email(message = "Formato do e-mail inválido")
        String email,
        String telefone
) {
}
