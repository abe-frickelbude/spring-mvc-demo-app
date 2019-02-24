package com.example.spring_mvc_demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * This advice specifically handles input data validation errors (e.g. controller request bodies annotated with @Valid)
 * and produces a BAD_REQUEST (400) response with a detailed JSON-based description of the violated validation
 * constraints.
 * <p>
 * This is a good example of a custom MVC exception handler that customizes the response format and may perform
 * additional side effects (in this case, pretty-printing and logging the validation constraint violations)
 */
@ControllerAdvice
public class InputValidationErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(InputValidationErrorHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleBadRequest(final MethodArgumentNotValidException ex) {

        log.warn(makeErrorMessage(ex));
        return ResponseEntity.badRequest().body(makeErrorResponse(ex));
    }

    private Map<String, String> makeErrorResponse(final MethodArgumentNotValidException ex) {

        final BindingResult bindingResult = ex.getBindingResult();
        Map<String, String> result = new HashMap<>();

        for (final FieldError error : bindingResult.getFieldErrors()) {
            result.put(error.getField(), error.getDefaultMessage());
        }

        for (final ObjectError error : bindingResult.getGlobalErrors()) {
            result.put(error.getObjectName(), error.getDefaultMessage());
        }
        return result;
    }

    /*
     * Note: this is only used for logging well-readable output
     */
    private String makeErrorMessage(final MethodArgumentNotValidException ex) {

        final BindingResult bindingResult = ex.getBindingResult();
        final StringBuilder builder = new StringBuilder();

        builder.append(
                MessageFormat.format("Input data validation failed in controller method {0}() in {1}\n",
                        ex.getParameter().getMethod().getName(),
                        ex.getParameter().getDeclaringClass().getSimpleName()));

        builder.append("Errors: [\n");

        for (final FieldError error : bindingResult.getFieldErrors()) {

            builder.append("\t");
            builder.append(MessageFormat.format("Field: {0}, invalid value: {1}, message: {2}",
                    error.getField(),
                    error.getRejectedValue(),
                    error.getDefaultMessage()));
            builder.append("\n");
        }

        for (final ObjectError error : bindingResult.getGlobalErrors()) {

            builder.append("\t");
            builder.append(MessageFormat.format("Object: {0}, message: {2}",
                    error.getObjectName(),
                    error.getDefaultMessage()));
            builder.append("\n");
        }

        builder.append("]");
        return builder.toString();
    }
}
