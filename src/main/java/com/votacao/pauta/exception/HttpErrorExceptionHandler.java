package com.votacao.pauta.exception;

import jakarta.servlet.http.HttpServletRequest;
import java.time.Instant;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;


public class HttpErrorExceptionHandler {

  @Autowired
  private MessageSource messageSource;


  @ExceptionHandler(ObjectNotFoundException.class)
  public ResponseEntity<ApiError> handleObjectNotFoundException(ObjectNotFoundException e, HttpServletRequest request) {
    var errorMessage = messageSource.getMessage("register.not.found", new Object[]{e.getIdentifier()}, LocaleContextHolder.getLocale());
    var error = new ApiError(HttpStatus.NOT_FOUND.value(), errorMessage, Instant.now().toEpochMilli());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }

  @ExceptionHandler(HttpClientErrorException.Forbidden.class)
  public ResponseEntity<ApiError> handleObjectForbiddenException(HttpClientErrorException.Forbidden e, HttpServletRequest request) {
    var error = new ApiError(HttpStatus.FORBIDDEN.value(), e.getMessage(), Instant.now().toEpochMilli());
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
  }

  @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
  public ResponseEntity<ApiError> handleObjectInternalServerErrorException(HttpServerErrorException.InternalServerError e, HttpServletRequest request) {
    var error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), Instant.now().toEpochMilli());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
  }

  @ExceptionHandler(HttpClientErrorException.BadRequest.class)
  public ResponseEntity<ApiError> handleObjectBadRequestErrorException(HttpClientErrorException.BadRequest e, HttpServletRequest request) {
    var error = new ApiError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), Instant.now().toEpochMilli());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }
}
