package com.senac.projeto4.controller;

import com.senac.projeto4.entity.ItemPedido;
import com.senac.projeto4.entity.Pedido;
import com.senac.projeto4.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

// Usaremos a rota base /api/pedidos para ambas as funções, distinguindo pelo tipo de requisição (POST vs. GET seguro)
@RestController
@RequestMapping("api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    // ==========================================================
    // ROTA PÚBLICA (CLIENTE FAZENDO O PEDIDO)
    // ==========================================================

    @Operation(summary = "Realiza um novo Pedido", description = "Recebe o pedido do cliente e salva no BD. Rota pública.")
    @PostMapping("/{restauranteId}")
    public ResponseEntity<Pedido> criarPedido(
            @PathVariable Integer restauranteId,
            // Em um projeto real, você receberia um DTO de Pedido
            @RequestBody String clienteNome // Apenas simulando a entrada
    ) {
        // --- SIMULAÇÃO ---
        // Aqui você receberia um DTO e faria as validações.
        // Simulando a criação de um item para usar o PedidoService.
        ItemPedido itemSimulado = new ItemPedido();
        itemSimulado.setProdutoId(1);
        itemSimulado.setItemPedidoQuantidade(2);

        Pedido novoPedido = pedidoService.criarNovoPedidoSimulado(
                restauranteId,
                clienteNome,
                Collections.singletonList(itemSimulado)
        );
        // -----------------

        return new ResponseEntity<>(novoPedido, HttpStatus.CREATED);
    }

    // ==========================================================
    // ROTA SEGURA (ADMIN VISUALIZA OS PEDIDOS)
    // ==========================================================

    @Operation(summary = "Lista Pedidos por Restaurante", description = "Retorna todos os pedidos de um restaurante. Rota protegida por JWT.")
    @GetMapping("/admin/{restauranteId}")
    // Futuramente, esta rota precisa ser protegida pelo Spring Security
    public ResponseEntity<List<Pedido>> listarPedidosPorRestaurante(@PathVariable Integer restauranteId) {
        // NOTA: Em produção, o restauranteId não viria da URL, mas sim do Token JWT (para segurança)
        List<Pedido> pedidos = pedidoService.listarPedidosPorRestaurante(restauranteId);
        return ResponseEntity.ok(pedidos);
    }
}