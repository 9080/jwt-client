package com.helon.jwtclient.util;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Helon
 * @Description:
 * @Data: Created in 2018/7/19 14:11
 * @Modified By:
 */
public class JwtHelper {

    private static Logger logger = LoggerFactory.getLogger(JwtHelper.class);

    //签名秘钥
    private final static String base64Seceret = "MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY=";
    //超时毫秒数
    private final static int expiresSecond = 1800000;

    /***
     * 生成JWT
     * @param userId
     * @param userName
     * @return
     */
    public static String generateJWT(String userId, String userName) {
        //签名方式
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowTimeMillis = System.currentTimeMillis();
        Date now = new Date(nowTimeMillis);
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Seceret);

        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        //添加构成JWT的参数
        Map<String, Object> headMap = new HashMap<>();
        headMap.put("alg", "HS256");
        headMap.put("typ", "JWT");
        JwtBuilder builder = Jwts.builder().setHeader(headMap)
                .claim("userId", userId)
                .claim("userName", userName)
                .signWith(signatureAlgorithm, signingKey);
        //添加Token过期时间
        if (expiresSecond >= 0) {
            long expMillis = nowTimeMillis + expiresSecond;
            Date expDate = new Date(expMillis);
            builder.setExpiration(expDate).setNotBefore(now);
        }
        return builder.compact();
    }

    /***
     * 解析JWT
     * @param jsonWebToken
     * @return
     */
    public static Claims parseJWT(String jsonWebToken) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(base64Seceret))
                    .parseClaimsJws(jsonWebToken).getBody();
        } catch (Exception e) {
            claims = null;
            logger.error("解析JWT失败:", e);
        }
        return claims;
    }

    /**
     * @Author: Helon
     * @Description: 校验是否有效登录
     * @param jsonWebToken
     * @Data: 2018/7/24 15:28
     * @Modified By:
     */
    public static String validateLogin(String jsonWebToken) {
        Claims claims = parseJWT(jsonWebToken);
        Map<String, Object> retMap = null;
        if (claims != null) {
            retMap = new HashMap<>();
            retMap.put("userId", claims.get("userId"));
            retMap.put("userName", claims.get("userName"));
        }
        return retMap!=null?JSONObject.toJSONString(retMap):null;
    }

    public static void main(String[] args) {
       String jsonWebKey = generateJWT("123", "张三");
       System.out.println(jsonWebKey);
       Claims claims =  parseJWT(jsonWebKey);
       System.out.println(claims);
        System.out.println(validateLogin(jsonWebKey));

    }


}
