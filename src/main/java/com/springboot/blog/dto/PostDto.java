package com.springboot.blog.dto;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {

    private long Id;

    @NotEmpty
    @Size(min = 2, message = "Post Title Should have atleast 2 Chars")
    private String title;

    @NotEmpty
    @Size(min = 10, message = "Post Description should have atleast 10 chars")
    private String description;

    @NotEmpty
    private String content;
    private Set<CommentDto> comments;

}
