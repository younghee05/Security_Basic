package com.study.SpringSecurity.domain.entity;

import com.study.SpringSecurity.security.principal.PrincipalUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Builder
public class User {

    @Id
    // GeneratedValue를 써야 Autowired를 쓸 수 있음
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false) // nullable: null 을 허용하지 않겠다
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;

    // fetch : 엔티티를 조언했을 때 연관된 데이터를 언제 가져올지 결정(EAGER - 당장, LAZY - 나중에 사용할 때)

    // 다 : 다(M:M) 관계
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable( // ManyToMany를 쓸 때 joinTable이 필요
            name = "user_roles",  // 테이블
            joinColumns = @JoinColumn(name = "user_id"), // join 키값 user_id
            inverseJoinColumns = @JoinColumn(name = "role_id")  // join할 때 쓰이는 키값
    )
    private Set<Role> roles; // 중복이 나올 관계 때문에 방지 차원에서 Set 이 쓰임
                            // (ex.1:1 = 1:1) => user_id = 1 일때 role_id = 1을 가지고 role_id = 1 일때 user_id = 1을 갖는
                           //  관계가 같음(중복이 됨)

//    @OneToMany(mappedBy = "user") // "user" UserRole에 있는 데이터 값을 똑같이 써야함
//    private Set<UserRole> userRoles = new HashSet<>(); // 초기화

    public PrincipalUser toPrincipalUser() {
        return PrincipalUser.builder()
                .userId(id)
                .username(username)
                .password(password)
                .roles(roles)
                .build();
    }


}
