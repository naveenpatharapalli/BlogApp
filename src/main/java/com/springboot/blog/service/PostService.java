package com.springboot.blog.service;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.dto.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);
    List<PostDto> getAllPosts();
    PostResponse getAllPosts(int pageNo, int pageSize);
    PostResponse getAllPosts(int pageNo, int pageSize,String sortBy);
    PostResponse getAllPosts(int pageNo, int pageSize,String sortBy,String sortDir);
    PostDto getPostById(long id);
    PostDto updatePostById(PostDto postDto,long id);
    void deletePost(long id);
}
