package com.simon.project.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Setter
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false, unique = true, length = 255)
    private String username;
    @Column(columnDefinition = "TEXT", nullable = false, length = 255)
    private String password;
    private String displayName;
}
