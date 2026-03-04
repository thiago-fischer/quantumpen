package io.fischer.quantumpen.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Schema(description = "Estrutura padrão de erro da API")
public record ExceptionResponse(

        @Schema(description = "Data do erro", example = "2026-03-04")
        LocalDate data,

        @Schema(description = "Mensagem resumida do erro", example = "Entidade não encontrada")
        String erro,

        @Schema(description = "Mensagem detalhada", example = "Caneta com o ID 10 não encontrada!")
        String detalhes
) {}