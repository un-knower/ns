package com.newsee.oauth.util;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.Serializable;
import java.util.Date;

public class JwtTokenUtil implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4197956449207303014L;


	/**
     * 创建jwtToken
     * @param signingKey
     * @param subject
     * @return
     */
    public static String generateToken(String signingKey, String subject, Long userId) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        JwtBuilder builder = Jwts.builder()
                .setSubject(subject)
                .setId(userId.toString())
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, signingKey);

        return builder.compact();
    }

    /**
     * 解密jwtToken
     * @param token
     * @param signingKey
     * @return
     */
    public static Boolean parserToken(String token,String signingKey){
        try{
            Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token);
        }catch (Exception e){
            return false;
        }
        return true;
    }

//    /**
//     * 获取用户名
//     * @param httpServletRequest
//     * @param jwtTokenCookieName
//     * @param signingKey
//     * @return
//     */
//    public static String getSubject(HttpServletRequest httpServletRequest, String jwtTokenCookieName, String signingKey){
//        String token = CookieUtil.getValue(httpServletRequest, jwtTokenCookieName);
//        if(token == null) return null;
//        return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody().getSubject();
//    }
//
//    /**
//     * 获取用户名
//     * @param httpServletRequest
//     * @param jwtTokenCookieName
//     * @param signingKey
//     * @return
//     */
//    public static Integer getId(HttpServletRequest httpServletRequest, String jwtTokenCookieName, String signingKey){
//        String token = CookieUtil.getValue(httpServletRequest, jwtTokenCookieName);
//        if(token == null) return null;
//        return Integer.parseInt(Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody().getId());
//    }
}

