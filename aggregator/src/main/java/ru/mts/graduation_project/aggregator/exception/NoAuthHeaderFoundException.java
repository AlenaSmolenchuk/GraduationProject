package ru.mts.graduation_project.aggregator.exception;

/**
 * Исключение, выбрасываемое в случае отсутствия заголовка аутентификации.
 */
public class NoAuthHeaderFoundException extends AggregatorException {
    public NoAuthHeaderFoundException(String message) {
        super(message);
    }
}
