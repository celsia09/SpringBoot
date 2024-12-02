package com.example.revisaospring.services.implementation;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.revisaospring.entities.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenServiceImpl {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(Usuario usuario) {
        try{
            System.out.println("Iniciando geração do token...");
            System.out.println("Usuário: " + usuario.getEmail());
            Algorithm algorithm = Algorithm.HMAC256(this.secret); //forma que faz com que os nossos hashes sejam unicos para a nossa aplicacao
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
            return token;
        }catch(JWTCreationException e){
            throw new RuntimeException("Erro ao gerar o token", e);
        }
    }



    public String validarToken(String token) {
        try {
            System.out.println("Iniciando a validação do token...");
            Algorithm algorithm = Algorithm.HMAC256(this.secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)//descriptografei o token
                    .getSubject(); //peguei o token
        }catch (JWTVerificationException e){
            return "Token invalido, crie outro porque esse já expirou.";
        }
    }

    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public Object validateToken(String token) {
        return null;
    }
}
