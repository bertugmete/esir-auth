package tr.com.mkk.esir.auth.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Key {
    @JsonProperty("kty")
    String keyType;

    @JsonProperty("alg")
    String algorithm;

    @JsonProperty("n")
    String modulus;

    @JsonProperty("e")
    String exponent;

    @JsonProperty("use")
    String usage;
}
