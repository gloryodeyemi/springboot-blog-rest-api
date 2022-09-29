package com.example.springbootblogrestapi.services;

import com.example.springbootblogrestapi.payload.PostDto;

public interface PostService {
    PostDto createPost(PostDto postDto);
}
