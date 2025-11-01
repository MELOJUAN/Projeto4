package com.senac.projeto4.service;

import com.senac.projeto4.dto.CreateAdminDto;
import com.senac.projeto4.dto.LoginAdminDto;
import com.senac.projeto4.dto.RecoveryJwtTokenDto;
import com.senac.projeto4.entity.Administrador;
import com.senac.projeto4.entity.Role;
import com.senac.projeto4.entity.RoleName;
import com.senac.projeto4.repository.AdministradorRepository;
import com.senac.projeto4.repository.RoleRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradorService {

    // Injeções de Repositórios e Serviços (O Spring cuida da criação desses objetos)
    private final AdministradorRepository administradorRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final PasswordEncoder passwordEncoder;

    public AdministradorService(
            AdministradorRepository administradorRepository,
            RoleRepository roleRepository,
            AuthenticationManager authenticationManager,
            JwtTokenService jwtTokenService,
            PasswordEncoder passwordEncoder)
    {
        this.administradorRepository = administradorRepository;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Lógica de Criação do Administrador (Gerente do Restaurante)
     * Recebe um DTO e salva o novo Admin no BD remoto.
     * CRÍTICO: Usa o restauranteId para o vínculo Multi-Tenant.
     */
    public void criarAdministrador(CreateAdminDto createAdminDto) {

        // 1. Garante que a Role existe (ADMINISTRATOR)
        Role role = roleRepository.findByName(RoleName.ROLE_ADMINISTRATOR);
        if (role == null) {
            // Caso a tabela 'role' esteja vazia, é necessário rodar um script INSERT inicial.
            throw new RuntimeException("Role de Administrador não encontrada no banco de dados.");
        }

        Administrador novoAdmin = new Administrador();

        // 2. Mapeamento de Campos
        novoAdmin.setAdministradorNome(createAdminDto.nome());
        novoAdmin.setAdministradorEmail(createAdminDto.email());

        // 3. Criptografia da Senha (BCrypt)
        novoAdmin.setAdministradorSenha(passwordEncoder.encode(createAdminDto.senha()));

        // 4. CHAVE MULTI-TENANT: Define o vínculo com o restaurante
        // O ID vem do DTO (quem está chamando a API deve saber qual ID vincular)
        novoAdmin.setRestauranteId(createAdminDto.restauranteId());

        novoAdmin.setRoles(List.of(role));

        // 5. Salva no BD remoto do SENAC
        administradorRepository.save(novoAdmin);
    }

    /**
     * Lógica de Login e Geração de JWT
     */
    public RecoveryJwtTokenDto login(LoginAdminDto loginAdminDto) {

        // 1. Cria o objeto de autenticação (usando email e senha)
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginAdminDto.login(), loginAdminDto.senha());

        // 2. Tenta autenticar (chama UserDetailsServiceImpl para buscar o usuário)
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        // 3. Obtém os detalhes do usuário autenticado (UserDetailsImpl)
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // 4. Gera o JWT (o TokenService usa o email/username para criar o token)
        return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));
    }
}