package com.senac.projeto4.dto;

import jakarta.validation.constraints.NotBlank;

// DTO para a rota POST /api/auth/login
public record LoginAdminDto(
        @NotBlank(message = "O login (e-mail) é obrigatório.")
        String login,

        @NotBlank(message = "A senha é obrigatória.")
        String senha
) {
}