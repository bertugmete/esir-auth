package tr.com.mkk.esir.auth.service;

import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.List;

@Service
public class JWKProvider {

    private static final String CRYPTO_ALGORITHM = "RSA";
    private static final String KEY_USAGE_SIGNING = "sig";

    @Value("#{'${jwt.jws.private-key}'.replaceAll('\\s*', '')}")
    private String jwsPrivateKey;
    @Value("#{'${jwt.jws.public-key}'.replaceAll('\\s*', '')}")
    private String jwsPublicKey;

    @Getter
    private PrivateKey privateKey;
    @Getter
    private RSAPublicKey publicKey;

    @PostConstruct
    void initialize() throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance(CRYPTO_ALGORITHM);

        PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(jwsPrivateKey));
        this.privateKey = keyFactory.generatePrivate(keySpecPKCS8);

        X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(jwsPublicKey));
        this.publicKey = (RSAPublicKey) keyFactory.generatePublic(keySpecX509);
    }

    public KeyList getKeys() {
        return KeyList.of(List.of(
                Key.builder()
                        .keyType(CRYPTO_ALGORITHM)
                        .algorithm(SignatureAlgorithm.RS256.getValue())
                        .modulus(Base64.getUrlEncoder().encodeToString(publicKey.getModulus().toByteArray()))
                        .exponent(Base64.getUrlEncoder().encodeToString(publicKey.getPublicExponent().toByteArray()))
                        .usage(KEY_USAGE_SIGNING)
                        .build()
        ));
    }
}
