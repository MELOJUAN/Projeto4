package com.senac.projeto4.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private UserAuthenticationFilter userAuthenticationFilter;

    // --- ENDPOINTS PÚBLICOS (Não requerem JWT) ---
    public static final String [] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
            // 1. Autenticação (Login do Administrador)
            "/api/auth/login",
            "/api/auth/criar-primeiro-admin", // Rota de cadastro inicial (pública)

            // 2. Cardápio Público (Rota aberta para o App Mobile)
            "/api/publico/restaurante/*", // Ex: /api/publico/restaurante/lanchonete-senac
            "/api/publico/produtos/*",

            // 3. Swagger/OpenAPI UI
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };

    // --- ENDPOINTS PROTEGIDOS (Requerem JWT e Permissão) ---

    // Endpoints que só podem ser acessados por ADMINISTRADOR (Gestão de Conteúdo/Pedidos)
    public static final String [] ENDPOINTS_ADMIN = {
            "/api/admin/**" // Rota para CRUD de Produtos, Pedidos, Temas, etc.
    };

    // NOTA: Para este MVP, as rotas de cliente (CUSTOMER) podem ser ignoradas ou públicas.

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desativa proteção CSRF (comum em APIs REST)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Não usa sessão
                .authorizeHttpRequests(auth -> auth

                        // Permite acesso irrestrito aos endpoints definidos como públicos
                        .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll()

                        // Permite acesso de OPTIONS (necessário para o Swagger/CORS)
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // Define que as rotas ADMIN requerem o papel ROLE_ADMINISTRATOR
                        .requestMatchers(ENDPOINTS_ADMIN).hasRole("ADMINISTRATOR")

                        // Todas as outras requisições devem ser negadas, a menos que definidas acima
                        .anyRequest().denyAll()
                )
                // Adiciona o filtro JWT antes do filtro padrão de login (UsernamePasswordAuthenticationFilter)
                .addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Usamos BCrypt para criptografar senhas
    }
}