package com.senac.projeto4.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;

// DTO de resposta para listar produtos no App Mobile (leitura)
public record ProdutoResponseDto(

        @NotNull
        Integer id,

        @NotNull
        String nome,

        String descricao,

        @NotNull
        BigDecimal preco, // Usando BigDecimal para precisão

        String imagemUrl,

        // Chaves Estrangeiras, se o Front precisar filtrar localmente
        Integer categoriaId,
        Integer restauranteId
) {
}