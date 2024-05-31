package com.cartelera.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad{

    @Autowired
    RequestFilter requestFilter;

    @Autowired
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:4200"); // Reemplaza esto con el dominio de tu aplicación Angular
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http
    .cors(Customizer.withDefaults()).csrf(csrf -> csrf.disable())
    .authorizeHttpRequests(
        authz -> authz
                    .requestMatchers("usuarios/registrar", "usuarios/inicioSesion/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
        ).exceptionHandling(exception -> exception
            .authenticationEntryPoint(jwtAuthenticationEntryPoint));

    http.addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class);
    
    return http.build();
    }
}
