package org.acme.infrastructure.driven.shared;


import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.jwt.Claims;


@ApplicationScoped
public class TokenService {
    public String generateToken(String username) {
        return Jwt.issuer("https://example.com/issuer")
                .expiresAt(System.currentTimeMillis() / 1000 + 3600)
                .claim(Claims.upn, username)
                .claim(Claims.groups, "user")
                .sign();
    }
}
