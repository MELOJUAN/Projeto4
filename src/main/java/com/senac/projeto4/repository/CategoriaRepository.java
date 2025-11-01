package com.senac.projeto4.repository;

import com.senac.projeto4.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    /**
     * Consulta essencial para o Cardápio Público:
     * Retorna todas as categorias ativas, filtradas pelo restaurante_id.
     */
    List<Categoria> findByRestauranteIdAndCategoriaAtivo(Integer restauranteId, Boolean categoriaAtivo);

    /**
     * Consulta para buscar uma categoria específica pelo ID e pelo ID do Restaurante.
     * Usado para garantir que o Admin só edite suas próprias categorias.
     */
    Optional<Categoria> findByRestauranteIdAndCategoriaId(Integer restauranteId, Integer categoriaId);
}