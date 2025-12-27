package net.calmarti.banking_app.exception;

import java.time.LocalDateTime;

public record ErrorDetails(
                           LocalDateTime localDateTime,
                           Integer status,
                           String error,
                           String message,
                           String path,
                           String errorCode ) {
}
