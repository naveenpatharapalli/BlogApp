package com.springboot.blog.exception;

public class ExcelUploadException extends RuntimeException{

    public ExcelUploadException(String message,Exception exception){
        super(message,exception);
    }
}
