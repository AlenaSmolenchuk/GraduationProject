package ru.mts.graduation_project.deposit.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Класс для представления исключения, связанного с вкладом.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DepositException extends RuntimeException {

    private String message;

    public DepositException(String message) {
        super(message);
        this.message = message;
    }
}
