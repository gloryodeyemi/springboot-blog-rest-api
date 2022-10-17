package com.example.springbootblogrestapi.services.impl;

import com.example.springbootblogrestapi.entities.Comment;
import com.example.springbootblogrestapi.entities.Post;
import com.example.springbootblogrestapi.exceptions.BlogAPIException;
import com.example.springbootblogrestapi.exceptions.ResourceNotFoundException;
import com.example.springbootblogrestapi.payload.CommentDto;
import com.example.springbootblogrestapi.repositories.CommentRepository;
import com.example.springbootblogrestapi.repositories.PostRepository;
import com.example.springbootblogrestapi.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    CommentRepository commentRepository;
    PostRepository postRepository;

    private ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {
        Comment comment =  mapToEntity(commentDto);
        Post post = getPost(postId);
        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);
        return mapToDto(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long id) {
        Post post = getPost(postId);
        Comment comment = getComment(id);
        checkCommentBelongToPost(comment, post);

        return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(Long postId, Long id, CommentDto commentRequest) {
        Post post = getPost(postId);
        Comment comment = getComment(id);
        checkCommentBelongToPost(comment, post);

        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());

        Comment updatedComment = commentRepository.save(comment);
        return mapToDto(updatedComment);
    }

    @Override
    public void deleteComment(Long postId, Long id) {
        Post post = getPost(postId);
        Comment comment = getComment(id);
        checkCommentBelongToPost(comment, post);
        commentRepository.deleteById(id);
    }

    private CommentDto mapToDto(Comment comment){
        CommentDto commentDto = mapper.map(comment, CommentDto.class);
//        commentDto.setId(comment.getId());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setBody(comment.getBody());
        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = mapper.map(commentDto, Comment.class);
//        comment.setId(commentDto.getId());
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
//        comment.setBody(commentDto.getBody());
        return comment;
    }

    private Post getPost(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));
        return post;
    }

    private Comment getComment(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", id));
        return comment;
    }

    private void checkCommentBelongToPost(Comment comment, Post post){
        if (!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
    }
}
