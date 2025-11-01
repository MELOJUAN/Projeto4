package com.senac.projeto4.service;

import com.senac.projeto4.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // Você pode adicionar métodos aqui no futuro para buscar, criar ou verificar Roles.
}