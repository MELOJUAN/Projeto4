package com.senac.projeto4.repository;

import com.senac.projeto4.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    /**
     * Consulta essencial para a área administrativa:
     * Lista todos os pedidos de um restaurante específico (Multi-Tenant).
     */
    List<Pedido> findByRestauranteId(Integer restauranteId);
}