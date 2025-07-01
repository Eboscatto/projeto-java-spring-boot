package com.eboscatto.projetoJava.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Post {

    @NotNull(message = "O userId é obrigatório.")
    private int userId;
    private int id;

    @NotBlank(message = "O título não pode estar em branco.")
    private String title;

    @NotBlank(message = "O corpo do post não pode estar vazio.")
    private String body;

    // Gertters e Setters
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
