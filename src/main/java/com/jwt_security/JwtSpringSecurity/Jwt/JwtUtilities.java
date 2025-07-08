package com.jwt_security.JwtSpringSecurity.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtilities {
    private final String secretKey = "this_is_my_super_secret_key_my_jwt_Authentication_authorization";
    private final SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
    public String jwtTokenGenerate(String username) {
        return
                Jwts.builder()
                        .setSubject(username)
                        .setIssuedAt(new Date())
                        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                        .signWith(key, SignatureAlgorithm.HS256)
                        .compact();
    }

    /**
     * Extract claims body.
     * This user for all require details from claims
     * */
    private Claims extractAllClaims(String jwtToken) {
        return Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }
    public String extractUsername(String jwtToken) {
        return extractAllClaims(jwtToken).getSubject();
    }

    public boolean ValidateToken(String jwtToken, UserDetails userDetails) {
        final String username=extractUsername(jwtToken);
        return username.equals(userDetails.getUsername())&&!isTokenExpire(jwtToken) ;
    }
    private boolean isTokenExpire(String jwtToken) {
        final Date expiration=extractAllClaims(jwtToken).getExpiration();
        return expiration.before(new Date());
    }
}
