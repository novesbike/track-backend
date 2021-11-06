package com.hexagonal.api.application.adapters.http;

import com.hexagonal.api.core.domain.exception.*;
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


  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiErrorResponse> resourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
    return buildMessageError(ex.getMessage(), request, HttpStatus.NOT_FOUND);
  }


  @ExceptionHandler(EmailAlreadyRegisteredException.class)
  public ResponseEntity<ApiErrorResponse> emailAlreadyRegisteredException(EmailAlreadyRegisteredException ex, HttpServletRequest request) {
    return buildMessageError(ex.getMessage(), request, HttpStatus.UNPROCESSABLE_ENTITY);
  }

  @ExceptionHandler(InvalidAttributeException.class)
  public ResponseEntity<ApiErrorResponse> invalidAttributeException(InvalidAttributeException ex, HttpServletRequest request) {
    return buildMessageError(ex.getMessage(), request, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NotAuthorizedException.class)
  public ResponseEntity<ApiErrorResponse> notAuthorizedException(NotAuthorizedException ex, HttpServletRequest request) {
    return buildMessageError(ex.getMessage(), request, HttpStatus.UNAUTHORIZED);
  }


  @ExceptionHandler(BusinessRuleException.class)
  public ResponseEntity<ApiErrorResponse> businessRuleException(BusinessRuleException ex, HttpServletRequest request) {
    return buildMessageError(ex.getMessage(), request, HttpStatus.UNPROCESSABLE_ENTITY);
  }

  private ResponseEntity<ApiErrorResponse> buildMessageError(String msg, HttpServletRequest request, HttpStatus status) {
    var responseBody = new ApiErrorResponse(status.value(), msg, request.getRequestURI());
    return ResponseEntity.status(status).body(responseBody);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

    var httpStatus = status.value();
    var message = "um ou mais campos estão inválidos";
    var path = request.getDescription(false).substring(4);

    var fields = new ArrayList<String>();

    for (ObjectError error : ex.getBindingResult().getAllErrors()) {
      var fieldName = ((FieldError) error).getField();
      var msg = error.getDefaultMessage();
      fields.add(fieldName + " - " + msg);
    }

    var response = new ApiErrorResponse(httpStatus, message, path);
    response.setFields(fields);

    return super.handleExceptionInternal(ex, response, headers, status, request);
  }
}