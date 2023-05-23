package com.qzce.forchae;

import com.qzce.forchae.config.AuditorAwareImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

@EnableJpaAuditing
@SpringBootApplication
public class ForChaeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForChaeApplication.class, args);
    }

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }

    @Bean
    public PageableHandlerMethodArgumentResolverCustomizer customizer() {
        return p-> p.setOneIndexedParameters(true);
    }
}
