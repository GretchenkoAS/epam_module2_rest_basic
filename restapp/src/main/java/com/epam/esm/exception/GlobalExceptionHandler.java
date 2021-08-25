package com.epam.esm.exception;

import com.epam.esm.exeption.CustomException;
import com.epam.esm.exeption.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private final MessageSource messageSource;

    @Autowired
    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<CustomErrorResponse> handleProjectException(CustomException ex) {
        CustomErrorResponse apiResponse = new CustomErrorResponse();
        apiResponse.setCode(ex.getErrorCode().getCode().toString());
        apiResponse.setMessage(messageSource.getMessage(ex.getErrorCode().getMessage(), ex.getParams(), Locale.getDefault()));
        return new ResponseEntity<>(apiResponse, ex.getErrorCode().getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomErrorResponse apiResponse = new CustomErrorResponse();
        apiResponse.setCode(ErrorCode.MESSAGE_NOT_READABLE.getCode().toString());
        apiResponse.setMessage(messageSource.getMessage(ErrorCode.MESSAGE_NOT_READABLE.getMessage(), new Object[]{}, Locale.getDefault()));
        return new ResponseEntity(apiResponse, status);
    }




    @ExceptionHandler({DataAccessException.class})
    public ResponseEntity<CustomErrorResponse> handleProjectException(DataAccessException ex) {
        CustomErrorResponse apiResponse = new CustomErrorResponse();
        apiResponse.setCode(ErrorCode.INTERNAL_ERROR.getCode().toString());
        apiResponse.setMessage(messageSource.getMessage(ErrorCode.INTERNAL_ERROR.getMessage(),
                new Object[] {}, Locale.getDefault()));
        return new ResponseEntity<>(apiResponse, ErrorCode.INTERNAL_ERROR.getHttpStatus());
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<CustomErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        CustomErrorResponse apiResponse = new CustomErrorResponse();
        apiResponse.setCode(ErrorCode.METHOD_ARGUMENT_TYPE_MISMATCH.getCode().toString());
        apiResponse.setMessage(messageSource.getMessage(ErrorCode.METHOD_ARGUMENT_TYPE_MISMATCH.getMessage(),
                new Object[] {ex.getName()}, Locale.getDefault()));
        return new ResponseEntity<>(apiResponse, ErrorCode.METHOD_ARGUMENT_TYPE_MISMATCH.getHttpStatus());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<CustomErrorResponse> handleProjectException(Exception ex) {
        CustomErrorResponse apiResponse = new CustomErrorResponse();
        apiResponse.setCode(ErrorCode.BAD_REQUEST.getCode().toString());
        apiResponse.setMessage(messageSource.getMessage(ErrorCode.BAD_REQUEST.getMessage(),
                new Object[] {}, Locale.getDefault()));
        return new ResponseEntity<>(apiResponse, ErrorCode.BAD_REQUEST.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleNoHandlerFoundException(ex, headers, status, request);
    }
}
