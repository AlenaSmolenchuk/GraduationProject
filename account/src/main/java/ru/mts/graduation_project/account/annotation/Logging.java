package ru.mts.graduation_project.account.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для определения деталей логирования метода.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Logging {

    String value() default "";
    boolean enter() default false;
    boolean exit() default false;
    String level() default "INFO";
    boolean logParams() default false;
    boolean logResult() default false;
}