package com.senac.projeto4.service;

import com.senac.projeto4.entity.Administrador;
import com.senac.projeto4.repository.AdministradorRepository; // Importa o Repositório
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AdministradorRepository administradorRepository; // Injeta o Repositório

    /**
     * Método do Spring Security: Carrega o usuário pelo nome de usuário (email).
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 1. Busca o Administrador no BD usando o email (username)
        Administrador administrador = administradorRepository.findByAdministradorEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Administrador não encontrado com o e-mail: " + username));

        // 2. Retorna a instância adaptada para o Spring Security
        return new UserDetailsImpl(administrador);
    }
}