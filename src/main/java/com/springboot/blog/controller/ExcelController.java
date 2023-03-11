package com.springboot.blog.controller;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.service.ExcelGeneratorService;
import com.springboot.blog.service.PostService;
import com.springboot.blog.service.UploadService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/Excel")
@AllArgsConstructor
@Slf4j
public class ExcelController {

    private UploadService uploadService;

    private ExcelGeneratorService excelGeneratorService;

    private PostService postService;

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> uploadPosts(@RequestParam(value = "file") MultipartFile file) throws ExecutionException,InterruptedException {
        log.info("Uploaded File : {} starts",file.getOriginalFilename());
        uploadService.UploadPosts(file);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/getAllPosts")
    @PreAuthorize("hasAnyRole('READ','ADMIN')")
    public HttpEntity<byte[]> getAllPosts() throws  IOException {
        log.info("Export of All Posts Started");
        List<PostDto> postDto = postService.getAllPosts();
        byte[] content = excelGeneratorService.generateExcelFile(postDto);
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application","vnd.openxmlformats-officedocument.spreedsheetml.sheet"));
        headers.set(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"All_Posts.xlsx");
        headers.setContentLength(null!=content?content.length:0);
        return new HttpEntity<>(content,headers);

    }
}
