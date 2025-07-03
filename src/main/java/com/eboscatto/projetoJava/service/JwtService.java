package com.eboscatto.projetoJava.service;

import com.eboscatto.projetoJava.config.TokenProperties;
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
   /* private final String chave = "6Ld@QmE9rV#zU2!zKdPf8YxRbWpL@x7m"; // mínimo 256 bits */

    private final TokenProperties props;

    public JwtService(TokenProperties props) {
        this.props = props;
    }

    public String gerarToken(String username) {
        String segredo = props.getSecret();
        Key key = Keys.hmacShaKeyFor(segredo.getBytes());

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    public String validarToken(String token) {
        Key key = Keys.hmacShaKeyFor(props.getSecret().getBytes());

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
