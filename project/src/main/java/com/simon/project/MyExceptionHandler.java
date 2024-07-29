package com.simon.project;

import com.simon.project.member.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.NoSuchElementException;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handler(){
        return ResponseEntity.status(400).body("에러 페이지입니다.");
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> nosuchhandler(){
        return ResponseEntity.status(404).body("해당 id에 해당하는 아이템이 존재하지 않습니다.");
    }

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handleMemberException(RuntimeException ex) {
        ModelAndView modelAndView = new ModelAndView("register.html");
        modelAndView.addObject("errorMessage", ex.getMessage());
        return modelAndView;
    }

}
