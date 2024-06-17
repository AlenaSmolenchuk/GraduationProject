package ru.mts.graduation_project.customservice.util.exc_handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.mts.graduation_project.customservice.dto.ExcData;
import ru.mts.graduation_project.customservice.dto.ExcResponse;
import ru.mts.graduation_project.customservice.exception.CustomerException;

/**
 * Обработчик исключений, связанных с пользователем.
 */
@Log4j2
@ControllerAdvice
public class CustomerExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Обработчик исключений CustomerException.
     *
     * @param e исключение
     * @return объект ResponseEntity с данными исключения и статусом NOT_FOUND
     */
    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<ExcResponse<ExcData>> handleException(CustomerException e) {
        ExcResponse<ExcData> exceptionResponse =
                new ExcResponse<>(new ExcData(e.getMessage()));

        log.error("Ошибка: {}, Сообщение: {}",
                e.getClass().getSimpleName(),
                e.getMessage()
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Обработчик исключений BadCredentialsException.
     *
     * @param e исключение
     * @return объект ResponseEntity с данными исключения и статусом FORBIDDEN
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExcResponse<ExcData>> handleException(BadCredentialsException e) {
        ExcResponse<ExcData> exceptionResponse =
                new ExcResponse<>(new ExcData("Неверный логин или пароль"));

        log.error("Ошибка: {}, Сообщение: {}",
                e.getClass().getSimpleName(),
                e.getMessage()
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
    }
}
