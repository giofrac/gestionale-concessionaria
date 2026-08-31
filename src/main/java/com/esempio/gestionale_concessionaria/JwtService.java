package com.esempio.gestionale_concessionaria;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKeyBase;

    @Value("${jwt.expiration-ms}")
    private long expirationMs;

    private SecretKey key() {
        return Keys.hmacShaKeyFor(secretKeyBase.getBytes(StandardCharsets.UTF_8));
    }

    public String generaToken(String username) {
        Instant ora = Instant.now();
        return Jwts.builder()
                .subject(username)
                .issuedAt(Date.from(ora))
                .expiration(Date.from(ora.plusMillis(expirationMs)))
                .signWith(key())
                .compact();
    }

    public String estraiUsername(String token) {
        return parseClaims(token).getSubject();
    }

    public boolean isTokenValido(String token, String username) {
        return estraiUsername(token).equals(username) && !isTokenScaduto(token);
    }

    private boolean isTokenScaduto(String token) {
        return parseClaims(token).getExpiration().before(new Date());
    }

    private Claims parseClaims(String token) {
        return Jwts.parser().verifyWith(key()).build().parseSignedClaims(token).getPayload();
    }
}
