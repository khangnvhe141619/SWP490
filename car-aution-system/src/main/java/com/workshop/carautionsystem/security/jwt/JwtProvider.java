package com.workshop.carautionsystem.security.jwt;

import com.workshop.carautionsystem.security.userPrinciple.UserPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtProvider {
    private static  final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    private String jwtSecret="synvhe141716@fpt.com";
    private int jwtExpiration = 86400;
    public String createToken(Authentication authentication){
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return Jwts.builder().setSubject(userPrinciple.getUserName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+jwtExpiration*1000))
                .signWith(SignatureAlgorithm.HS512,jwtSecret).compact();
    }
    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch (SignatureException e){
            logger.error("Invalid JWT signature -> message:{}",e);
        }catch (MalformedJwtException e){
            logger.error("Invalid fomat token -> message:{}",e);
        }catch (ExpiredJwtException e){
            logger.error("Expired JWT token -> message:{}",e);
        }catch (UnsupportedJwtException e){
            logger.error("Unsupperted JWT signature -> message:{}",e);
        }catch (IllegalArgumentException e){
            logger.error("JWT calim String is empty -> message:{}",e);
        }
        return false;
    }
    public String getUserNameFromToken(String token){
        String userName= Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
        return userName;
    }
}
