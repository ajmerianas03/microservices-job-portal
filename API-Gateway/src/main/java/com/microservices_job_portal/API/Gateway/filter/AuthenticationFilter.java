package com.microservices_job_portal.API.Gateway.filter;

import com.microservices_job_portal.API.Gateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            // Check if the route is secured
            if (validator.isSecured.test(exchange.getRequest())) {

                // Check if Authorization header exists
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    return sendForbiddenError(exchange, "Missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

                // Check if the Authorization header starts with "Bearer "
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7); // Extract the token part
                } else {
                    return sendForbiddenError(exchange, "Invalid authorization header format");
                }

                try {
                    // Validate the JWT token
                    jwtUtil.validateToken(authHeader);
                } catch (Exception e) {
                    return sendForbiddenError(exchange, "Unauthorized access to application");
                }
            }
            return chain.filter(exchange);
        };
    }

    private Mono<Void> sendForbiddenError(ServerWebExchange exchange, String errorMessage) {
        // Set response status to 403 Forbidden
        exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.FORBIDDEN);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        // Create a custom error response
        String errorResponse = String.format("{\"status\":403, \"error\":\"Forbidden\", \"message\":\"%s\"}", errorMessage);

        // Send the error response body
        return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(errorResponse.getBytes())));
    }

    public static class Config {
    }
}
