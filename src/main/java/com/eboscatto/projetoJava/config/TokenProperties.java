package com.eboscatto.projetoJava.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "projeto-java.security.token")
public class TokenProperties {
    /**
     * Chave secreta usada para assinar o JWT.
     */
    private String secret;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
