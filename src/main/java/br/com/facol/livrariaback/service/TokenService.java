package br.com.facol.livrariaback.service;

import br.com.facol.livrariaback.domain.Client;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {


    public String gerarToken(Client usuario) {

        return JWT.create()
                .withIssuer("Livos")
                .withSubject(usuario.getName())
                .withClaim("id", usuario.getId())
                .withExpiresAt(LocalDateTime.now().plusMinutes(10).toInstant(ZoneOffset.of("-03:00"))
                ).sign(Algorithm.HMAC256("javainuse"));
    }
}
