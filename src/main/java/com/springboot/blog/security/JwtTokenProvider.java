package com.springboot.blog.security;

import com.springboot.blog.exception.BlogAPIException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;
    @Value("${app.jwt-expiration-milliseconds}")
    private String jwtExpirationDate;

    public String generateToken(Authentication authentication){
        String userName = authentication.getName();

       /* Date currentDate= new Date();
        System.out.println("--------------");
        Date expirationDate = new Date(currentDate.getTime()+jwtExpirationDate);
        System.out.println("-----------------NO");*/ // Java 8 depreciated.
        System.out.println("_------------------Not-----");
        Instant instant = Instant.now().plusMillis(Long.parseLong(jwtExpirationDate));
        Date expireDate = Date.from(instant);
        System.out.println("-----------Passed");
        JwtBuilder builder = Jwts.builder();
        builder.setSubject(userName);
        builder.setIssuedAt(new Date());
        builder.setExpiration(expireDate);
        builder.signWith(key());
        String token = builder.compact();

        return token;
    }

    private Key key(){
        //Need to decode the secret
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    //get UserName from JWT Token
    public String getUserName(String token){

        Claims claims = Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody();

        String userName = claims.getSubject();
        return userName;
    }

    //Validate Jwt Token
    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(token);
            return true;
        }catch (MalformedJwtException ex){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Invalid JWT Token");
        }catch (ExpiredJwtException ex){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Expired JWT Token");
        }catch (UnsupportedJwtException ex){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"UnSupported JWT Token");
        }catch (IllegalArgumentException ex){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"JWT Claims String is Empty");
        }
    }

}
