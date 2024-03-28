package com.lolpee.server.core.config;

import com.lolpee.server.domain.auth.handler.OAuth2FailureHandler;
import com.lolpee.server.domain.auth.handler.OAuth2SuccessHandler;
import com.lolpee.server.domain.auth.service.LolpeeOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private final LolpeeOAuth2UserService oAuth2UserService;
    private final OAuth2SuccessHandler successHandler;
    private final OAuth2FailureHandler failureHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        return security
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        registry -> registry
                            .requestMatchers("/api/v1/auth/**").permitAll()
                            .anyRequest().authenticated()
                )
                .oauth2Login(
                    configurer ->
                        configurer.authorizationEndpoint(
                                authorization -> authorization.baseUri("/oauth/authorization")
                        ).redirectionEndpoint(redirection -> redirection.baseUri("/*/oauth2/code/*")
                    )
                    .successHandler(successHandler)
                    .failureHandler(failureHandler)
                    .userInfoEndpoint(endPoint -> endPoint.userService(oAuth2UserService))
                )
                .build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
