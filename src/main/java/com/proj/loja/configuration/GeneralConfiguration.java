package com.proj.loja.configuration;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.SecretKeyFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

/**
 * A general configuration class
 */
@Configuration
public class GeneralConfiguration {
    @Bean
    public MessageDigest messageDigest() throws NoSuchAlgorithmException{
        return MessageDigest.getInstance("SHA-512");
    }

    @Bean
    public SecretKeyFactory secretKeyFactory() throws NoSuchAlgorithmException{
        return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
    }

    @Bean
    public SecureRandom secureRandom(){
        return new SecureRandom();
    }

    @Bean
    public OpenAPI apiDocs(){
        return new OpenAPI().info(new Info().title("Loja Virtual").description("Uma API simples de uma loja virtual").contact(new Contact().name("Pedro Henrique de Oliveira").email("pedrohenrique.oliveira119@gmail.com")));
    }
}
