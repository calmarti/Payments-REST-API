package net.calmarti.banking_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {


    //Handle specific exception: AccountException:
    @ExceptionHandler(AccountException.class)
    ResponseEntity<ErrorDetails> accountExceptionHandler(AccountException exception, WebRequest request){

        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorDetails errorDetails  = new ErrorDetails(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                exception.getMessage(),
                request.getDescription(false),
                "ACCOUNT NOT FOUND"
        );

        return new ResponseEntity<>(errorDetails, status);
    }

    //Handle generic exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGenericException(Exception exception, WebRequest request){

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                "An unexpected error occurred",   //am intentionally generic message for sacurity reasons (instead of exception.getMessage) f
                 request.getDescription(false),
                "UNEXPECTED ERROR"
        );

        return new ResponseEntity<>(errorDetails, status);

    }
}
