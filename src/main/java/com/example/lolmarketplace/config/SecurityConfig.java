package com.example.lolmarketplace.config;

import com.example.lolmarketplace.services.UserLogin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration

public class SecurityConfig {
    private final UserLogin userLogin;

    public SecurityConfig(UserLogin userLogin) {
        this.userLogin = userLogin;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.disable())
                .authorizeRequests((requests) -> requests
                        .requestMatchers(
                                "/AddAccount", "/UpdateAccount"
                        ).authenticated()
                        .requestMatchers(
                                "/index", "/", "/webjars/**" ,"/h2-console/**"
                        ).permitAll()
                )
                .userDetailsService(userLogin)
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll())
                .headers().frameOptions().disable();
               ;


        return http.build();
    }
}
