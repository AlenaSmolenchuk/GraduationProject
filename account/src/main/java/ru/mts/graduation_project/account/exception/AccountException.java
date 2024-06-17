package ru.mts.graduation_project.account.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Класс для представления исключения, связанного с банковским счетом.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AccountException extends RuntimeException {
    private String message;

    public AccountException(String message) {
        super(message);
        this.message = message;
    }
}
