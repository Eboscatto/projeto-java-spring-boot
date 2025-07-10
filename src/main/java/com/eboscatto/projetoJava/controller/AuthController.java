package com.eboscatto.projetoJava.controller;

import com.eboscatto.projetoJava.dto.UsuarioDTO;
import com.eboscatto.projetoJava.model.Usuario;
import com.eboscatto.projetoJava.service.JwtService;
import com.eboscatto.projetoJava.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;
    private final UsuarioService usuarioService;

    public AuthController(JwtService jwtService, UsuarioService usuarioService) {
        this.jwtService = jwtService;
        this.usuarioService = usuarioService;
    }

    // login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario loginRequest) {
        return usuarioService.autenticar(loginRequest.getUsername(), loginRequest.getSenhaCriptografada())
                .map(usuario -> {
                    String token = jwtService.gerarToken(usuario);
                    return ResponseEntity.ok(Map.of("token", token));
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("erro", "Usuário ou senha inválidos")));
    }

    // cadastro
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Usuario novoUsuario) {
        Usuario criado = usuarioService.criarUsuario(novoUsuario.getUsername(), novoUsuario.getSenhaCriptografada());
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    // usuário autenticado
    @GetMapping("/me")
    public ResponseEntity<?> me(Authentication authentication) {
        String username = authentication.getName();

        return ResponseEntity.ok(Map.of(
                "usuario", username,
                "mensagem", "Usuário autenticado com sucesso 🎉"
        ));
    }

    // listar todos os usuários (ADMIN)
    @GetMapping("/usuarios")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
          List<UsuarioDTO> usuarios = usuarioService.buscasrTodosDTOs();
          return ResponseEntity.ok(usuarios);
    }
}
