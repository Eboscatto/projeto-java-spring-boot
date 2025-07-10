package com.eboscatto.projetoJava.service;

import com.eboscatto.projetoJava.config.TokenProperties;
import com.eboscatto.projetoJava.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.management.relation.Relation;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
   /* private final String chave = "6Ld@QmE9rV#zU2!zKdPf8YxRbWpL@x7m"; // mínimo 256 bits */

    private final TokenProperties props;
    private Relation usuario;
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(props.getSecret().getBytes(StandardCharsets.UTF_8));
    }


    public JwtService(TokenProperties props) {
        this.props = props;
    }

    public String gerarToken(Usuario usuario) {
        String segredo = props.getSecret();
        Key key = Keys.hmacShaKeyFor(segredo.getBytes());

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", usuario.getRole().name()); // ou .toString(), dependendo do tipo

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(usuario.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // ex: 1 hora
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
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

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenValido(String token, Usuario usuario) {
        final String username = extractUsername(token);
        return username.equals(usuario.getUsername()) && !isTokenExpirado(token);
    }


    public boolean isTokenExpirado(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractRole(String token) {
        return extractClaim(token, claims -> claims.get("role").toString());
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = parseToken(token);
        return claimsResolver.apply(claims);
    }
    private Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

}
