package io.fischer.quantumpen.exception;

import java.util.Date;

public record ExceptionResponse(Date data, String mensagem, String detalhes) {
}
