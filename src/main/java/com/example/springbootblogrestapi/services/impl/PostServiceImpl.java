package com.example.springbootblogrestapi.services.impl;

import com.example.springbootblogrestapi.entities.Post;
import com.example.springbootblogrestapi.payload.PostDto;
import com.example.springbootblogrestapi.repositories.PostRepository;
import com.example.springbootblogrestapi.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Override
    public PostDto createPost(PostDto postDto) {

//        System.out.println(postDto);
        // convert DTO to entity
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post newPost = postRepository.save(post);
//        System.out.println(newPost);

        // convert entity to DTO
        PostDto postResponse = new PostDto();
        postResponse.setId(newPost.getId());
        postResponse.setTitle(newPost.getTitle());
        postResponse.setDescription(newPost.getDescription());
        postResponse.setContent(newPost.getContent());
//        System.out.println(postResponse);

        return postResponse;
    }
}
