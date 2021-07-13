package uz.pdp.task_1.securty;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtProvider {

    static long expireTime = 1000 * 60 * 60;
    static String keyWord = "secretWord";

    public String generateToken(String username){
        Date expireDate =new Date(System.currentTimeMillis()+expireTime);
        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, keyWord)
                .compact();
        return token;
    }

    public boolean validationtoken(String token){
        try {
            Jwts
                    .parser()
                    .setSigningKey(keyWord)
                    .parseClaimsJwt(token);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public String getUsernameFromToken(String token){
        return Jwts
                .parser()
                .setSigningKey(keyWord)
                .parseClaimsJwt(token)
                .getBody()
                .getSubject();
    }
}
