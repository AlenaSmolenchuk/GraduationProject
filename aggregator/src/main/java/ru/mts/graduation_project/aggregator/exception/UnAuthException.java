package ru.mts.graduation_project.aggregator.exception;


/**
 * Исключение, выбрасываемое в случае попытки неавторизованного входа.
 */
public class UnAuthException extends AggregatorException {
    public UnAuthException(String message) {
        super(message);
    }
}
