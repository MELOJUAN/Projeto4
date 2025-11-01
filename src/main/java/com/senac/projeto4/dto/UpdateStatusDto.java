package com.senac.projeto4.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

// DTO para atualizar o status de um pedido (Admin)
public record UpdateStatusDto(

        @NotBlank(message = "O novo status é obrigatório.")
        // Restringe os valores para um conjunto pré-definido de status do seu projeto
        // Os valores devem ser exatamente 'RECEBIDO', 'EM_PREPARO', etc.
        @Pattern(regexp = "RECEBIDO|EM_PREPARO|PRONTO|ENTREGUE|CANCELADO", message = "Status de pedido inválido. Use RECEBIDO, EM_PREPARO, PRONTO, ENTREGUE ou CANCELADO.")
        String novoStatus
) {
}