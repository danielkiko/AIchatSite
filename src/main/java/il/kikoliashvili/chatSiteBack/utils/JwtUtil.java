package il.kikoliashvili.chatSiteBack.utils;

import il.kikoliashvili.chatSiteBack.entities.SiteUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {
    // Секретный ключ для подписи токенов
    private final String SECRET_KEY = "mySecretKey...mySecretKey...mySecretKey...mySecretKey...";

    // Срок жизни токена - 10 часов
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 10;

    // Создать токен для пользователя
    public String generateToken(SiteUser user) {
        return Jwts.builder()
                .subject(user.getUsername()) // Кому выдан токен
                .issuedAt(new Date()) // Когда выдан
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // До когда действует
                .signWith(getSigningKey()) // Подпись
                .compact();
    }

    // Проверить токен
    public Boolean validateToken(String token, SiteUser user) {
        final String username = extractUsername(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .decryptWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}
