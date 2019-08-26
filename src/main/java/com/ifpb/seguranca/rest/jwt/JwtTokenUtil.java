package com.ifpb.seguranca.rest.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenUtil {

    private static String key = "secreta";

    public static final String TOKEN_HEADER = "Authentication";

    private static SignatureAlgorithm SIG_ALGORITHM = SignatureAlgorithm.HS256;

    public static String encode(String subject){
        return Jwts.builder()
                .setSubject(subject)
                .signWith(SIG_ALGORITHM, key)
                .compact();
    }

    public static Claims decode(String token){
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }

}
