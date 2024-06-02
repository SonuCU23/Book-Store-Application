package com.book.store.exceptions;

import com.book.store.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomException {

    private static final String INVALID_REQUEST = "Invalid request";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse> handleMethodArgumentNotValidException(final MethodArgumentNotValidException exception) {
        BaseResponse baseResponse = new BaseResponse();
        FieldError firstError = exception.getBindingResult().getFieldError();
        if(firstError != null) {
            baseResponse.setMessage(firstError.getDefaultMessage());
        } else {
            baseResponse.setMessage(INVALID_REQUEST);
        }
        baseResponse.setStatus("FAILURE");
        return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookException.class)
    public ResponseEntity<BaseResponse> handleBookException(BookException ex) {
        BaseResponse response = new BaseResponse();
        response.setStatus("FAILURE");
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, ex.getStatus());
    }
}
