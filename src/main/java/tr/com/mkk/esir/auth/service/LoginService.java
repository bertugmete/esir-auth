package tr.com.mkk.esir.auth.service;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.com.mkk.esir.auth.request.LoginReqeust;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final JWKProvider jwkProvider;

    public String login(LoginReqeust loginReqeust) {
        return Jwts.builder()
                .setSubject(loginReqeust.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plus(5, ChronoUnit.MINUTES)))
                .signWith(jwkProvider.getPrivateKey())
                .compact();
    }
}
