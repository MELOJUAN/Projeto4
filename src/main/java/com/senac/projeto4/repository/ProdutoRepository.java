package com.senac.projeto4.repository;

import com.senac.projeto4.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    /**
     * Consulta essencial para o Cardápio Público:
     * Retorna todos os produtos ativos de um restaurante específico.
     */
    List<Produto> findByRestauranteIdAndProdutoAtivo(Integer restauranteId, Boolean produtoAtivo);

    /**
     * Consulta para buscar produtos ativos, filtrados por restaurante e categoria.
     */
    List<Produto> findByRestauranteIdAndCategoriaIdAndProdutoAtivo(Integer restauranteId, Integer categoriaId, Boolean produtoAtivo);

    // Outros métodos de busca para a gestão (CRUD) podem ser adicionados aqui.
}