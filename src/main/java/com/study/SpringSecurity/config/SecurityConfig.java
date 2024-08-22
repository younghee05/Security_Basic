package com.study.SpringSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity // 우리가 만든 SecurityConfig를 적용시키겠다.
@Configuration // Bean 등록 가능 / annotation 사용 불가
public class SecurityConfig extends WebSecurityConfigurerAdapter { // 추상클래스를 상속 시킴

    // annotation 사용 불가하므로 bean을 써서 BCryptPasswordEncoder를 생성한다
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // protected : 해당 패키지 안에서 클래스끼리만 쓸 수 있다
    // 단, 상속 받았으면 어디서든 쓸 수 있다
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().disable();
        http.httpBasic().disable();
        // 위조 방지 스티커 (토큰)
        http.csrf().disable();
//        http.sessionManagement().disable();
        // 스프링 시큐리티가 세션을 생성하지도 않고 기존의 세션을 사용하지도 않겠다는 뜻
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 스프링 시큐리티가 생성하지 않겠다. 기존의 센션을 완전히 사용하지 않겠다는 뜻은 아님.
        // JWT 등의 토큰 인증방식을 사용할 때 설정하는 것.

        http.cors(); // 다른 서버에서 이런 메소드일때 / url 일때 연결 요청을 보내겠다 라는 뜻 / 요청만 보냄 응답 x
        http.authorizeRequests()
                .antMatchers("/auth/**", "/h2-console/**", "/test/**") // 주소를 골라줄 수 있다
                .permitAll()
                .anyRequest() // 키값 인증 요청
                .authenticated() // 인증 허용
                .and()
                .headers()
                .frameOptions()
                .disable();

    }
}
