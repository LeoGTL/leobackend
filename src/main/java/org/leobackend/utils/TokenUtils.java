package org.leobackend.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.leobackend.entity.TokenEntity;
import org.springframework.beans.BeanUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
     * @param username 用户名
     * @param password 密码
     * @return token
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
     * @param token token
     * @return TokenEntity
     */
    public static TokenEntity verifyToken (String token) {
        try {
            TokenEntity tokenEntity = new TokenEntity();
            DecodedJWT decodedJWT = JWT.decode(token);
            String username = decodedJWT.getClaim("username").asString();
            Date issuedDate = decodedJWT.getClaim("issuedDate").asDate();
            // 获取密码
            List<Map<String, Object>> resultList = DBUtils.getInstance().select ("select id,username,password,nickname,tel,email,token from t_sys_user where username=?",
                    new String[]{"id","username","password","nickname","tel","email","token"}, new String[]{username});
            Map<String, Object> row = resultList.get(0);
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256((String) row.get("password")))
                    .withClaim("issuer", ISSUER)
                    .withClaim("username", username)
                    .withClaim("issuedDate", issuedDate)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            BeanUtils.copyProperties(jwt, tokenEntity);
            if (!token.equals(row.get("token"))) {
                System.out.println("当前token已失效");
                return null;
            }
            return tokenEntity;
        } catch (Exception e) {
            System.out.println("token不合法");
        }
        return null;
    }

    /**
     * 获取字符串md5
     * @param src 源串
     * @return md5
     */
    public static String getMd5 (String src) {
        try {
            byte[] md5s = MessageDigest.getInstance("MD5").digest(src.getBytes());
            String md5 = new BigInteger(md5s).toString(16);
            return md5;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

}
