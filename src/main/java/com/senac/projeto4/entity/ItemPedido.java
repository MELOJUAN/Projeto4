package com.senac.projeto4.entity;

import jakarta.persistence.*;
import java.math.BigDecimal; // Essencial para precisão monetária
import java.util.Objects;

@Entity
@Table(name = "item_pedido")
public class ItemPedido {

    // PK: Chave Primária
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_pedido_id")
    private Integer itemPedidoId;

    // Colunas de Detalhe
    @Column(name = "item_pedido_quantidade", nullable = false)
    private Integer itemPedidoQuantidade;

    @Column(name = "item_pedido_preco_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal itemPedidoPrecoUnitario; // Preço do produto no momento da compra


    // FK 1: Chave para o Pedido (Ligação Muitos-Para-Um)
    @Column(name = "pedido_id", nullable = false)
    private Integer pedidoId;

    // FK 2: Chave para o Produto (Ligação Muitos-Para-Um)
    @Column(name = "produto_id", nullable = false)
    private Integer produtoId;


    // Construtor Vazio
    public ItemPedido() {}

    // ----------------------------------------------------
    // Getters e Setters (Você deve gerar todos no IntelliJ)
    // ----------------------------------------------------

    public Integer getItemPedidoId() { return itemPedidoId; }
    public void setItemPedidoId(Integer itemPedidoId) { this.itemPedidoId = itemPedidoId; }

    public Integer getItemPedidoQuantidade() { return itemPedidoQuantidade; }
    public void setItemPedidoQuantidade(Integer itemPedidoQuantidade) { this.itemPedidoQuantidade = itemPedidoQuantidade; }

    public BigDecimal getItemPedidoPrecoUnitario() { return itemPedidoPrecoUnitario; }
    public void setItemPedidoPrecoUnitario(BigDecimal itemPedidoPrecoUnitario) { this.itemPedidoPrecoUnitario = itemPedidoPrecoUnitario; }

    public Integer getPedidoId() { return pedidoId; }
    public void setPedidoId(Integer pedidoId) { this.pedidoId = pedidoId; }

    public Integer getProdutoId() { return produtoId; }
    public void setProdutoId(Integer produtoId) { this.produtoId = produtoId; }

    // ----------------------------------------------------
    // Equals e HashCode
    // ----------------------------------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedido that = (ItemPedido) o;
        return Objects.equals(itemPedidoId, that.itemPedidoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemPedidoId);
    }
}