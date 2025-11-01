package com.senac.projeto4.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// DTO usado dentro do PedidoRequestDto para listar os itens
public record ItemPedidoRequestDto(

        @NotNull(message = "O ID do produto é obrigatório.")
        Integer produtoId,

        @NotNull(message = "A quantidade é obrigatória.")
        @Min(value = 1, message = "A quantidade mínima é 1.")
        Integer quantidade
) {
}