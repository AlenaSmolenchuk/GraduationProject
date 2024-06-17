package ru.mts.graduation_project.aggregator.security.jwt.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

/**
 * Класс, предназначенный для определения правил доступности маршрутов API.
 */
@Component
public class RouteValidator {

    // Список открытых конечных точек API, доступных без аутентификации.
    public static final List<String> openApiEndpoints = List.of("/auth/authenticate");

    // Проверка, является ли запрос безопасным, основываясь на списке открытых конечных точек.
    public Predicate<ServerHttpRequest> isSecured = request -> openApiEndpoints.stream().noneMatch(
            uri -> request.getURI().getPath().contains(uri)
    );
}
