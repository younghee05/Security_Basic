package com.study.SpringSecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    // get 요청때는 requestBody로 받지 않음
    @GetMapping("/test")
    public ResponseEntity<?> get() {
        System.out.println("요청들어옴????");

        return ResponseEntity.ok("확인");
    }
}
