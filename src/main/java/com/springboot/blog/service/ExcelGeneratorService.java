package com.springboot.blog.service;

import com.springboot.blog.dto.PostDto;

import java.io.IOException;
import java.util.List;

public interface ExcelGeneratorService {

    byte[] generateExcelFile(List<PostDto> postDto) throws IOException;
}
