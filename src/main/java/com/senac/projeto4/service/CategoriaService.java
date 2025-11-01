package com.senac.projeto4.service;

import com.senac.projeto4.entity.Categoria;
import com.senac.projeto4.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    /**
     * MÉTODOS PÚBLICOS (Cardápio)
     */

    /**
     * Retorna todas as categorias ativas de um restaurante específico.
     * Essencial para montar a navegação do cardápio no App Mobile.
     */
    public List<Categoria> buscarCategoriasAtivasPorRestaurante(Integer restauranteId) {
        // Usa o método do Repository para filtrar por ID e Status Ativo
        return categoriaRepository.findByRestauranteIdAndCategoriaAtivo(restauranteId, true);
    }

    // --- Futuros métodos ADMIN (Gerenciamento do Cardápio) ---

    // Exemplo: Método que um Administrador autenticado usaria para cadastrar uma nova categoria
    public Categoria criarCategoria(String nome, Integer restauranteId) {
        // NOTA: Aqui seria necessário receber um DTO e validar a entrada
        Categoria novaCategoria = new Categoria();
        novaCategoria.setCategoriaNome(nome);
        novaCategoria.setRestauranteId(restauranteId); // Vínculo Multi-Tenant
        novaCategoria.setCategoriaAtivo(true);
        return categoriaRepository.save(novaCategoria);
    }
}