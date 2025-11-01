package com.senac.projeto4.repository;

import com.senac.projeto4.entity.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {

    /**
     * Busca um Administrador pelo seu endereço de e-mail (que é o campo de login/username).
     * Este método é crucial para o Spring Security carregar os detalhes do usuário
     * no momento da autenticação.
     *
     * O Spring Data JPA cria a implementação automaticamente baseado no nome do método.
     *
     * @param email O e-mail (username) do administrador.
     * @return Um Optional contendo o Administrador, se encontrado.
     */
    Optional<Administrador> findByAdministradorEmail(String email);
}