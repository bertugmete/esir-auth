package tr.com.mkk.esir.auth.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tr.com.mkk.esir.auth.request.LoginReqeust;
import tr.com.mkk.esir.auth.service.JWKProvider;
import tr.com.mkk.esir.auth.service.KeyList;
import tr.com.mkk.esir.auth.service.LoginService;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {
    private final LoginService loginService;

    @PostMapping("/login")
    public String login(@RequestBody LoginReqeust loginRequest) {
        return loginService.login(loginRequest);
    }

    @GetMapping("/register")
    public String register(@RequestBody LoginReqeust loginRequest) {
        return "Register";
    }
}
