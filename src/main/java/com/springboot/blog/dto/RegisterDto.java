package com.springboot.blog.dto;

import com.springboot.blog.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

    private String name;
    private String userName;
    private String email;
    private String password;
    private Set<Role> roles;
}
