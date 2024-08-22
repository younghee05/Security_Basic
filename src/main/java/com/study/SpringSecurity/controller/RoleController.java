package com.study.SpringSecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

// 권한 부여 기능

@RestController
public class RoleController {

    private ResponseEntity<?> init() {
        return ResponseEntity.ok().body(null);
    }

}
