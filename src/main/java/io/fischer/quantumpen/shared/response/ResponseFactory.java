package io.fischer.quantumpen.shared.response;

import io.fischer.quantumpen.shared.dto.ApiResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public class ResponseFactory {

    public static <T> ResponseEntity<ApiResponseDTO<T>> ok(
            T content,
            String message,
            HttpServletRequest request
    ) {
        ApiResponseDTO<T> response = ApiResponseDTO.success(
                200,
                message,
                request.getRequestURI(),
                content
        );

        return ResponseEntity.ok(response);
    }

    public static <T> ResponseEntity<ApiResponseDTO<T>> created(
            T content,
            String message,
            HttpServletRequest request
    ) {
        ApiResponseDTO<T> response = ApiResponseDTO.success(
                201,
                message,
                request.getRequestURI(),
                content
        );

        return ResponseEntity.status(201).body(response);
    }

    public static ResponseEntity<ApiResponseDTO<Void>> noContent(
            String message,
            HttpServletRequest request
    ) {
        ApiResponseDTO<Void> response = ApiResponseDTO.success(
                204,
                message,
                request.getRequestURI(),
                null
        );

        return ResponseEntity.status(204).body(response);
    }
}