package com.simon.project.info;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class infoController {

    private final infoRepository infoRepository;

    @GetMapping("/info")
    String list(Model model){
        List<info> info = infoRepository.findAll();
        model.addAttribute("info",info);

        var a = new info();
        a.setAge(250);
        System.out.println(a);
        a.setAge(-25);
        System.out.println(a);
        a.setAge(25);
        System.out.println(a);
        a.plusOneAge();
        System.out.println(a);



        return "info.html";
    }

    @GetMapping("/index")
    String index(Model model){
        return "index.html";
    }

}
