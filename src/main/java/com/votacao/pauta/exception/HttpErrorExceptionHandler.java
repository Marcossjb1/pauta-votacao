package com.votacao.pauta.exception;

import java.time.Instant;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.HttpClientErrorException.Forbidden;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

@ControllerAdvice
public class HttpErrorExceptionHandler {

  @ExceptionHandler(ObjectNotFoundException.class)
  public ResponseEntity<ApiError> notFound(ObjectNotFoundException ex) {
    return buildErrorResponse(HttpStatus.NOT_FOUND, "Não foi possível encontrar " + ex.getEntityName());
  }

  @ExceptionHandler(Forbidden.class)
  public ResponseEntity<ApiError> forbidden(Forbidden e) {
    return buildErrorResponse(HttpStatus.FORBIDDEN, e.getMessage());
  }

  @ExceptionHandler(InternalServerError.class)
  public ResponseEntity<ApiError> internalServerError(InternalServerError e) {
    return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
  }

  @ExceptionHandler(BadRequest.class)
  public ResponseEntity<ApiError> badRequest(BadRequest e) {
    return buildErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
  }

  private ResponseEntity<ApiError> buildErrorResponse(HttpStatus status, String message) {
    var error = new ApiError(status.value(), message, Instant.now().toEpochMilli());
    return ResponseEntity.status(status).body(error);
  }

}
