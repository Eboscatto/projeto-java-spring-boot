package com.eboscatto.projetoJava.service;

import com.eboscatto.projetoJava.model.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private List<Post> posts = new ArrayList<>();
    private int nextId = 1;

    public List<Post> listar() {
        return posts;
    }

    public Post criar(Post post) {
        post.setId(nextId++);
        posts.add(post);
        return post;
    }
}
