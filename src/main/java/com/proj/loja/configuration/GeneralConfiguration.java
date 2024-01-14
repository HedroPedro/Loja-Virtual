package com.proj.loja.configuration;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.DuplicateFormatFlagsException;

import javax.swing.text.Document;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * A general configuration class
 * @author Pedro
 */
@Configuration
public class GeneralConfiguration {
    @Bean
    public MessageDigest messageDigest() throws NoSuchAlgorithmException{
        return MessageDigest.getInstance("SHA-512");
    }

    @Bean
    public SecureRandom secureRandom(){
        return new SecureRandom();
    }

    @Bean
    public OpenAPI apiDocs(){
        return new OpenAPI().info(new Info().title("Loja Virtual").description("Uma API simples de uma loja virtual"));
    }
}
