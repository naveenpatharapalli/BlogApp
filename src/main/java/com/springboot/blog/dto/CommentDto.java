package com.springboot.blog.dto;

import com.springboot.blog.entity.Post;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {

    private long id;
    @NotEmpty(message = "Name should not be empty or null")
    private String name;

    @NotEmpty(message = "Email should not be empty or null")
    @Email(message = "Email is not valid")
    private String email;

    @NotEmpty
    @Size(min = 10, message = "body should be more than 10 chars")
    private String body;
    //private Post post;
}
