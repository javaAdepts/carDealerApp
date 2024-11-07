package com.binary.carDealer.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Component
public class JwtUtil {

    private final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // this method is used to extract UserName from token
    public String extractUsername(String token) {
        return  exractClaim(token, Claims::getSubject);
    }

    // this method is used to check the expiration of the token returns the Expiration Date
    public Date extractExpiration(String token) {
        return exractClaim(token, Claims::getExpiration);
    }

    // this method is used to generate the token using user credentials
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        String token = Jwts.builder()
                      .setClaims(claims)
                      .setSubject(userDetails.getUsername())
                      .setIssuedAt(new Date(System.currentTimeMillis()))
                      .setExpiration(new Date(System.currentTimeMillis()+ 1000*60*10))
                      .signWith(SECRET_KEY)
                      .compact();
        return token;
    }

    // this method is used to validate the token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String USERNAME = extractUsername(token);
        System.out.println(USERNAME);
        return (userDetails.getUsername().equals(USERNAME)) && !isTokenExpired(token);
    }

    // this method is used to check the token expiration using the current Date comparing with the Expiration Date
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // extract the Claims from the token
    private <T>T exractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }

}
