package com.senac.projeto4.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal; // Import para valores monetários

// DTO usado para criar ou atualizar um Produto
public record ProdutoDto(

        @NotBlank(message = "O nome do produto é obrigatório.")
        @Size(max = 100, message = "O nome não pode exceder 100 caracteres.")
        String nome,

        @Size(max = 255, message = "A descrição não pode exceder 255 caracteres.")
        String descricao,

        @NotNull(message = "O preço é obrigatório.")
        @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero.")
        BigDecimal preco,

        String imagemUrl,

        @NotNull(message = "A Categoria é obrigatória.")
        Integer categoriaId, // FK: ID da Categoria

        @NotNull(message = "O status de Ativo é obrigatório.")
        Boolean ativo // Se o produto está visível no cardápio
) {
}