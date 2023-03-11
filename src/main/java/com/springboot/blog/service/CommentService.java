package com.springboot.blog.service;

import com.springboot.blog.dto.CommentDto;
import com.springboot.blog.entity.Comment;

import java.util.List;

public interface CommentService {

    CommentDto createComment(long postId, CommentDto commentDto);
    List<CommentDto> getAllcommentsByPostId(long postId);
    CommentDto getCommentbyId(long postId,long commentId);
    CommentDto UpdateComment(long postId, long commentId, CommentDto commentDto);
    void deleteCommentbyId(long postId, long commentId);
}
