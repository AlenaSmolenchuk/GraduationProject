package ru.mts.graduation_project.aggregator.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Класс для представления исключения, связанного с аггрегатором.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AggregatorException extends RuntimeException {
    private String message;

    public AggregatorException(String message) {
        super(message);
        this.message = message;
    }
}
