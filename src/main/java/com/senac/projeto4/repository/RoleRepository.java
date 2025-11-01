package com.senac.projeto4.repository; // Pacote correto

import com.senac.projeto4.entity.Role;
import com.senac.projeto4.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> { // Deve ser 'interface'

    // Método para buscar a permissão pelo nome
    Role findByName(RoleName name);
}