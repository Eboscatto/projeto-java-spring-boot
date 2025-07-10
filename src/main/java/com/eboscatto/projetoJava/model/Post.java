package com.eboscatto.projetoJava.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Getter
@Entity
@Table(name = "post")
public class Post {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Setter
    @NotNull(message = "O userId é obrigatório.")
    private int userId;

    @NotBlank(message = "O título não pode estar em branco.")
    private String title;

    @NotBlank(message = "O corpo do post não pode estar vazio.")
    private String body;

    // Gertters e Setters

    public void SetTitle(String title) {
        this.title = title;
    }

    public void SetBody(String body) {
        this.body = body;
    }
    @Override
    public String toString() {
        return "Post {" +
                ", userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
