package raisetech.StudentManagement.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, Object>> handleValidationException(
      MethodArgumentNotValidException ex) {

    Map<String, String> fieldErrors = new HashMap<>();
    ex.getBindingResult().getFieldErrors()
        .forEach(error ->
            fieldErrors.put(error.getField(), error.getDefaultMessage())
        );

    return ResponseEntity
        .badRequest()
        .body(Map.of(
            "message", "validation error",
            "errors", fieldErrors
        ));
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<Map<String, String>> handleInvalidJson(
      HttpMessageNotReadableException ex) {

    return ResponseEntity
        .badRequest()
        .body(Map.of(
            "message", "invalid json payload"
        ));
  }
}
