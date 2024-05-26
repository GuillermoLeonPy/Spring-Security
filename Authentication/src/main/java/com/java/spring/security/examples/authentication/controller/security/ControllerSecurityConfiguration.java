package com.java.spring.security.examples.authentication.controller.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration(value = "webSecurityConfiguration")
@EnableWebSecurity(debug = false)
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class ControllerSecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(
                User.withUsername("guest")
                        .password("guest")
                        .roles("GUEST")
                        .build());
        manager.createUser(
                User.withUsername("admin")
                        .password("admin")
                        .roles("GUEST","ADMIN")
                        .build());

        return manager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(
                authorize -> authorize.requestMatchers(HttpMethod.DELETE)
                        .hasRole("ADMIN")
                        .requestMatchers("/info/admin/**").hasAnyRole("ADMIN")
                        .requestMatchers("/info/guest/**").hasAnyRole("GUEST","ADMIN")
                        .requestMatchers("/info/login/**").permitAll()
                        .requestMatchers("/info/public/**").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
}
