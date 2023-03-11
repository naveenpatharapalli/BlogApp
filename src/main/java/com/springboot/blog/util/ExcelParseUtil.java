package com.springboot.blog.util;

import lombok.experimental.UtilityClass;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

@UtilityClass
public class ExcelParseUtil {

    public static String getStringValueFromCell(XSSFRow row,int column){
        XSSFCell cell = row.getCell(column);
        if(cell==null){
            return null;
        }

        if(cell.getCellType()== Cell.CELL_TYPE_NUMERIC){
            return cell.getRawValue();
        }

        return cell.getStringCellValue().isEmpty() ? null : cell.getStringCellValue();
    }
}
