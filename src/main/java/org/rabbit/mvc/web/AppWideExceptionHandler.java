package org.rabbit.mvc.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class AppWideExceptionHandler {

    @ExceptionHandler(DuplicateSpittleException.class)
    public String handleDuplicateSpittle() {
        return "error/suplicate";
    }

//    @ExceptionHandler(IOException.class)
//    public String handleIOException() {
//        return "error/ioexption";
//    }
}
