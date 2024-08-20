package com.study.SpringSecurity.service;

import com.study.SpringSecurity.dto.request.ReqSignupDto;
import com.study.SpringSecurity.repository.UserRepository;
import com.study.SpringSecurity.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignupService {

    @Autowired
    private UserRepository userRepository;

    public User signup(ReqSignupDto dto) {
        return userRepository.save(dto.toEntity());
    }
}
