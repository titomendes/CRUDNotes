package example.CRUDNOTES.service;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import example.CRUDNOTES.exception.InvalidJwtAuthenticationException;
import example.CRUDNOTES.security.UserPrincipal;

@Service
public class JwtService {

    // 🔑 Algoritmo usado para assinar/verificar o JWT (neste caso HMAC-SHA256)
    private final Algorithm algorithm;

    
    private final Long expirationMs;

    // ⚙️ Construtor injeta o segredo e a duração a partir da configuração
    public JwtService(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration-ms}") Long expirationMs) {
        // Prepara o algoritmo de assinatura com o segredo definido na config
        this.algorithm = Algorithm.HMAC256(secret.getBytes());
        this.expirationMs = expirationMs;
    }

    public String generateToken(UserPrincipal principal) {
        Instant now = Instant.now(); // momento atual

        // extrair o role
        String role = principal.getAuthorities().toString();

        return JWT.create()
                .withSubject(principal.getEmail()) // "sub" claim → email
                .withIssuedAt(Date.from(now)) // "iat" claim → instante da emissão
                .withExpiresAt(Date.from(now.plusMillis(expirationMs))) // "exp" claim → validade
                .withClaim("role", role) // claim customizada → lista de roles
                .sign(algorithm); // assinatura com HMAC256 e segredo

    }

 
    // 🔎 Extrai o "email" (subject) de dentro de um token JWT válido
    public String extractEmail(String token) {
        return decodeJwt(token).getSubject();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        DecodedJWT decodedJWT = decodeJwt(token);

        // verifica se o email bate e se o token não expirou
        return decodedJWT.getSubject().equals(userDetails.getUsername()) &&
                decodedJWT.getExpiresAt().after(new Date());
    }

    private DecodedJWT decodeJwt(String token) {
        try {
            return JWT.require(algorithm) // requer o algoritmo correto
                    .build()
                    .verify(token); // verifica a assinatura e validade do token
        } catch (JWTVerificationException e) {
            throw new InvalidJwtAuthenticationException("JWT invalid or expired", e);
        }

    }


}
