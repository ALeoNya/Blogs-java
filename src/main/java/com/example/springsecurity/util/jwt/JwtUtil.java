package com.example.springsecurity.util.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

/**
 * JWT工具类
 */
public class JwtUtil {
    // 设置秘钥明文
    public static final String KEY = "Vanilla";
    // 设置TOKEN存活时间
//    private static long TTL =3600000;  //1h
    private static long TTL =36000000;  //10h

    /**
     * 生成JWT
     *
     * @param id
     * @param
     * @return
     */
    public static String createJWT(String id) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey secretKey = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setId(id)  // 唯一ID
                .setSubject("")  // 主题 可以是JSON数据
                .setIssuer("sg")  // 签发者
                .setIssuedAt(now)  // 签发时间
                .signWith(SignatureAlgorithm.HS256,secretKey );  // 使用HS256对称加密算法签名, 第二个参数为秘钥
        if (TTL > 0) {
            builder.setExpiration( new Date( nowMillis + TTL));
        }
        return builder.compact();
    }
    /**
     * 解析JWT
     * @param jwtStr
     * @return
     */
    public static Claims parseJWT(String jwtStr){
        SecretKey secretKey = generalKey();
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtStr).getBody();
    }


    /**
     * 解析JWT
     * @param jwtStr
     * @return boolean
     */
    public static boolean checkToken(String jwtStr){
        SecretKey secretKey = generalKey();
        if(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtStr).getBody()!=null) {
            return true;
        }
        return false;
    }

    /**
     * 生成加密后的秘钥 secretKey
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.KEY);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }
}
