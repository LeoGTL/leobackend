package org.leobackend.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.leobackend.entity.TokenEntity;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created on 2020-12-26.
 * token相关工具类
 *
 * @author Leo
 */
public class TokenUtils {

    private static final String ISSUER = "leobackend.org";

    /**
     * 创建Token
     * @param username
     * @param passwd
     * @return
     */
    public static String createToken (String username, String password) {
        String token = JWT.create()
                .withClaim("issuer", ISSUER)
                .withClaim("issuedDate", new Date())
                .withClaim("username", username)
                .sign(Algorithm.HMAC256(password));
        return token;
    }

    /**
     * 解析token
     * @param token
     * @param username
     * @param password
     * @return
     */
    public static TokenEntity verifyToken (String token) {
        try {
            TokenEntity tokenEntity = new TokenEntity();
            DecodedJWT decodedJWT = JWT.decode(token);
            String username = decodedJWT.getClaim("username").asString();
            Date issuedDate = decodedJWT.getClaim("issuedDate").asDate();
            // 获取密码
            List<Map<String, Object>> resultList = DbManager.getInstance().query ("select id,username,password,nickname,tel,email from t_sys_user where username=?",
                    new String[]{"id","username","password","nickname","tel","email"}, new String[]{username});
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256((String) resultList.get(0).get("password")))
                    .withClaim("issuer", ISSUER)
                    .withClaim("username", username)
                    .withClaim("issuedDate", issuedDate)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            BeanUtils.copyProperties(jwt, tokenEntity);
            return tokenEntity;
        } catch (Exception e) {
            System.out.println("token验证失败");
        }
        return null;
    }

}
