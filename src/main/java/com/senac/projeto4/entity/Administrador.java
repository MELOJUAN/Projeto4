package com.senac.projeto4.entity;

import jakarta.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "administrador")
public class Administrador {

    // PK: Chave Primária
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "administrador_id")
    private Integer administradorId;

    // Colunas de Credenciais e Informação
    @Column(name = "administrador_nome", nullable = false, length = 100)
    private String administradorNome;

    @Column(name = "administrador_email", nullable = false, length = 100, unique = true)
    private String administradorEmail; // Será o campo de LOGIN (Username)

    @Column(name = "administrador_senha", nullable = false, length = 255)
    private String administradorSenha; // A senha criptografada

    // FK: Chave Multi-Tenant
    @Column(name = "restaurante_id", nullable = false)
    private Integer restauranteId;

    // Relacionamento com as Roles (Permissões)
    // Muitos para Muitos (M:N) com tabela intermediária (JOIN)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "administrador_role", // Tabela pivot (junção)
            joinColumns = @JoinColumn(name = "administrador_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<Role> roles;

    // Construtor Vazio (Obrigatório para o JPA)
    public Administrador() {}

    // ----------------------------------------------------
    // Getters e Setters
    // ----------------------------------------------------

    public Integer getAdministradorId() { return administradorId; }
    public void setAdministradorId(Integer administradorId) { this.administradorId = administradorId; }

    public String getAdministradorNome() { return administradorNome; }
    public void setAdministradorNome(String administradorNome) { this.administradorNome = administradorNome; }

    public String getAdministradorEmail() { return administradorEmail; }
    public void setAdministradorEmail(String administradorEmail) { this.administradorEmail = administradorEmail; }

    public String getAdministradorSenha() { return administradorSenha; }
    public void setAdministradorSenha(String administradorSenha) { this.administradorSenha = administradorSenha; }

    public Integer getRestauranteId() { return restauranteId; }
    public void setRestauranteId(Integer restauranteId) { this.restauranteId = restauranteId; }

    public Collection<Role> getRoles() { return roles; }
    public void setRoles(Collection<Role> roles) { this.roles = roles; }

    // ----------------------------------------------------
    // Equals e HashCode (Boa prática para Entities)
    // ----------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Administrador that = (Administrador) o;
        return Objects.equals(administradorId, that.administradorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(administradorId);
    }
}