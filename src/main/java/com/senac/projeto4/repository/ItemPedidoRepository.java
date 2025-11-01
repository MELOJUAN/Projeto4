package com.senac.projeto4.repository;

import com.senac.projeto4.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

    /**
     * Busca todos os itens (produtos) associados a um pedido específico.
     */
    List<ItemPedido> findByPedidoId(Integer pedidoId);
}