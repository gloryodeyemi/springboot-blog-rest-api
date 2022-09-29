package com.example.springbootblogrestapi.controllers;

import com.example.springbootblogrestapi.payload.CommentDto;
import com.example.springbootblogrestapi.payload.PostDto;
import com.example.springbootblogrestapi.payload.PostResponse;
import com.example.springbootblogrestapi.services.CommentService;
import com.example.springbootblogrestapi.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
//
//    @GetMapping()
//    public PostResponse getAllPosts(
//            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
//            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
//            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
//            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir){
//        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<PostDto> getPostById(@PathVariable Long id){
//        return ResponseEntity.ok(postService.getPostById(id));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Long id){
//        PostDto postResponse = postService.updatePost(postDto, id);
//        return new ResponseEntity<>(postResponse, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    public  ResponseEntity<String> deletePost(@PathVariable Long id){
//        postService.deletePost(id);
//        return new ResponseEntity<>("Post entity deleted successfully.", HttpStatus.OK);
//    }
}
