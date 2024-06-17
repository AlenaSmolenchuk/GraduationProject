package ru.mts.graduation_project.customservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс для представления запроса с данными для аутентификации.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest {

    private String phoneNumber;
    private String password;
}
