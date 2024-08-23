package com.study.SpringSecurity.security.jwt;

import com.study.SpringSecurity.domain.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {

    private final Key key;

    public JwtProvider(@Value("${jwt.secret}") String secret) {
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public String removeBearer(String token) {
        return token.substring("Bearer ".length());
    }

    public String generateUserToken(User user) {

        // 시간 단위로 date 값을 가지고 온다 /                  달로 표현
        Date expireDate = new Date(new Date().getTime() + (1000l * 60 * 60 * 24 * 30));
        // token의 유효기간을 1달(30일)로 잡는다는 뜻

        // jwt : Jason Wep Token
        // jwp 토큰에는 user_Id 만이 들어가야한다 // 나머지 정보들은 해킹당하기 쉽기 때문
        String token = Jwts.builder()
                .claim("userId", user.getId())
                .expiration(expireDate)
                .signWith(key, SignatureAlgorithm.HS256) // HS256으로 암호화
                .compact(); // compact : build method 와 같다

        return token;
    }

    public Claims parseToken(String token) {
        JwtParser jwtParser = Jwts.parser()
                .setSigningKey(key)
                .build();

        return jwtParser.parseClaimsJws(token).getPayload();
    }
}
