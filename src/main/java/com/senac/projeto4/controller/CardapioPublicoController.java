package com.senac.projeto4.controller;

import com.senac.projeto4.entity.Categoria;
import com.senac.projeto4.entity.Produto;
import com.senac.projeto4.entity.Restaurante;
import com.senac.projeto4.service.CategoriaService;
import com.senac.projeto4.service.ProdutoService;
import com.senac.projeto4.service.RestauranteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/publico") // Rota de acesso público (sem autenticação)
public class CardapioPublicoController {

    private final RestauranteService restauranteService;
    private final CategoriaService categoriaService;
    private final ProdutoService produtoService;

    public CardapioPublicoController(
            RestauranteService restauranteService,
            CategoriaService categoriaService,
            ProdutoService produtoService)
    {
        this.restauranteService = restauranteService;
        this.categoriaService = categoriaService;
        this.produtoService = produtoService;
    }

    @Operation(summary = "Busca Configurações do Restaurante", description = "Retorna nome, cores e URLs do tema, usando o SLUG como chave. CRÍTICO: Rota Multi-Tenant.")
    @GetMapping("/restaurante/{slug}")
    public ResponseEntity<Restaurante> buscarRestaurantePorSlug(@PathVariable String slug) {
        Restaurante restaurante = restauranteService.buscarPorSlug(slug);
        return ResponseEntity.ok(restaurante);
    }

    @Operation(summary = "Busca Categorias do Restaurante", description = "Retorna as categorias ativas do restaurante específico. Usa o restauranteId retornado pela rota de slug.")
    @GetMapping("/categorias/{restauranteId}")
    public ResponseEntity<List<Categoria>> buscarCategorias(@PathVariable Integer restauranteId) {
        List<Categoria> categorias = categoriaService.buscarCategoriasAtivasPorRestaurante(restauranteId);
        return ResponseEntity.ok(categorias);
    }

    @Operation(summary = "Busca Todos os Produtos Ativos", description = "Retorna todos os produtos ativos de um restaurante específico.")
    @GetMapping("/produtos/{restauranteId}")
    public ResponseEntity<List<Produto>> buscarTodosProdutos(@PathVariable Integer restauranteId) {
        List<Produto> produtos = produtoService.buscarProdutosAtivosPorRestaurante(restauranteId);
        return ResponseEntity.ok(produtos);
    }
}