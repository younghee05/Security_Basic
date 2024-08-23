package com.study.SpringSecurity.security.principal;

import com.study.SpringSecurity.domain.entity.Role;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Data
public class PrincipalUser implements UserDetails {

    private Long userId;
    private String username;
    private String password;
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        // map을 안쓸 경우
//        Set<GrantedAuthority> authorities = new HashSet<>();
//        for(Role role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
//        }
//        return authorities;

        // map을 사용하여 strema 안에 role을 꺼내 GrantedAuthority 로 변환
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isAccountNonExpired() { // 만료된 계정 / 휴먼계정
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { // 잠긴 계정 / ex. 5번 이상 틀렸다면 계정이 잠김
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { // 인증이 만료된 경우
        return true;
    }

    @Override
    public boolean isEnabled() { // 계정 활성화 비활성화
        return true;
    }
}
