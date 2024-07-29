package com.simon.project.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;


    @GetMapping("/register")
    public String register(Authentication auth){
        if(auth.isAuthenticated() == true){
            return "redirect:/list/page/1";
        } else {
            return "register.html";
        }
    }

    @PostMapping("/member")
    public String signup(String username,
                  String password,
                  String displayName){
        // 유저가 보낸 아이디 비번 이름 DB에 저장
        memberService.signup(username,password,displayName);
        return "redirect:/register";
    }

    @GetMapping("/login")
    public String login(){
        return "login.html";
    }

    @GetMapping("/my-page")
    public String myPage(Authentication auth){
        CustomUser result = (CustomUser) auth.getPrincipal();
        System.out.println(result.displayName);
//        String className = auth.getClass().getName().toString();
//        System.out.println(String.valueOf(className));
//        System.out.println(className);
//        System.out.println(auth.getAuthorities().contains(
//        new SimpleGrantedAuthority("일반 유저")
//        ));

        if (auth.isAuthenticated() == true){
            return "mypage.html";
        } else {
            return "login.html";
        }

    }

    @GetMapping("/user/1")
    @ResponseBody
    public MemberDto getUser(String username,
                             String displayName,
                             Long id){
        return memberService.userinfo();
    }

}

