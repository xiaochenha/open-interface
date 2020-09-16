package com.example.apiAuth.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.util.Date;

public class OpenInterfaceUtil {

    public static final String JWT_SECRET = "E8hL94fTQd3!X9QnhVcuHh8G3h1m@y!L";
    public static Long TOKEN_EXPIRE_TIME = 60 * 1000L;

    public static String sign(String magicStr) {
        Date date = new Date(System.currentTimeMillis() + TOKEN_EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(magicStr + JWT_SECRET);
        return JWT.create()
                .withExpiresAt(date)
                .sign(algorithm);
    }

    public static boolean verify(String token,String magicStr) {
        try {
            // 根据密码生成JWT效验器
            Algorithm algorithm = Algorithm.HMAC256(magicStr + JWT_SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            // 效验TOKEN
            verifier.verify(token);

            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

    public static void main(String[] args) {
        String signResult = sign("abcd");
        System.out.println(signResult);
    }

}
