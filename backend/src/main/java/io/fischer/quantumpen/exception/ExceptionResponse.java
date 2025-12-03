package io.fischer.quantumpen.exception;

import java.time.LocalDate;

public record ExceptionResponse(LocalDate data, String mensagem, String detalhes) {
}
