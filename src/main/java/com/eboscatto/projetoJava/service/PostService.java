package com.eboscatto.projetoJava.service;

import com.eboscatto.projetoJava.model.Post;
import com.eboscatto.projetoJava.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final PostRepository repo;

    public PostService(PostRepository repo) {
        this.repo = repo;
    }

    public List<Post> listar() {
        return repo.findAll();
    }

    public Post criar(Post post) {
       return repo.save(post);
    }
}
