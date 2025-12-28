package net.calmarti.banking_app.exception;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


import java.time.LocalDateTime;
import java.util.stream.Collectors;

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

    //Handle specific exception
    @ExceptionHandler({
            HttpMessageNotReadableException.class,
            MethodArgumentNotValidException.class})
    ResponseEntity<ErrorDetails> badRequestHandler (Exception exception, WebRequest request){

        HttpStatus status = HttpStatus.BAD_REQUEST;

        String message = (exception instanceof MethodArgumentNotValidException)
                ? "Validation error"
                : "Invalid request body. One or more fields are not allowed or malformed.";


        ErrorDetails errorDetails = new ErrorDetails(
                    LocalDateTime.now(),
                    status.value(),
                    status.getReasonPhrase(),
                    message,
                    request.getDescription(false),
                    "BAD REQUEST"
            );

        return new ResponseEntity<>(errorDetails,status);
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
