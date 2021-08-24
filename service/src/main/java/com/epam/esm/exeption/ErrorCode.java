package com.epam.esm.exeption;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    TAG_NOT_FOUND(40401, "tag.not.found", HttpStatus.NOT_FOUND),
    TAG_FIELD_INVALID(40001, "tag.field.invalid", HttpStatus.BAD_REQUEST),
    TAG_ALREADY_EXIST(40002, "tag.already.exist", HttpStatus.BAD_REQUEST),
    INTERNAL_ERROR(50001, "internal.error", HttpStatus.INTERNAL_SERVER_ERROR);

    private Integer code;
    private String message;
    private HttpStatus httpStatus;

    ErrorCode(Integer code, String messageCode, HttpStatus httpStatus){
        this.code = code;
        this.message = messageCode;
        this.httpStatus = httpStatus;

    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessageCode() {
        return message;
    }

    public void setMessageCode(String messageCode) {
        this.message = messageCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
