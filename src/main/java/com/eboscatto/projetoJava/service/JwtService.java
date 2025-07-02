package com.eboscatto.projetoJava.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JwtService {
    private final String chave = "6Ld@QmE9rV#zU2!zKdPf8YxRbWpL@x7m"; // mínimo 256 bits

    public String gerarToken(String username) {
        Key key = Keys.hmacShaKeyFor(chave.getBytes());

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)))
                .signWith(Keys.hmacShaKeyFor(chave.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }
    public String validarToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(chave.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
