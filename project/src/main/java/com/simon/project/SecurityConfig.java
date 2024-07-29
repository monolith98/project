package com.simon.project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    함수(){
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf)->csrf.disable());
        http.authorizeHttpRequests((authorize) ->
                authorize
                        .requestMatchers("/write").authenticated() // /write 경로는 인증된 사용자만 접근 가능
                        .requestMatchers("/**").permitAll() // 그 외의 경로는 모두 접근 가능
        );
        http.formLogin((formLogin) ->
                formLogin.loginPage("/login").defaultSuccessUrl("/")
        );
        http.logout(logout -> logout.logoutUrl("/logout"));

        return http.build();
    }


}
