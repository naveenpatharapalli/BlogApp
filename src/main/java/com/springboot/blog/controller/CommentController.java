package com.springboot.blog.controller;

import com.springboot.blog.dto.CommentDto;
import com.springboot.blog.entity.Comment;
import com.springboot.blog.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/posts/{postId}/comments")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@PathVariable(name = "postId") long postId, @Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId,commentDto),HttpStatus.CREATED);

    }

    @GetMapping
    public List<CommentDto> getAllCommentsByPostId(@PathVariable(name = "postId") long postId){
        return commentService.getAllcommentsByPostId(postId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(name = "postId") long postId, @PathVariable(name = "id") long commentId){
        return ResponseEntity.ok(commentService.getCommentbyId(postId,commentId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> updateCommentById(@PathVariable(name = "postId") long postId,
                                                     @PathVariable(name = "id") long commentId,
                                                     @Valid @RequestBody CommentDto commentDto){
        return ResponseEntity.ok(commentService.UpdateComment(postId,commentId,commentDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteCommentById(@PathVariable(name = "postId") long postId,
                                                     @PathVariable(name = "id") long commentId){
        commentService.deleteCommentbyId(postId,commentId);
        return ResponseEntity.ok("Deleted Sucessfully !!!!");
    }
}
