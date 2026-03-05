package io.fischer.quantumpen.shared.dto;

import java.time.Instant;

public record ApiResponseDTO<T>(
        Instant timestamp,
        int status,
        String message,
        String path,
        T content
) {
    public static <T> ApiResponseDTO<T> success(int status, String message, String path, T content) {
        return new ApiResponseDTO<>(Instant.now(), status, message, path, content);
    }
}