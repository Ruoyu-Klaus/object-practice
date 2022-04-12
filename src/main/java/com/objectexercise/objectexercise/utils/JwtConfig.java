package com.objectexercise.objectexercise.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.yaml")
public class JwtConfig {
    private static String secret;

    public String getSecret(){
        return JwtConfig.secret;
    }

    @Value("${jwt.secret}")
    public void setSecret(String secret){
        JwtConfig.secret = secret;
    }
}
