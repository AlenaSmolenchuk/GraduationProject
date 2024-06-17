package ru.mts.graduation_project.aggregator.security.jwt.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import ru.mts.graduation_project.aggregator.exception.NoAuthHeaderFoundException;
import ru.mts.graduation_project.aggregator.exception.UnAuthException;
import ru.mts.graduation_project.aggregator.security.jwt.JwtUtil;

import static ru.mts.graduation_project.aggregator.util.constants.FilterConstants.AUTHORIZATION;
import static ru.mts.graduation_project.aggregator.util.constants.FilterConstants.BEARER;

/**
 * Фильтр JWT для обеспечения безопасности маршрутов API.
 */
@Component
public class JwtFilter extends AbstractGatewayFilterFactory<JwtFilter.Config> {

    private final RouteValidator validator;
    private final JwtUtil jwtUtil;

    public JwtFilter(RouteValidator validator, JwtUtil jwtUtil) {
        super(Config.class);
        this.validator = validator;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Применяет фильтр к маршрутам, проверяя наличие и валидность токена доступа.
     *
     * @param config конфигурация фильтра
     * @return {@link GatewayFilter} для применения к маршрутам
     */
    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey(AUTHORIZATION)) {
                    throw new NoAuthHeaderFoundException("Отсутствует заголовок.");
                }

                String authHeader = exchange.getRequest().getHeaders().get(AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith(BEARER)) {
                    authHeader = authHeader.substring(7);
                }
                try {
                    jwtUtil.validateToken(authHeader);
                } catch (Exception e) {
                    throw new UnAuthException("Неавторизованный вход.");
                }
            }
            return chain.filter(exchange);
        });
    }

    /**
     * Класс для конфигурации фильтра.
     * Не содержит дополнительных полей или методов.
     */
    public static class Config {
    }
}
