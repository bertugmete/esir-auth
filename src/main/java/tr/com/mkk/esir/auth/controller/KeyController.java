package tr.com.mkk.esir.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.mkk.esir.auth.service.JWKProvider;
import tr.com.mkk.esir.auth.service.KeyList;

@RestController
@RequestMapping("/keys")
@RequiredArgsConstructor
public class KeyController {
    private final JWKProvider jwkProvider;

    @GetMapping("/jwks")
    public KeyList getKeys() {
        return jwkProvider.getKeys();
    }

}
