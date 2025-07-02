package com.eboscatto.projetoJava.controller;

import com.eboscatto.projetoJava.model.Usuario;
import com.eboscatto.projetoJava.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth/")
public class AuthController {

    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {

        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        // Exemplo simples de verificação fixa
        if ("admin".equals(usuario.getUsername()) && "123".equals(usuario.getSenha())) {
            String token = jwtService.gerarToken(usuario.getUsername());
            return ResponseEntity.ok(Map.of("token", token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}