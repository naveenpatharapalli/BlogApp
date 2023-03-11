package com.springboot.blog.service.impl;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ExcelUploadException;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.UploadService;
import lombok.AllArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.springboot.blog.util.ExcelParseUtil.getStringValueFromCell;

@Service
@AllArgsConstructor
public class UploadServicveImpl implements UploadService {

    private PostRepository postRepository;

    private ModelMapper mapper;



    @Override
    public void UploadPosts(MultipartFile file) {
        try{

            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet sheet = workbook.getSheetAt(0);
            List<PostDto> postDtos = parseExcel(sheet);

            List<Post> posts = postDtos.stream()
                    .filter(po -> po.getTitle() != null)
                    .map(po -> toPost(po))
                    .collect(Collectors.toList());

            postRepository.saveAll(posts);

        }catch(Exception e){
            throw new ExcelUploadException("Issue with the Input file ", e);
        }
    }

    public Post toPost(PostDto postDto){
        Post post = mapper.map(postDto, Post.class);
        return post;
    }

    private List<PostDto> parseExcel(XSSFSheet sheet){
        return IntStream.rangeClosed(1, sheet.getLastRowNum()).mapToObj(i->convert(sheet.getRow(i))).collect(Collectors.toList());
    }

    protected PostDto convert(XSSFRow row){
        return PostDto.builder().title(getStringValueFromCell(row,0))
                .description(getStringValueFromCell(row,1))
                .content(getStringValueFromCell(row,2))
                .build();
    }
}
