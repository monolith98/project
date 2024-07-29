package com.simon.project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class BasicController {
    @GetMapping("/")
    String hello(){
        //return "폴더명/index.html"; // 경로를 따로 지정할 때
        return "index.html"; // 기본경로는 static 폴더
    }

    @GetMapping("/about")
    @ResponseBody
    String about(){
        return "낚시사이트";
    }

    LocalDateTime currentDateTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd E HH:mm:ss");
    String formattedDateTime = currentDateTime.format(formatter);

    @GetMapping("/date")
    @ResponseBody
    String date(){
        return ("현재 날짜와 시간 : " + formattedDateTime);
    }
}
