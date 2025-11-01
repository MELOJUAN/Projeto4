package com.senac.projeto4.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "restaurante")
public class Restaurante {

    // PK: Chave Primária (restaurante_id)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurante_id")
    private Integer restauranteId;

    // Colunas de Conteúdo e Customização (ver diagrama)
    @Column(name = "restaurante_nome", nullable = false, length = 100)
    private String restauranteNome;

    @Column(name = "restaurante_slug", nullable = false, length = 50, unique = true)
    private String restauranteSlug;

    @Column(name = "restaurante_cor_primaria_hex", nullable = false, length = 7)
    private String restauranteCorPrimariaHex;

    @Column(name = "restaurante_logo_url", length = 255)
    private String restauranteLogoUrl;

    @Column(name = "restaurante_banner_url", length = 255)
    private String restauranteBannerUrl;

    @Column(name = "restaurante_ativo", nullable = false)
    private Boolean restauranteAtivo;

    // Construtor Vazio
    public Restaurante() {}

    // ----------------------------------------------------
    // Getters e Setters (Você deve gerá-los no IntelliJ)
    // ----------------------------------------------------

    public Integer getRestauranteId() { return restauranteId; }
    public void setRestauranteId(Integer restauranteId) { this.restauranteId = restauranteId; }

    public String getRestauranteNome() { return restauranteNome; }
    public void setRestauranteNome(String restauranteNome) { this.restauranteNome = restauranteNome; }

    public String getRestauranteSlug() { return restauranteSlug; }
    public void setRestauranteSlug(String restauranteSlug) { this.restauranteSlug = restauranteSlug; }

    public String getRestauranteCorPrimariaHex() { return restauranteCorPrimariaHex; }
    public void setRestauranteCorPrimariaHex(String restauranteCorPrimariaHex) { this.restauranteCorPrimariaHex = restauranteCorPrimariaHex; }

    public String getRestauranteLogoUrl() { return restauranteLogoUrl; }
    public void setRestauranteLogoUrl(String restauranteLogoUrl) { this.restauranteLogoUrl = restauranteLogoUrl; }

    public String getRestauranteBannerUrl() { return restauranteBannerUrl; }
    public void setRestauranteBannerUrl(String restauranteBannerUrl) { this.restauranteBannerUrl = restauranteBannerUrl; }

    public Boolean getRestauranteAtivo() { return restauranteAtivo; }
    public void setRestauranteAtivo(Boolean restauranteAtivo) { this.restauranteAtivo = restauranteAtivo; }

    // ----------------------------------------------------
    // Equals e HashCode (Boa prática)
    // ----------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurante that = (Restaurante) o;
        return Objects.equals(restauranteId, that.restauranteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(restauranteId);
    }
}