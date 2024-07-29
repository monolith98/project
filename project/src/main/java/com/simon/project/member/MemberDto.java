package com.simon.project.member;

public class MemberDto {
    public String username;
    public String displayName;
    public Long id;
    MemberDto(String a, String b, Long id){
        this.username = a;
        this.displayName =b;
        this.id = id;
    }
}
