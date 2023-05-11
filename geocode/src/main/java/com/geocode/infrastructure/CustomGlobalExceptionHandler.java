package com.geocode.infrastructure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    // error handler for @Valid
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {

        log.error(getEndpointData(request) + e.getLocalizedMessage());
        Map<String, Object> body = prepareResponseMap(e, status);
        return new ResponseEntity<>(body, headers, status);
    }

    // error handler for Exception, code INTERNAL_SERVER_ERROR
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> exception(Exception e, WebRequest request) {
        log.error(getEndpointData(request) + e.getLocalizedMessage());
        return new ResponseEntity<>(prepareCustomExceptionDto(e, HttpStatus.INTERNAL_SERVER_ERROR, Collections.singletonList(e.getMessage()),
                request.getDescription(false), ((ServletWebRequest) request).getHttpMethod().toString()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String getEndpointData(WebRequest request) {
        return MessageFormat.format("aspects.logTemplate.customGlobalExceptionHandler",
                ((ServletWebRequest) request).getHttpMethod().toString(), request.getDescription(false));
    }

    private Map prepareResponseMap(Exception e, HttpStatusCode status) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        //Get all errors
        List<String> errors = new ArrayList<>();
        if (e instanceof MethodArgumentNotValidException)
            errors = ((MethodArgumentNotValidException) e).getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
        else
            errors.add(e.getMessage());
        body.put("errors", errors);
        body.put("stackTrace", e.getStackTrace());
        return body;
    }

    private ExceptionDto prepareCustomExceptionDto(Exception e, HttpStatus status, List<String> messages, String path, String method) {
        return ExceptionDto.builder()
                .timestamp(new Date())
                .status(status.value())
                .messages(messages)
                .path(path)
                .method(method)
                .build();
    }
}
