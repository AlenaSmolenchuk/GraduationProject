package ru.mts.graduation_project.customservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.mts.graduation_project.customservice.security.jwt.JwtRequestFilter;

/**
 * Конфигурация безопасности для приложения.
 * Настраивает компоненты аутентификации и авторизации.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationProvider provider;
    private final JwtRequestFilter jwtRequestFilter;

    public SecurityConfig(AuthenticationProvider provider, JwtRequestFilter jwtRequestFilter) {
        this.provider = provider;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    /**
     * Определяет бин для SecurityFilterChain.
     * Настраивает безопасность HTTP запросов, включая аутентификацию и авторизацию.
     *
     * @param http объект HttpSecurity для настройки безопасности HTTP
     * @return SecurityFilterChain
     * @throws Exception если возникает ошибка при настройке безопасности HTTP
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(provider)
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
