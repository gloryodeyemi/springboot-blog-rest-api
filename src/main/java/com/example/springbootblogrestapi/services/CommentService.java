package com.example.springbootblogrestapi.services;

import com.example.springbootblogrestapi.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(Long postId, CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(Long postId);

    CommentDto getCommentById(Long postID, Long id);

    CommentDto updateComment(Long postId, Long id, CommentDto commentRequest);

    void deleteComment(Long postId, Long id);
}
