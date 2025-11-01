package com.senac.projeto4.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// DTO usado para criar ou atualizar uma Categoria
public record CategoriaDto(

        @NotBlank(message = "O nome da categoria é obrigatório.")
        @Size(max = 50, message = "O nome não pode exceder 50 caracteres.")
        String nome

        // O ID do Restaurante é implícito na requisição (do JWT ou URL)
) {
}