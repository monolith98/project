package com.simon.project.item;

import com.simon.project.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Setter
@Getter
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private Integer price;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String member;

    @Column(columnDefinition = "TEXT")
    private String imageUrl;

}