package com.hexagonal.api.application.adapters.web;

import com.hexagonal.api.core.domain.exception.BusinessRuleException;
import com.hexagonal.api.core.domain.exception.EmailAlreadyRegisteredException;
import com.hexagonal.api.core.domain.exception.InvalidAttributeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  private ResponseEntity<ApiErrorResponse> buildUnprocessableEntity(String msg, HttpServletRequest request) {
    var error = "unprocessable entity";
    var httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
    var message = msg;
    var path = request.getRequestURI();
    var responseBody = new ApiErrorResponse(httpStatus.value(), error, message, path);
    return ResponseEntity.status(httpStatus).body(responseBody);
  }


  @ExceptionHandler(EmailAlreadyRegisteredException.class)
  public ResponseEntity<ApiErrorResponse> emailAlreadyRegistered(EmailAlreadyRegisteredException ex, HttpServletRequest request) {
    return buildUnprocessableEntity(ex.getMessage(), request);
  }


  @ExceptionHandler(BusinessRuleException.class)
  public ResponseEntity<ApiErrorResponse> businessRuleException(BusinessRuleException ex, HttpServletRequest request) {
    return buildUnprocessableEntity(ex.getMessage(), request);
  }


  @ExceptionHandler(InvalidAttributeException.class)
  public ResponseEntity<ApiErrorResponse> invalidAttributeException(InvalidAttributeException ex, HttpServletRequest request) {
    return buildUnprocessableEntity(ex.getMessage(), request);
  }
}
