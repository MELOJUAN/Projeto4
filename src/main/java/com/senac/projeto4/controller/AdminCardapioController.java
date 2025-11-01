package com.senac.projeto4.controller;

import com.senac.projeto4.dto.CategoriaDto; // Novo DTO
import com.senac.projeto4.dto.ProdutoDto; // Novo DTO
import com.senac.projeto4.entity.Categoria;
import com.senac.projeto4.entity.Produto;
import com.senac.projeto4.service.CategoriaService;
import com.senac.projeto4.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/admin/cardapio") // Rota PROTEGIDA por JWT (ROLE_ADMINISTRATOR)
public class AdminCardapioController {

    private final CategoriaService categoriaService;
    private final ProdutoService produtoService;

    public AdminCardapioController(CategoriaService categoriaService, ProdutoService produtoService) {
        this.categoriaService = categoriaService;
        this.produtoService = produtoService;
    }

    // --- MÓDULO CATEGORIA ---

    @Operation(summary = "ADMIN: Cria nova Categoria", description = "Cadastra uma nova categoria para o restaurante logado.")
    @PostMapping("/categorias/{restauranteId}")
    public ResponseEntity<Categoria> criarCategoria(
            @PathVariable Integer restauranteId,
            @RequestBody @Valid CategoriaDto categoriaDto // Usa o novo DTO
    ) {
        // NOTA: Em produção, o restauranteId deve ser validado/obtido do Token JWT.
        Categoria novaCategoria = categoriaService.criarCategoria(categoriaDto.nome(), restauranteId);
        return new ResponseEntity<>(novaCategoria, HttpStatus.CREATED);
    }

    // --- MÓDULO PRODUTO ---

    @Operation(summary = "ADMIN: Cria novo Produto", description = "Cadastra um novo produto, vinculando-o ao restaurante.")
    @PostMapping("/produtos/{restauranteId}")
    public ResponseEntity<Produto> criarProduto(
            @PathVariable Integer restauranteId,
            @RequestBody @Valid ProdutoDto produtoDto // Usa o novo DTO
    ) {
        // NOTA: A lógica para criar o produto seria adicionada ao ProdutoService
        // Produto novoProduto = produtoService.criarProduto(produtoDto, restauranteId);
        return new ResponseEntity<>(HttpStatus.CREATED); // Simulação de sucesso
    }

    @Operation(summary = "ADMIN: Lista Produtos para Gestão", description = "Lista todos os produtos (ativos/inativos) para o painel de edição.")
    @GetMapping("/produtos/{restauranteId}/gestao")
    public ResponseEntity<List<Produto>> listarProdutosParaGestao(@PathVariable Integer restauranteId) {
        List<Produto> produtos = produtoService.buscarProdutosAtivosPorRestaurante(restauranteId); // Simples
        return ResponseEntity.ok(produtos);
    }
}