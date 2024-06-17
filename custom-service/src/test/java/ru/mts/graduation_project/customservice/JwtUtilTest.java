package ru.mts.graduation_project.customservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import ru.mts.graduation_project.customservice.security.jwt.JwtUtil;
import ru.mts.graduation_project.customservice.service.UserDetailsServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JwtUtilTest {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl service;

    @Test
    public void testGenerate() {
        UserDetails userDetails = service.loadUserByUsername("89992223355");
        String token = jwtUtil.generateToken(userDetails);
        System.out.println(token);

        assertNotNull(token);
    }

    @Test
    public void testValidate() {
        UserDetails userDetails = service.loadUserByUsername("89992223355");
        String token = jwtUtil.generateToken(userDetails);

        assertTrue(jwtUtil.validateToken(token, userDetails));
    }

    @Test
    public void testExtractPhoneNumber() {
        UserDetails userDetails = service.loadUserByUsername("89992223355");
        String token = jwtUtil.generateToken(userDetails);

        assertEquals("89992223355", jwtUtil.extractPhoneNumber(token));
    }
}
