package ru.mts.graduation_project.deposit.util.exc_handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.mts.graduation_project.deposit.dto.ExcData;
import ru.mts.graduation_project.deposit.dto.ExcResponse;
import ru.mts.graduation_project.deposit.exception.DepositException;

/**
 * Обработчик исключений, связанных с вкладом.
 */
@Log4j2
@ControllerAdvice
public class DepositExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Обработчик исключений DepositException.
     *
     * @param e исключение
     * @return объект ResponseEntity с данными исключения и статусом HTTP
     */
    @ExceptionHandler(DepositException.class)
    public ResponseEntity<ExcResponse<ExcData>> handleException(DepositException e) {
        ExcResponse<ExcData> exceptionResponse =
                new ExcResponse<>(new ExcData(e.getMessage()));

        log.error("Ошибка: {}, Сообщение: {}",
                e.getClass().getSimpleName(),
                e.getMessage()
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
