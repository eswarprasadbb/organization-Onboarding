package com.example.organizationservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


    private final com.example.organizationservice.security.JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(com.example.organizationservice.security.JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    /**
     * Basic filter chain: permits health and auth endpoints, secures the rest.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(new com.example.organizationservice.security.RestAuthenticationEntryPoint())
                        .accessDeniedHandler(new com.example.organizationservice.security.RestAccessDeniedHandler()))
                .cors(Customizer.withDefaults())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**", "/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/users").permitAll()
                        .requestMatchers(HttpMethod.GET, "/actuator/health").permitAll()
                        // onboarding & tenant endpoints
                        .requestMatchers(HttpMethod.POST, "/api/organizations").authenticated()
                        .requestMatchers("/api/organizations/*/members/**").authenticated()
                        .requestMatchers("/api/organizations/*/roles/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/organizations/**").hasAuthority("ORG_READ")
                        .requestMatchers("/api/organizations/**").hasAnyAuthority("ORG_CREATE","ORG_UPDATE","ORG_DELETE")
                        // system admin endpoints
                        .requestMatchers("/api/system/**", "/api/permissions/**", "/api/roles/**", "/api/system-settings/**", "/api/audit-logs/**", "/api/api-call-logs/**").authenticated()
                        .requestMatchers("/api/**").hasRole("OWNER")
                        .anyRequest().denyAll())
                .addFilterBefore(jwtAuthenticationFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
