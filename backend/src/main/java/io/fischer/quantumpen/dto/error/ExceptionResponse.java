package io.fischer.quantumpen.dto.error;

import java.util.Date;

public record ExceptionResponse(Date data, String mensagem, String detalhes) {
}
