package ru.mts.graduation_project.customservice.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.mts.graduation_project.customservice.dto.JwtRequest;
import ru.mts.graduation_project.customservice.dto.JwtResponse;
import ru.mts.graduation_project.customservice.entity.Customer;
import ru.mts.graduation_project.customservice.security.jwt.JwtUtil;
import ru.mts.graduation_project.customservice.repository.CustomerRepository;

/**
 * Сервис для авторизации пользователей через JWT токены.
 * Обеспечивает процесс аутентификации пользователя, генерации и проверки JWT токенов.
 */
@Service
public class AuthService {

    private final JwtUtil jwtUtil;
    private final CustomerRepository repository;
    private final AuthenticationManager manager;

    public AuthService(JwtUtil jwtUtil, CustomerRepository repository, AuthenticationManager manager) {
        this.jwtUtil = jwtUtil;
        this.repository = repository;
        this.manager = manager;
    }

    /**
     * Аутентифицирует пользователя по предоставленному номеру телефона и паролю.
     * Генерирует и возвращает JWT токен для аутентифицированного пользователя.
     *
     * @param request DTO с номером телефона и паролем пользователя
     * @return JWT токен.
     */
    public JwtResponse authenticate(JwtRequest request) {
        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getPhoneNumber(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Customer customer = repository.findByPhoneNumber(request.getPhoneNumber()).get();
        String jwtToken = jwtUtil.generateToken(customer);

        return JwtResponse.builder()
                .token(jwtToken)
                .build();
    }
}
