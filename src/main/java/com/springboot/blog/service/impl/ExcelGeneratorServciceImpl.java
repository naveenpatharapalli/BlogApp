package com.springboot.blog.service.impl;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.entity.Post;
import com.springboot.blog.service.ExcelGeneratorService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ExcelGeneratorServciceImpl implements ExcelGeneratorService {

    XSSFWorkbook workbook = new XSSFWorkbook();
    private XSSFSheet sheet;


    @Override
    public byte[] generateExcelFile(List<PostDto> postDto) throws IOException {
        byte[] bytes = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        writeHeader();
        write(postDto);
        workbook.write(outputStream);
        bytes = outputStream.toByteArray();
        return bytes;
    }

    private void writeHeader(){
        sheet = workbook.createSheet("posts");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row,0,"Id",style);
        createCell(row,1,"Title",style);
        createCell(row,2,"Description",style);
        createCell(row,3,"Content",style);
    }

    private void createCell(Row row,int columnCount,Object valueOfCell,CellStyle style){
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else {
            cell.setCellValue((Boolean) valueOfCell);
        }
        cell.setCellStyle(style);
    }

    private void write(List<PostDto> postDtoList){

        int rowCount = 1;
        CellStyle cellStyle = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(false);
        font.setFontHeight(14);
        cellStyle.setFont(font);
        for (PostDto record:postDtoList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row,columnCount++,record.getId(),cellStyle);
            createCell(row,columnCount++,record.getTitle(),cellStyle);
            createCell(row,columnCount++,record.getDescription(),cellStyle);
            createCell(row,columnCount++,record.getContent(),cellStyle);
        }
    }



}
