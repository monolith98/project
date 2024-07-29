package com.simon.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);

	}

}

class Friend{
	String name = "kim";
	int age = 20;
	Friend(String name, int age){ // new Friend()를 할 때 자동 실행
		this.name = name; //  잘만 쓴다면 매번 다른 변수값으로
		this.age = age; // object를 뽑을 수 있다.
	}
}
