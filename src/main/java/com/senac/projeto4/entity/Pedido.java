package com.senac.projeto4.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime; // Para a data e hora do pedido
import java.util.Objects;

@Entity
@Table(name = "pedido")
public class Pedido {

    // PK: Chave Primária
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedido_id")
    private Integer pedidoId;

    // Colunas de Transação
    @Column(name = "pedido_data_hora", nullable = false)
    private LocalDateTime pedidoDataHora;

    @Column(name = "pedido_valor_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal pedidoValorTotal;

    @Column(name = "pedido_status", nullable = false, length = 50)
    private String pedidoStatus; // Ex: 'RECEBIDO', 'EM_PREPARO', 'PRONTO'

    // Colunas do Cliente (simplificado para o MVP)
    @Column(name = "cliente_nome", length = 100)
    private String clienteNome;

    @Column(name = "cliente_endereco_entrega", length = 255)
    private String clienteEnderecoEntrega;

    @Column(name = "pedido_ativo", nullable = false)
    private Boolean pedidoAtivo;


    // FK: Chave Multi-Tenant
    @Column(name = "restaurante_id", nullable = false)
    private Integer restauranteId;


    // Construtor Vazio
    public Pedido() {}

    // ----------------------------------------------------
    // Getters e Setters (Você deve gerar todos no IntelliJ)
    // ----------------------------------------------------

    public Integer getPedidoId() { return pedidoId; }
    public void setPedidoId(Integer pedidoId) { this.pedidoId = pedidoId; }

    public LocalDateTime getPedidoDataHora() { return pedidoDataHora; }
    public void setPedidoDataHora(LocalDateTime pedidoDataHora) { this.pedidoDataHora = pedidoDataHora; }

    public BigDecimal getPedidoValorTotal() { return pedidoValorTotal; }
    public void setPedidoValorTotal(BigDecimal pedidoValorTotal) { this.pedidoValorTotal = pedidoValorTotal; }

    public String getPedidoStatus() { return pedidoStatus; }
    public void setPedidoStatus(String pedidoStatus) { this.pedidoStatus = pedidoStatus; }

    public String getClienteNome() { return clienteNome; }
    public void setClienteNome(String clienteNome) { this.clienteNome = clienteNome; }

    public String getClienteEnderecoEntrega() { return clienteEnderecoEntrega; }
    public void setClienteEnderecoEntrega(String clienteEnderecoEntrega) { this.clienteEnderecoEntrega = clienteEnderecoEntrega; }

    public Boolean getPedidoAtivo() { return pedidoAtivo; }
    public void setPedidoAtivo(Boolean pedidoAtivo) { this.pedidoAtivo = pedidoAtivo; }

    public Integer getRestauranteId() { return restauranteId; }
    public void setRestauranteId(Integer restauranteId) { this.restauranteId = restauranteId; }

    // ----------------------------------------------------
    // Equals e HashCode
    // ----------------------------------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(pedidoId, pedido.pedidoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedidoId);
    }
}