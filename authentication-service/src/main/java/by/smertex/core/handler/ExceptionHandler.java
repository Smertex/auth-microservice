package by.smertex.core.handler;

import by.smertex.core.dto.output.ResponseException;
import by.smertex.core.exception.AccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(AccessException.class)
    ResponseEntity<ResponseException> responseExceptionHandler(AccessException e) {
        return ResponseEntity.badRequest()
                .body(ResponseException.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message(e.getMessage())
                        .build());
    }
}
