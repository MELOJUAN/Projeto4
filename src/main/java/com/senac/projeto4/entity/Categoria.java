package com.senac.projeto4.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "categoria")
public class Categoria {

    // PK: Chave Primária
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoria_id")
    private Integer categoriaId;

    // Colunas de Conteúdo
    @Column(name = "categoria_nome", nullable = false, length = 50)
    private String categoriaNome;

    @Column(name = "categoria_ativo", nullable = false)
    private Boolean categoriaAtivo;

    // FK: Chave Multi-Tenant (Relacionamento com a tabela 'restaurante')
    @Column(name = "restaurante_id", nullable = false)
    private Integer restauranteId;

    // Construtor Vazio
    public Categoria() {}

    // ----------------------------------------------------
    // Getters e Setters
    // ----------------------------------------------------

    // (Você deve usar Alt + Insert / Generate no IntelliJ para criar os Getters e Setters)

    public Integer getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Integer categoriaId) { this.categoriaId = categoriaId; }

    public String getCategoriaNome() { return categoriaNome; }
    public void setCategoriaNome(String categoriaNome) { this.categoriaNome = categoriaNome; }

    public Boolean getCategoriaAtivo() { return categoriaAtivo; }
    public void setCategoriaAtivo(Boolean categoriaAtivo) { this.categoriaAtivo = categoriaAtivo; }

    public Integer getRestauranteId() { return restauranteId; }
    public void setRestauranteId(Integer restauranteId) { this.restauranteId = restauranteId; }

    // ----------------------------------------------------
    // Equals e HashCode
    // ----------------------------------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(categoriaId, categoria.categoriaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoriaId);
    }
}