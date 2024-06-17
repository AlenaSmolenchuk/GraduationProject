package ru.mts.graduation_project.aggregator.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;

/**
 * Утилита для работы с JWT токенами.
 */
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;


    /**
     * Валидирует JWT токен.
     *
     * @param token JWT токен
     */
    public void validateToken(final String token) {
        Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token);
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
