package com.senac.projeto4.controller;

import com.senac.projeto4.dto.UpdateStatusDto; // Novo DTO
import com.senac.projeto4.entity.Pedido;
import com.senac.projeto4.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin/pedidos") // Rota PROTEGIDA por JWT
public class AdminPedidoController {

    private final PedidoService pedidoService;

    public AdminPedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Operation(summary = "ADMIN: Listar Pedidos do Restaurante", description = "Lista todos os pedidos para o painel de gestão.")
    @GetMapping("/{restauranteId}")
    public ResponseEntity<List<Pedido>> listarPedidos(@PathVariable Integer restauranteId) {
        // O restauranteId seria validado/obtido do Token JWT.
        List<Pedido> pedidos = pedidoService.listarPedidosPorRestaurante(restauranteId);
        return ResponseEntity.ok(pedidos);
    }

    @Operation(summary = "ADMIN: Mudar Status do Pedido", description = "Atualiza o status do pedido (ex: para EM_PREPARO).")
    @PutMapping("/{pedidoId}")
    public ResponseEntity<Void> atualizarStatusPedido(@PathVariable Integer pedidoId, @RequestBody @Valid UpdateStatusDto statusDto) {
        // NOTA: A lógica para atualizar o status precisa ser adicionada ao PedidoService
        // pedidoService.atualizarStatus(pedidoId, statusDto.novoStatus());
        return ResponseEntity.noContent().build();
    }
}