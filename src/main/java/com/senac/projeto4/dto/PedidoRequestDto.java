package com.senac.projeto4.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

// DTO completo para o cliente criar um novo pedido
public record PedidoRequestDto(

        @NotBlank(message = "O nome do cliente é obrigatório.")
        @Size(max = 100)
        String clienteNome,

        @NotBlank(message = "O endereço de entrega é obrigatório.")
        @Size(max = 255)
        String enderecoEntrega,

        @NotNull(message = "A lista de itens não pode ser nula.")
        @Size(min = 1, message = "O pedido deve ter pelo menos um item.")
        List<@Valid ItemPedidoRequestDto> itens // Lista de DTOs de Itens
) {
}