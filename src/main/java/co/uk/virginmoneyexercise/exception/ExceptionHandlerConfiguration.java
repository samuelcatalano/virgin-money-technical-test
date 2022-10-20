package co.uk.virginmoneyexercise.exception;

import co.uk.virginmoneyexercise.json.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerConfiguration {

    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<Object> handleInvalidCreditCardException(final NoSuchElementException e) {
        var error = new ErrorMessage()
            .message(e.getMessage())
            .status(BAD_REQUEST.name())
            .code(BAD_REQUEST.value());

        log.error(e.getMessage());
        return ResponseEntity.status(BAD_REQUEST).contentType(APPLICATION_JSON).body(error);
    }
}