package ru.mts.graduation_project.customservice.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Класс для представления исключения, связанного с пользователем.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerException extends RuntimeException {
    private String message;

    public CustomerException(String message) {
        super(message);
        this.message = message;
    }
}
