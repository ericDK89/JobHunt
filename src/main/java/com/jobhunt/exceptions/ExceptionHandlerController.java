package com.jobhunt.exceptions;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;

@ControllerAdvice
public class ExceptionHandlerController {

    private final MessageSource messageSource;

    public ExceptionHandlerController(MessageSource message) {
        this.messageSource = message;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ArrayList<Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        var errorList = new ArrayList<>();

        exception.getBindingResult().getFieldErrors().forEach(err -> {
            var message = messageSource.getMessage(err, LocaleContextHolder.getLocale());
            var error = new ErrorDTO(message, err.getField());
            errorList.add(error);
        });

        return new ResponseEntity<>(errorList, HttpStatus.FAILED_DEPENDENCY);
    }
}
