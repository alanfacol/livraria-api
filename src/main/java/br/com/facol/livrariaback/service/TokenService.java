package br.com.facol.livrariaback.service;

import br.com.facol.livrariaback.domain.Login;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {


    public String gerarToken(Login usuario) {

        return JWT.create()
                .withIssuer("Livros")
                .withSubject(usuario.getUsername())
                .withClaim("id", usuario.getId())
                .withExpiresAt(LocalDateTime.now().plusMinutes(10).toInstant(ZoneOffset.of("-03:00"))
                ).sign(Algorithm.HMAC256("javainuse"));
    }

    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256("javainuse"))
                .withIssuer("Livros")
                .build().verify(token).getSubject();
    }
}
