package com.simon.project.info;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@ToString
@Getter
@Setter
public class info {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    public String title;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    public Date createdAt;

    private String name;
    private Integer age;

    public Integer plusOneAge() {
        return age = age + 1;
    }

    public void setAge(Integer age) {
        if (age < 0){
            return;
        } else if (age >= 100) {
            return;
        }
        this.age = age;
    }
}
