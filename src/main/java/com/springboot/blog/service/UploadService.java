package com.springboot.blog.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
     void UploadPosts(MultipartFile file);
}
