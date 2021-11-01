package com.hexagonal.api.application.adapters.web;

import com.hexagonal.api.core.domain.exception.BusinessRuleException;
import com.hexagonal.api.core.domain.exception.EmailAlreadyRegisteredException;
import com.hexagonal.api.core.domain.exception.InvalidAttributeException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    var httpStatus = status.value();
    var error = "bad request";
    var message = "um ou mais campos estão inválidos";
    var path = request.getDescription(false).substring(4);

    var response = new ApiErrorResponse(httpStatus, error, message, path);

    var fields = new ArrayList<String>();

    for (ObjectError erro : ex.getBindingResult().getAllErrors()) {
      var fieldName = ((FieldError) erro).getField();
      var msg = erro.getDefaultMessage();
      fields.add(fieldName + " - " + msg);
    }

    response.setFields(fields);

    return super.handleExceptionInternal(ex, response, headers, status, request);
  }

  private ResponseEntity<ApiErrorResponse> buildUnprocessableEntity(String msg, HttpServletRequest request) {
    var error = "unprocessable entity";
    var status = HttpStatus.UNPROCESSABLE_ENTITY;
    var message = msg;
    var path = request.getRequestURI();
    var responseBody = new ApiErrorResponse(status.value(), error, message, path);
    return ResponseEntity.status(status).body(responseBody);
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