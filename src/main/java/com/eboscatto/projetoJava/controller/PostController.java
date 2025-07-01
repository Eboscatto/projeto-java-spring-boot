package com.eboscatto.projetoJava.controller;

import com.eboscatto.projetoJava.model.Post;
import com.eboscatto.projetoJava.service.PostService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public List<Post> listarPosts() {
        return service.listar();
    }

    @PostMapping
    public Post criarPost(@RequestBody @Valid Post post) {
        return service.criar(post);
    }
}
