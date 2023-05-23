package com.qzce.forchae.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                    .antMatchers("/board/modify/**").authenticated()
                    .anyRequest().permitAll()
                .and()
                    .formLogin()
                    .loginProcessingUrl("/loginProc")
                    .usernameParameter("mname")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/")
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/logoutMessage")
                .and()
                    .csrf().disable();

        return http.build();

    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/static/js/**", "/static/css/**");
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
