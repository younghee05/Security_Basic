package com.study.SpringSecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity // 우리가 만든 SecurityConfig를 적용시키겠다.
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter { // 추상클래스를 상속 시킴

    @Override
    // protected : 해당 패키지 안에서 클래스끼리만 쓸 수 있다
    // 단, 상속 받았으면 어디서든 쓸 수 있다
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

        http.cors();
        http.authorizeRequests()
                // 주소를 골라줄 수 있다
                .antMatchers("/auth/**")
                .permitAll()
                .anyRequest()
                .authenticated();

    }
}
