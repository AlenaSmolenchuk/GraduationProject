package ru.mts.graduation_project.customservice.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Утилита для работы с JWT токенами.
 */
@Log4j2
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expirationMs;

    /**
     * Генерирует новый JWT токен для пользователя.
     *
     * @param userDetails данные пользователя
     * @return сгенерированный JWT токен
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Генерирует JWT токен с указанными claims и данными пользователя.
     *
     * @param claims claims для включения в токен
     * @param userDetails данные пользователя
     * @return сгенерированный JWT токен
     */
    private String generateToken(Map<String, Object> claims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

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
     * Валидирует JWT токен.
     *
     * @param token JWT токен
     * @param userDetails данные пользователя
     * @return true, если токен валиден, иначе false
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractPhoneNumber(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
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
     * Проверяет, истек ли срок действия JWT токена.
     *
     * @param token JWT токен
     * @return true, если срок действия токена истек, иначе false
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Извлекает дату истечения срока действия JWT токена.
     *
     * @param token JWT токен
     * @return дата истечения срока действия
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
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
