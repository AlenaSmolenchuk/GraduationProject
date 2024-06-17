package ru.mts.graduation_project.customservice.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mts.graduation_project.customservice.annotation.Logging;
import ru.mts.graduation_project.customservice.dto.JwtRequest;
import ru.mts.graduation_project.customservice.dto.JwtResponse;
import ru.mts.graduation_project.customservice.service.AuthService;

/**
 * Контроллер для обработки аутентификации.
 */
@Log4j2
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }


    /**
     * Обрабатывает запрос на аутентификацию и возвращает JWT токен.
     *
     * @param authenticationRequest запрос с данными для аутентификации
     * @return объект ResponseEntity с JWT токеном.
     */
    @Logging(value = "Аутентификация пользователяя",
            logParams = true,
            logResult = true,
            exit = true,
            enter = true)
    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> createToken(@RequestBody JwtRequest authenticationRequest) {
        return ResponseEntity.ok(service.authenticate(authenticationRequest));
    }
}