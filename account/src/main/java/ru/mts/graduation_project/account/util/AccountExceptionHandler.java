package ru.mts.graduation_project.account.util;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.mts.graduation_project.account.dto.ExcData;
import ru.mts.graduation_project.account.dto.ExcResponse;
import ru.mts.graduation_project.account.exception.AccountException;

/**
 * Обработчик исключений, связанных с банковским счетом.
 */
@Log4j2
@ControllerAdvice
public class AccountExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Обработчик исключений AccountException.
     *
     * @param e исключение
     * @return объект ResponseEntity с данными исключения и статусом HTTP
     */
    @ExceptionHandler(AccountException.class)
    public ResponseEntity<ExcResponse<ExcData>> handleException(AccountException e) {
        ExcResponse<ExcData> exceptionResponse =
                new ExcResponse<>(new ExcData(e.getMessage()));

        log.error("Ошибка: {}, Сообщение: {}",
                e.getClass().getSimpleName(),
                e.getMessage()
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
