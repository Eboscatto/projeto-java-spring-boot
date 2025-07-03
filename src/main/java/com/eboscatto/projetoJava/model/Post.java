package com.eboscatto.projetoJava.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "O userId é obrigatório.")
    private int userId;

    @NotBlank(message = "O título não pode estar em branco.")
    private String title;

    @NotBlank(message = "O corpo do post não pode estar vazio.")
    private String body;

    // Gertters e Setters

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void SetTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return  body;
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
