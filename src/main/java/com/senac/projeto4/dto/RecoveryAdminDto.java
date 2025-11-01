package com.senac.projeto4.dto;

import com.senac.projeto4.entity.Role;
import java.util.List;

// DTO para retornar informações do administrador logado (após o JWT ser gerado)
public record RecoveryAdminDto(

        Integer id,
        String email,
        Integer restauranteId, // CHAVE CRÍTICA: Qual restaurante este admin gerencia
        List<Role> roles // Permissões

) {
}