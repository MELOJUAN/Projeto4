package com.senac.projeto4.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class JwtTokenService {

    // CHAVE SECRETA: Use uma chave forte e armazene-a de forma segura (aqui, está hardcoded para o desenvolvimento)
    private static final String SECRET_KEY = "CHAVE_SECRETA_MUITO_FORTE_DO_SENAC_PROJETO4";

    // EMISSOR: Identificação da API que gerou o token
    private static final String ISSUER = "restaurante-api-senac";

    /**
     * Gera um Token JWT para o usuário (Administrador) autenticado.
     * O Subject (Assunto) será o email do Administrador.
     */
    public String generateToken(UserDetails user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withIssuedAt(creationDate())
                    .withExpiresAt(expirationDate())
                    .withSubject(user.getUsername()) // O username (email) é o identificador único
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new JWTCreationException("Erro ao gerar token JWT.", exception);
        }
    }

    /**
     * Valida o Token e retorna o Subject (email) contido nele.
     */
    public String getSubjectFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject(); // Retorna o email do administrador
        } catch (JWTVerificationException exception){
            throw new JWTVerificationException("Token inválido ou expirado.");
        }
    }

    private Instant creationDate() {
        return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toInstant(); // Ajustado para SP
    }

    private Instant expirationDate() {
        // Token expira em 4 horas (adapte conforme necessário)
        return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).plusHours(4).toInstant();
    }

}