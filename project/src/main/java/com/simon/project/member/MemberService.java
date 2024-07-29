package com.simon.project.member;


import com.simon.project.MyExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void signup(String username,String password,String displayName){

        Member member = new Member();
        var hashpassword = new BCryptPasswordEncoder().encode(password);
        
        if(username.length() > 10){
            // 아이디 10자 이상
            throw new RuntimeException("아이디는 10자 이상으로 해주세요.");
        }

        if(password.length() < 12){
            // 비밀번호 12자 이상
            throw new RuntimeException("비밀번호는 12자 이상으로 해주세요.");
        }


        member.setUsername(username);
        member.setPassword(hashpassword);
        member.setDisplayName(displayName);

        memberRepository.save(member);
    }

    public MemberDto userinfo(){
        var user = memberRepository.findById(1L);
        var result = user.get();
        var info = new MemberDto(result.getUsername(),result.getDisplayName(),result.getId());
        return info;
    }
}
