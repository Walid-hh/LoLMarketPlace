package com.example.lolmarketplace.config;

import com.example.lolmarketplace.services.UserLogin;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;
import java.net.URLEncoder;

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
                .sessionManagement((session) -> session
                        .invalidSessionUrl("/login")
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true)
                        .expiredUrl("/login?expired")
                        .and())
                .logout(
                        logout -> logout
                                .logoutUrl("/logout")
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
                                .logoutSuccessUrl("/login")
                )

                .authorizeRequests((requests) -> requests
                        .requestMatchers(
                                "/AddAccount", "/UpdateAccount"
                        ).authenticated()
                        .requestMatchers(
                                "/index", "/", "/webjars/**", "/h2-console/**"
                        ).permitAll()
                )
                .userDetailsService(userLogin)
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                        .failureHandler(customAuthenticationFailureHandler()))
                .headers().frameOptions().disable()
        ;

        return http.build();
    }



    private AuthenticationFailureHandler customAuthenticationFailureHandler() {
        return new SimpleUrlAuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException, IOException {
                // Extract the error message from the exception
                String errorMessage = exception.getMessage();

                // Redirect the user back to the login page with the error message as a parameter
                String encodedMessage = URLEncoder.encode(errorMessage, "UTF-8");
                getRedirectStrategy().sendRedirect(request, response, "/login?error=" + encodedMessage);
            }
        };
    }
}
