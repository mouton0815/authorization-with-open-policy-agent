package com.example.authz.config;

import com.example.authz.service.OpaAuthorizationManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.authorization.AuthorizationManagers;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${open-policy-agent.uri}")
    private String opaUri;

    @Bean
    public SecurityFilterChain accountAuthorization(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(requests ->
                        requests.anyRequest()
                                .access(AuthorizationManagers.allOf(
                                        new AuthenticatedAuthorizationManager<>(),
                                        new OpaAuthorizationManager(opaUri)))
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }
}
