package com.senac.projeto4.dto;

// DTO de resposta para listar categorias no App Mobile (leitura)
public record CategoriaResponseDto(

        Integer id,
        String nome,
        Integer restauranteId // O ID do restaurante a que pertence
        // Poderia incluir uma lista de Produtos, se desejássemos retornar tudo junto
) {
}