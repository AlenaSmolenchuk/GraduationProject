package ru.mts.graduation_project.deposit.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import ru.mts.graduation_project.deposit.annotation.Logging;

/**
 * Aspect для логирования выполнения методов в приложении.
 */
@Aspect
@Component
@Log4j2
public class LogAspect {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String ENTRY_SYMBOL = ">>";
    private static final String EXIT_SYMBOL = "<<";

    /**
     * Оболочка вокруг основного метода логирования, вызываемая через аннотацию {@link Around}.
     * Принимает {@link ProceedingJoinPoint} и аннотацию {@link Logging} для определения метода и его параметров.
     * Вызывает метод {@code log} для реализации логирования.
     *
     * @param joinPoint Процессируемый точка выполнения, представляющая метод, который должен быть залогирован.
     * @param logging Аннотация, определяющая детали логирования для данного метода.
     * @return Результат выполнения метода, залогированного через этот аспект.
     * @throws Throwable Если происходит исключение во время выполнения метода.
     */
    @Around("execution(* *(..)) && @annotation(logging)")
    public Object logMethod(ProceedingJoinPoint joinPoint, Logging logging) throws Throwable {
        return log(joinPoint, logging);
    }

    private Object log(ProceedingJoinPoint joinPoint, Logging logging) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        Object[] args = joinPoint.getArgs();

        String message = logging.value().isEmpty() ? methodName : logging.value();

        if (logging.enter()) {
            logLevel(logging.level(), ENTRY_SYMBOL
                    + " Вход в "
                    + methodName + ": "
                    + message);
        }

        if (logging.logParams()) {
            logLevel(logging.level(), ENTRY_SYMBOL
                    + " " + methodName
                    + " параметры: "
                    + objectMapper.writeValueAsString(args));
        }

        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable t) {
            logLevel("ERROR", "Ошибка в : "
                    + methodName + ". "
                    + "Что-то пошло не так "
                    + t.getMessage()
                    + t);
            throw t;
        }

        if (logging.logResult()) {
            logLevel(logging.level(), EXIT_SYMBOL
                    + " " + methodName
                    + " результат: "
                    + objectMapper.writeValueAsString(result));
        }

        if (logging.exit()) {
            logLevel(logging.level(), EXIT_SYMBOL
                    + " Выход из "
                    + methodName + ": "
                    + message);
        }

        return result;
    }

    private void logLevel(String level, String message) {
        switch (level.toUpperCase()) {
            case "DEBUG" -> log.debug(message);
            case "INFO" -> log.info(message);
            case "WARN" -> log.warn(message);
            case "ERROR" -> log.error(message);
            default -> log.info(message);
        }
    }
}
