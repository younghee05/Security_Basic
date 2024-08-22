package com.study.SpringSecurity.controller;

import com.study.SpringSecurity.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    // get 요청때는 requestBody로 받지 않음
    @GetMapping("/test")
    public ResponseEntity<?> get() {

        // 여기가 실행이 되면 aspect가 실행된다 / 실행이 될 때 전처리 후처리가 찍히는 것
        System.out.println(testService.aopTest()); // aopTest라는 메소드를 저장
        testService.aopTest2("김영희", 20);
        testService.aopTest3("010-1234-5678", "창원시 진해구");

        return ResponseEntity.ok("확인");
    }
}
