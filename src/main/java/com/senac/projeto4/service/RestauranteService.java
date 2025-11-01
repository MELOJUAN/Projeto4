package com.senac.projeto4.service;

import com.senac.projeto4.entity.Restaurante;
import com.senac.projeto4.repository.RestauranteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;

    public RestauranteService(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    /**
     * MÉTODOS PÚBLICOS (Sem autenticação)
     */

    /**
     * Busca um restaurante pelo SLUG. Essencial para o App Mobile.
     */
    public Restaurante buscarPorSlug(String slug) {
        return restauranteRepository.findByRestauranteSlug(slug)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado."));
    }

    // --- Futuros métodos ADMIN (Gerenciamento do Tema) ---

    // Você pode adicionar métodos aqui para criar o restaurante (apenas uma vez)
    // ou para um Admin alterar as cores e URLs do tema.

}