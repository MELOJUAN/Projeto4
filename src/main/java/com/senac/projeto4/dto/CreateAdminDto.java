package com.senac.projeto4.dto;

import com.senac.projeto4.entity.RoleName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// DTO para a rota POST /api/auth/criar-primeiro-admin
public record CreateAdminDto(

        @NotBlank(message = "O nome é obrigatório.")
        @Size(max = 100)
        String nome,

        @NotBlank(message = "O e-mail é obrigatório.")
        @Email(message = "Formato de e-mail inválido.")
        String email, // Usado como login

        @NotBlank(message = "A senha é obrigatória.")
        String senha,

        @NotNull(message = "É obrigatório vincular este administrador a um restaurante.")
        Integer restauranteId, // Vínculo Multi-Tenant

        RoleName role // A Role será sempre ROLE_ADMINISTRATOR
) {
}