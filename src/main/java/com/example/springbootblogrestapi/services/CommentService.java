package com.example.springbootblogrestapi.services;

import com.example.springbootblogrestapi.payload.CommentDto;

public interface CommentService {
    CommentDto createComment(Long postId, CommentDto commentDto);
}
