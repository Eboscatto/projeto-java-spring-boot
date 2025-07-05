package com.eboscatto.projetoJava.service;

import com.eboscatto.projetoJava.model.Usuario;
import com.eboscatto.projetoJava.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Optional<Usuario> autenticar(String username, String senhaDigitada) {
        return usuarioRepository.findByUsername(username)
                .filter(usuario -> passwordEncoder.matches(senhaDigitada, usuario.getSenhaCriptografada()));
    }

    public Usuario criarUsuario(String username, String senhaEmTexto) {
        Usuario novo = new Usuario();
        novo.setUsername(username);
        novo.setSenhaCriptografada(passwordEncoder.encode(senhaEmTexto));
        return usuarioRepository.save(novo);
    }
}
