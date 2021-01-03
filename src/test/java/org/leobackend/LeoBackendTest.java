package org.leobackend;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * Created on 2020-12-26.
 * 测试类而已
 *
 * @author Leo
 */
public class LeoBackendTest {

//    @Test
    public void crateToken () {
        /*
            iss: jwt签发者
            sub: jwt所面向的用户
            aud: 接收jwt的一方
            exp: jwt的过期时间，这个过期时间必须要大于签发时间
            nbf: 定义在什么时间之前，该jwt都是不可用的.
            iat: jwt的签发时间
            jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。
        */
        String token = JWT.create()
                .withIssuer("leobackend")
                .withIssuedAt(new Date())
                .withSubject("leo")
                .sign(Algorithm.HMAC256("123456"));

        System.out.println(token);
    }

    @Test
    public void verifierToken () {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3N1ZWREYXRlIjoxNjA5MDYxNzA3LCJpc3N1ZXIiOiJsZW9iYWNrZW5kLm9yZyIsInVzZXJuYW1lIjoibGVvIn0.8wPWwAzzHQPMXfUsZadkbYEw-Qsflw7g0yS9tk53mRM";

        DecodedJWT decodedJWT = JWT.decode(token);
        String username = decodedJWT.getClaim("username").asString();
        // 获取用户密码
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("63a9f0ea7bb98050796b649e85481845"))
                .withClaim("issuer", "leobackend.org")
                .withClaim("username", username)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        System.out.println(1);
    }

    @Test
    public void getMd5 () {
        String src = "root";
        try {
            byte[] md5s = MessageDigest.getInstance("MD5").digest(src.getBytes());
            String md5 = new BigInteger(md5s).toString(16);
            System.out.println(md5);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

}
