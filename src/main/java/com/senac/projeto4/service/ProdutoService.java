package com.senac.projeto4.service;

import com.senac.projeto4.entity.Produto;
import com.senac.projeto4.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    /**
     * MÉTODOS PÚBLICOS (Cardápio)
     */

    /**
     * Busca todos os produtos ativos de um restaurante.
     * Essencial para a listagem completa do cardápio.
     */
    public List<Produto> buscarProdutosAtivosPorRestaurante(Integer restauranteId) {
        // Usa o método do Repository para filtrar por ID do Restaurante e Status Ativo
        return produtoRepository.findByRestauranteIdAndProdutoAtivo(restauranteId, true);
    }

    /**
     * Busca produtos ativos, filtrando por restaurante E categoria.
     */
    public List<Produto> buscarProdutosPorCategoria(Integer restauranteId, Integer categoriaId) {
        return produtoRepository.findByRestauranteIdAndCategoriaIdAndProdutoAtivo(restauranteId, categoriaId, true);
    }

    /**
     * MÉTODOS ADMIN (CRUD - Gerenciamento)
     */

    /**
     * Busca um produto pelo ID. CRÍTICO: Filtra também pelo restauranteId para segurança.
     */
    public Optional<Produto> buscarProdutoPorIdESeguro(Integer produtoId, Integer restauranteId) {
        // Embora não tenhamos criado este método no Repositório, o Spring Data JPA
        // permite buscá-lo e então verificar o ID. Para a segurança, é melhor o ADMIN
        // não ter acesso a produtos que não são dele.
        return produtoRepository.findById(produtoId)
                .filter(p -> p.getRestauranteId().equals(restauranteId));
    }

    // Você adicionaria aqui métodos para criar, atualizar e deletar produtos (métodos POST, PUT, DELETE).
}