package ru.mts.graduation_project.aggregator.util.exc_handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.resource.NoResourceFoundException;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import ru.mts.graduation_project.aggregator.dto.ExcData;
import ru.mts.graduation_project.aggregator.dto.ExcResponse;
import ru.mts.graduation_project.aggregator.exception.AggregatorException;

/**
 * Обработчик исключений, связанных с аггрегатором.
 */
@Log4j2
@ControllerAdvice
public class AggregatorExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Обработчик исключений AggregatorException.
     *
     * @param e исключение
     * @return объект ResponseEntity с данными исключения и статусом FORBIDDEN
     */
    @ExceptionHandler(AggregatorException.class)
    public ResponseEntity<ExcResponse<ExcData>> handleException(AggregatorException e) {
        ExcResponse<ExcData> exceptionResponse =
                new ExcResponse<>(new ExcData(e.getMessage()));

        log.error("Ошибка: {}, Сообщение: {}",
                e.getClass().getSimpleName(),
                e.getMessage()
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
    }


    /**
     * Обработчик исключений NoResourceFoundException.
     *
     * @param e исключение
     * @return объект ResponseEntity с данными исключения и статусом NOT_FOUND
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ExcResponse<ExcData>> handleEndpointException(NoResourceFoundException e) {
        ExcResponse<ExcData> exceptionResponse =
                new ExcResponse<>(new ExcData(e.getMessage()));

        log.error("Ошибка: {}, Сообщение: {}",
                e.getClass().getSimpleName(),
                e.getMessage()
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
