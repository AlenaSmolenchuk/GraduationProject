package ru.mts.graduation_project.deposit.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.function.Function;

/**
 * Утилита для работы с JWT токенами.
 */
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    /**
     * Извлекает номер телефона(username) из JWT токена.
     *
     * @param token JWT токен
     * @return номер телефона
     */
    public String extractPhoneNumber(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Извлекает определенный claim из JWT токена.
     *
     * @param token JWT токен
     * @param claimsResolver функция для извлечения claim
     * @param <T> тип возвращаемого значения
     * @return значение claim
     */

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    /**
     * Извлекает все claims из JWT токена.
     *
     * @param token JWT токен
     * @return все claims
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Возвращает ключ для подписи JWT токенов.
     *
     * @return ключ для подписи
     */
    private Key key() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
