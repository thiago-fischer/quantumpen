package io.fischer.quantumpen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import java.time.LocalDate;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGenerico(Exception ex) {
        System.out.println(ex.getClass().getSimpleName());
        ExceptionResponse body = new ExceptionResponse(LocalDate.now(),"Erro interno no servidor", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNoResource(NoResourceFoundException ex) {
        ExceptionResponse body = new ExceptionResponse(LocalDate.now(),"Recurso estático não encontrado", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFound(NotFoundException ex) {
        ExceptionResponse body = new ExceptionResponse(LocalDate.now(),"Entidade não encontrada", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String paramValue = (ex.getValue() == null) ? "null" : ex.getValue().toString();
        ExceptionResponse body = new ExceptionResponse(
                LocalDate.now(),
                "Parâmetro inválido na requisição.",
                "O parâmetro " + ex.getName() + " recebeu " + paramValue + ", mas esperava um número"
        );
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

}
