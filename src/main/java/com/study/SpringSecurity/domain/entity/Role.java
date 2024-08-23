package com.study.SpringSecurity.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 권한
// 하나의 user의 여러 값(데이터)이 들어가도록
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name; // ROLE_USER, ROLE_ADMIN, ROLE_MANAGER 이런식으로 잡힌다

//    @OneToMany(mappedBy = "role")
//    private Set<UserRole> userRoles = new HashSet<>();

//    @ManyToMany(mappedBy = "roles")
//    private List<User> users;
    // 아이디 관계
//    @OneToMany(mappedBy = "roles")
//    private Set<User> users;
}
