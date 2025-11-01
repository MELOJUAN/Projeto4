package com.senac.projeto4.config;

import com.senac.projeto4.entity.Administrador; // Nova Entidade
import com.senac.projeto4.repository.AdministradorRepository; // Novo Repositório
import com.senac.projeto4.service.JwtTokenService;
import com.senac.projeto4.service.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private AdministradorRepository administradorRepository; // Uso do novo Repositório

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 1. Verifica se a URL requer autenticação
        if (checkIfEndpointIsNotPublic(request)) {
            String token = recoveryToken(request);

            if (token != null) {
                String subject = jwtTokenService.getSubjectFromToken(token);

                // 2. Busca o usuário pelo email (subject) no BD
                Administrador admin = administradorRepository.findByAdministradorEmail(subject)
                        .orElseThrow(() -> new RuntimeException("Administrador não encontrado no token.")); // Adaptação

                // 3. Cria o objeto UserDetails para o Spring Security
                UserDetailsImpl userDetails = new UserDetailsImpl(admin);

                // 4. Cria o token de autenticação (sem senha)
                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());

                // 5. Salva a autenticação no contexto
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                // Caso o endpoint seja protegido, mas o token esteja ausente
                throw new RuntimeException("O token está ausente.");
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

    // Método para verificar se o endpoint é público (será usado a lista da SecurityConfiguration)
    private boolean checkIfEndpointIsNotPublic(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return !Arrays.stream(SecurityConfiguration.ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED)
                .anyMatch(requestURI::startsWith); // Usa startsWith para cobrir /**
    }
}