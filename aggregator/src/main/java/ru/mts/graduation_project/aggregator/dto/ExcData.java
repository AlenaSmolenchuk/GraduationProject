package ru.mts.graduation_project.aggregator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Класс для представления данных исключения.
 */
@Data
@AllArgsConstructor
public class ExcData {
    private String message;
}
