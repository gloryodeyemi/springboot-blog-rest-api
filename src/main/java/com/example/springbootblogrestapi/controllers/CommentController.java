package com.example.springbootblogrestapi.controllers;

import com.example.springbootblogrestapi.payload.CommentDto;
import com.example.springbootblogrestapi.services.CommentService;
import com.example.springbootblogrestapi.utils.AppConstants;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable Long postId, @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable Long postId){
        return commentService.getCommentsByPostId(postId);
    }

    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable Long postId, @PathVariable(value = "id") Long commentId){
        CommentDto commentDto = commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }
//
    @PutMapping("/posts/{postId}/comments/{id}")
        public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto,
                                                        @PathVariable(value = "id") Long commentId,
                                                        @PathVariable Long postId){
        CommentDto commentResponse = commentService.updateComment(postId, commentId, commentDto);
        return new ResponseEntity<>(commentResponse, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{id}")
    public  ResponseEntity<String> deleteComment(@PathVariable Long postId, @PathVariable Long id){
        commentService.deleteComment(postId, id);
        return new ResponseEntity<>("Comment deleted successfully.", HttpStatus.OK);
    }
}
