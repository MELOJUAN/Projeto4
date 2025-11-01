package com.senac.projeto4.service;

import com.senac.projeto4.entity.Administrador; // Importa a Entidade Administrador
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private final Administrador administrador; // A Entidade que representa o usuário logado

    public UserDetailsImpl(Administrador administrador) {
        this.administrador = administrador;
    }

    /**
     * Mapeia as Roles (Permissões) do Administrador para o formato do Spring Security.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return administrador.getRoles() // Usa o método getRoles() da Entidade Administrador
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
    }

    // Retorna a senha criptografada
    @Override
    public String getPassword() {
        return administrador.getAdministradorSenha();
    }

    // Retorna o login (o email, neste caso)
    @Override
    public String getUsername() {
        return administrador.getAdministradorEmail();
    }

    // Métodos de status da conta (mantidos como true)
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }

    // Método auxiliar para buscar o objeto Administrador completo (útil na lógica de negócio)
    public Administrador getAdministrador() {
        return administrador;
    }
}