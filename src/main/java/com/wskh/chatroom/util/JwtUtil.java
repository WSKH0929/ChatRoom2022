package com.wskh.chatroom.util;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Data
public class JwtUtil {
    private static final String jwtToken = "!@#$%wskh^&*()_+";

    public String createToken(String userId){
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        // 过期时间（毫秒）
        Long lossTime = 3L*3600L*1000L;
        JwtBuilder jwtBuilder = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,jwtToken) // 签发算法，秘钥为jwtToken
                .setClaims(map) // body数据，要唯一，自行设置
                .setIssuedAt(new Date()) // 设置签发时间
                .setExpiration(new Date(System.currentTimeMillis()+lossTime)); // 设置过期时间
        String token = jwtBuilder.compact();
        return token;
    }

    public Map<String,Object> checkToken(String token){
        try {
            Jwt parse = Jwts.parser().setSigningKey(jwtToken).parse(token);
            return (Map<String, Object>) parse.getBody();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
