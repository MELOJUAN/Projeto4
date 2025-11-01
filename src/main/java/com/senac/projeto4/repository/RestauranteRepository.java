package com.senac.projeto4.repository;

import com.senac.projeto4.entity.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Integer> {

    /**
     * Consulta CRÍTICA para a API Pública:
     * Permite buscar as configurações e o tema de um restaurante pelo SLUG.
     * Ex: GET /api/publico/restaurante/{slug}
     */
    Optional<Restaurante> findByRestauranteSlug(String restauranteSlug);
}