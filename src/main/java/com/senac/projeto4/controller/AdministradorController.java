package com.senac.projeto4.controller;

import com.senac.projeto4.dto.CreateAdminDto;
import com.senac.projeto4.dto.LoginAdminDto;
import com.senac.projeto4.dto.RecoveryJwtTokenDto;
import com.senac.projeto4.service.AdministradorService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth") // Rota de Autenticação
public class AdministradorController {

    private final AdministradorService administradorService;

    public AdministradorController(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }

    @Operation(summary = "Login do Administrador", description = "Autentica o administrador e retorna um Token JWT.")
    @PostMapping("/login")
    public ResponseEntity<RecoveryJwtTokenDto> loginAdministrador(@RequestBody @Valid LoginAdminDto loginAdminDto) {
        // Chama o serviço de login
        RecoveryJwtTokenDto token = administradorService.login(loginAdminDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @Operation(summary = "Cadastro Inicial de Administrador", description = "Rota pública para criar o primeiro administrador e vincular ao restaurante. É usada raramente.")
    @PostMapping("/criar-primeiro-admin") // Rota configurada como pública na SecurityConfiguration
    public ResponseEntity<Void> criarAdministrador(@RequestBody @Valid CreateAdminDto createAdminDto) {
        // Chama o serviço de criação (que criptografa a senha e vincula ao restaurante)
        administradorService.criarAdministrador(createAdminDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}