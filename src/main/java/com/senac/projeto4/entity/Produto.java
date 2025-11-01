package com.senac.projeto4.entity;

import jakarta.persistence.*;
import java.math.BigDecimal; // Import para o tipo DECIMAL (Preço)
import java.util.Objects;

@Entity
@Table(name = "produto")
public class Produto {

    // PK: Chave Primária
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produto_id")
    private Integer produtoId;

    // Colunas de Conteúdo
    @Column(name = "produto_nome", nullable = false, length = 100)
    private String produtoNome;

    @Column(name = "produto_descricao", columnDefinition = "TEXT") // Mapeia o tipo TEXT do MySQL
    private String produtoDescricao;

    @Column(name = "produto_preco", nullable = false, precision = 10, scale = 2)
    private BigDecimal produtoPreco; // Tipo ideal para valores monetários

    @Column(name = "produto_imagem_url", length = 255)
    private String produtoImagemUrl;

    @Column(name = "produto_ativo", nullable = false)
    private Boolean produtoAtivo;


    // FK 1: Chave Multi-Tenant (Relacionamento com a tabela 'restaurante')
    @Column(name = "restaurante_id", nullable = false)
    private Integer restauranteId;

    // FK 2: Chave para a Categoria
    @Column(name = "categoria_id", nullable = false)
    private Integer categoriaId;


    // Construtor Vazio
    public Produto() {}

    // ----------------------------------------------------
    // Getters e Setters (Você deve gerar todos no IntelliJ)
    // ----------------------------------------------------

    public Integer getProdutoId() { return produtoId; }
    public void setProdutoId(Integer produtoId) { this.produtoId = produtoId; }

    public String getProdutoNome() { return produtoNome; }
    public void setProdutoNome(String produtoNome) { this.produtoNome = produtoNome; }

    public String getProdutoDescricao() { return produtoDescricao; }
    public void setProdutoDescricao(String produtoDescricao) { this.produtoDescricao = produtoDescricao; }

    public BigDecimal getProdutoPreco() { return produtoPreco; }
    public void setProdutoPreco(BigDecimal produtoPreco) { this.produtoPreco = produtoPreco; }

    public String getProdutoImagemUrl() { return produtoImagemUrl; }
    public void setProdutoImagemUrl(String produtoImagemUrl) { this.produtoImagemUrl = produtoImagemUrl; }

    public Boolean getProdutoAtivo() { return produtoAtivo; }
    public void setProdutoAtivo(Boolean produtoAtivo) { this.produtoAtivo = produtoAtivo; }

    public Integer getRestauranteId() { return restauranteId; }
    public void setRestauranteId(Integer restauranteId) { this.restauranteId = restauranteId; }

    public Integer getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Integer categoriaId) { this.categoriaId = categoriaId; }

    // ----------------------------------------------------
    // Equals e HashCode
    // ----------------------------------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(produtoId, produto.produtoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produtoId);
    }
}