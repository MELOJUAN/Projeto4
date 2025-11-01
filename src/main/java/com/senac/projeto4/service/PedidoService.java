package com.senac.projeto4.service;

import com.senac.projeto4.entity.Pedido;
import com.senac.projeto4.entity.ItemPedido;
import com.senac.projeto4.entity.Produto;
import com.senac.projeto4.repository.PedidoRepository;
import com.senac.projeto4.repository.ItemPedidoRepository;
import com.senac.projeto4.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Import para garantir a transação

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoService(
            PedidoRepository pedidoRepository,
            ItemPedidoRepository itemPedidoRepository,
            ProdutoRepository produtoRepository)
    {
        this.pedidoRepository = pedidoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
        this.produtoRepository = produtoRepository;
    }

    /**
     * MÉTODOS PÚBLICOS
     */

    /**
     * Simula o recebimento e salvamento de um novo pedido.
     * Necessita de @Transactional para garantir que tudo seja salvo ou revertido em caso de erro.
     */
    @Transactional
    public Pedido criarNovoPedidoSimulado(Integer restauranteId, String clienteNome, List<ItemPedido> itensSimulados) {

        // 1. Calcula o valor total e valida a existência dos produtos
        BigDecimal valorTotal = BigDecimal.ZERO;
        for (ItemPedido item : itensSimulados) {
            // Busca o preço atual do produto no BD
            Produto produto = produtoRepository.findById(item.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + item.getProdutoId()));

            // Cálculo: Preço Unitário * Quantidade
            BigDecimal precoTotalItem = produto.getProdutoPreco().multiply(BigDecimal.valueOf(item.getItemPedidoQuantidade()));
            valorTotal = valorTotal.add(precoTotalItem);

            // Atualiza o preço unitário do item_pedido com o preço atual do BD
            item.setItemPedidoPrecoUnitario(produto.getProdutoPreco());
        }

        // 2. Cria o registro do Pedido principal
        Pedido novoPedido = new Pedido();
        novoPedido.setRestauranteId(restauranteId); // Vínculo Multi-Tenant
        novoPedido.setPedidoDataHora(LocalDateTime.now());
        novoPedido.setPedidoValorTotal(valorTotal);
        novoPedido.setClienteNome(clienteNome);
        novoPedido.setPedidoStatus("RECEBIDO");
        novoPedido.setPedidoAtivo(true);

        Pedido pedidoSalvo = pedidoRepository.save(novoPedido);

        // 3. Salva os Itens do Pedido, vinculando à PK do pedido salvo
        for (ItemPedido item : itensSimulados) {
            item.setPedidoId(pedidoSalvo.getPedidoId()); // Vincula a FK
            itemPedidoRepository.save(item);
        }

        return pedidoSalvo;
    }

    /**
     * MÉTODOS ADMIN
     */

    /**
     * Lista todos os pedidos de um restaurante.
     */
    public List<Pedido> listarPedidosPorRestaurante(Integer restauranteId) {
        return pedidoRepository.findByRestauranteId(restauranteId);
    }
}