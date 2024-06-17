package ru.mts.graduation_project.deposit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Класс для представления ответа с данными исключения.
 *
 * @param <T> тип данных исключения
 */
@Data
@AllArgsConstructor
public class ExcResponse<T> {
    private T exc;
}
