package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class ValidationConfig {

    @Bean
    public ValidatingMongoEventListener ValidatingMongoEventListener(){
        return new ValidatingMongoEventListener(validator().getValidator());
    }

    @Bean
    public LocalValidatorFactoryBean validator(){
        return new LocalValidatorFactoryBean();
    }
}
