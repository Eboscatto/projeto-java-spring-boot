package com.eboscatto.projetoJava.dto;

import lombok.Getter;

public class UsuarioDTO {
    @Getter
    private Long id;
    @Getter
    private String username;
    private String role;

    public UsuarioDTO(Long id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    // Getters e Setters a cargo do Lombok

}
