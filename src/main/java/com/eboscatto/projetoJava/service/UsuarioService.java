package com.eboscatto.projetoJava.service;

import com.eboscatto.projetoJava.dto.UsuarioDTO;
import com.eboscatto.projetoJava.model.Role;
import com.eboscatto.projetoJava.model.Usuario;
import com.eboscatto.projetoJava.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
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
        novo.setRole(Role.USER);
        return usuarioRepository.save(novo);
    }

    // boas práticas recomendam não retornar entidades direto no controller
    public List<UsuarioDTO> buscasrTodosDTOs() {
        return usuarioRepository.findAll().stream()
                .map(usuario -> new UsuarioDTO(
                        usuario.getId(),
                        usuario.getUsername(),
                        usuario.getRole().name()
                ))
                .toList();
    }
    public Usuario buscarPorUsername(String username) {
        return usuarioRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
    }

}
