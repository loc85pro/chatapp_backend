package com.myproject.chatserver.security;

import java.util.Date;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;

@Component
public class JwtProvider {
    private final String JWT_SECRET = "PhanLoccccc";

    public String generateToken(String username, long expriration) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expriration);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        // try {
        Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
        return true;
        // } catch (MalformedJwtException ex) {
        // log.error("Invalid Token");
        // } catch (ExpiredJwtException ex) {
        // log.error("Expired JWT token");
        // } catch (UnsupportedJwtException ex) {
        // log.error("Unsupported JWT token");
        // } catch (IllegalArgumentException ex) {
        // log.error("JWT claims string is empty.");
        // }
        // return false;
    }
}
