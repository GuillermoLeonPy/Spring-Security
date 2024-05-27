package com.java.spring.security.examples.authentication.controller.security;

import com.java.spring.security.examples.authentication.controller.security.filter.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity(debug = false)
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class ControllerSecurityConfiguration {

    private final UserDetailsService userDetailsService;
    private final JwtRequestFilter jwtRequestFilter;

    public ControllerSecurityConfiguration(
            @Qualifier("my_custom_user_details_service")
            UserDetailsService userDetailsService,
            @Qualifier("jwt_request_filter")
            JwtRequestFilter jwtRequestFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(CsrfConfigurer -> CsrfConfigurer.ignoringRequestMatchers(PathRequest.toH2Console()).disable())
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
        .authorizeHttpRequests(
                authorize -> authorize.requestMatchers(HttpMethod.DELETE)
                        .hasRole("ADMIN")
                        .requestMatchers("/info/admin/**").hasAnyRole("ADMIN")
                        .requestMatchers("/info/guest/**").hasAnyRole("GUEST","ADMIN")
                        .requestMatchers("/info/login/**").permitAll()
                        .requestMatchers("/info/public/**").permitAll()
                        .requestMatchers("/authentication/authenticate").permitAll()
                        .requestMatchers(PathRequest.toH2Console()).permitAll()
                        .anyRequest().authenticated())
                //.authenticationProvider()
                //.authenticationManager()
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authBuilder) throws Exception {
        authBuilder.userDetailsService(userDetailsService);
    }


    @Bean("authentication_manager")
    public AuthenticationManager test(@Lazy AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


}
