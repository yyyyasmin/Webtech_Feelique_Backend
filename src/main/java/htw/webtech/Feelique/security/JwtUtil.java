package htw.webtech.Feelique.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil{

    private final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long EXPIRATION_TIME = 86400000; // 24 Stunden in Millisekunden

    /**
     * Generiert ein JWT-Token für einen Benutzer
     */
    public String generateToken(String email, String role) {
        return Jwts.builder()
                .subject(email)
                .claim("role", role)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }

    /**
     * Extrahiert die E-Mail aus dem Token
     */
    public String extractEmail(String token) {
        return extractClaims(token).getSubject();
    }

    /**
     * Extrahiert die Rolle aus dem Token
     */
    public String extractRole(String token) {
        return extractClaims(token).get("role", String.class);
    }

    /**
     * Prüft, ob das Token gültig ist
     */
    public boolean isTokenValid(String token) {
        try {
            extractClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    /**
     * Extrahiert alle Claims aus dem Token
     */
    private Claims extractClaims(String token) {
        return Jwts.parser()  // ✅ GEÄNDERT: parser() statt parserBuilder()
                .verifyWith(SECRET_KEY)  // ✅ GEÄNDERT: verifyWith() statt setSigningKey()
                .build()
                .parseSignedClaims(token)  // ✅ GEÄNDERT: parseSignedClaims() statt parseClaimsJws()
                .getPayload();  // ✅ GEÄNDERT: getPayload() statt getBody()
    }
}