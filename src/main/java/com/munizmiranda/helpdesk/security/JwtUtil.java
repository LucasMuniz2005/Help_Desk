package com.munizmiranda.helpdesk.security;

import io.jsonwebtoken.Jwts;

import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    private final SecretKey chave = Jwts.SIG.HS256.key().build();
    private final long expiracaoMs = 3600000; // 1 hora

    public String gerarToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiracaoMs))
                .signWith(chave)
                .compact();
    }

    public String extrairEmail(String token) {
        return Jwts.parser()
                .verifyWith(chave)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validarToken(String token) {
        try {
            Jwts.parser().verifyWith(chave).build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}