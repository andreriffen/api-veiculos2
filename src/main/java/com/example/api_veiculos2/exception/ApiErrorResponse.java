package com.example.api_veiculos2.exception;

import java.time.Instant;
import java.util.Map;

/**
 * Representa o payload padrão de erros retornados pela API.
 */
public record ApiErrorResponse(
        Instant timestamp,
        int status,
        String error,
        String message,
        String path,
        Map<String, String> fieldErrors) {
}
