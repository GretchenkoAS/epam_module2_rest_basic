package com.epam.esm.exception;

import com.epam.esm.exeption.CustomException;
import com.epam.esm.exeption.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
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

//    @Override
//    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
//                                                                     HttpHeaders headers, HttpStatus status, WebRequest request) {
//        CustomErrorResponse apiResponse = new CustomErrorResponse();
//        apiResponse.setCode(ErrorCode.UNSUPPORTED_MEDIA_TYPE.getCode().toString());
//        apiResponse.setMessage(messageSource.getMessage(ErrorCode.UNSUPPORTED_MEDIA_TYPE.getMessage(), new Object[] {}, Locale.getDefault()));
//        //return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(apiResponse);
//        return new ResponseEntity<>(apiResponse, ErrorCode.UNSUPPORTED_MEDIA_TYPE.getHttpStatus());
//    }

}
