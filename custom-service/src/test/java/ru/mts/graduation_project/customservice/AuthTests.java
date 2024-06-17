package ru.mts.graduation_project.customservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;
import ru.mts.graduation_project.customservice.controller.AuthController;
import ru.mts.graduation_project.customservice.dto.JwtRequest;
import ru.mts.graduation_project.customservice.dto.JwtResponse;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AuthTests {

    @Autowired
    private AuthController controller;

    @Test
    public void testAuthenticateUser() {
        JwtResponse jwtResponse = controller.createToken(
                new JwtRequest(
                        "89992223355",
                        "user1")).getBody();
        
        assertNotNull(jwtResponse != null ? jwtResponse.getToken() : null);
    }

    @Test
    public void testAuthenticateUserBadCredentials() {
        assertThrows(BadCredentialsException.class,
                () -> controller.createToken(
                        new JwtRequest(
                                "89992223355",
                                "89992223355")
                ));
    }
}
