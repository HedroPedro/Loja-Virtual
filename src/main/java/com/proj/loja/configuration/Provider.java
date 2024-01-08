package com.proj.loja.configuration;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Provider {
    @Bean
    public MessageDigest messageDigest() throws NoSuchAlgorithmException{
        return MessageDigest.getInstance("SHA-512");
    }

    @Bean
    public SecureRandom secureRandom(){
        return new SecureRandom();
    }
}
