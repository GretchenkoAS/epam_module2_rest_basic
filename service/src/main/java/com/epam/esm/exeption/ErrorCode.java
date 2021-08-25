package com.epam.esm.exeption;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    TAG_NOT_FOUND(40401, "tag.not.found", HttpStatus.NOT_FOUND),
    TAG_FIELD_INVALID(40001, "tag.field.invalid", HttpStatus.BAD_REQUEST),
    TAG_ALREADY_EXIST(40002, "tag.already.exist", HttpStatus.BAD_REQUEST),
    GIFT_CERTIFICATE_FIELD_INVALID(40003, "gift.certificate.field.invalid", HttpStatus.BAD_REQUEST),
    GIFT_CERTIFICATE_ALREADY_EXIST(40004, "gift.certificate.already.exist", HttpStatus.BAD_REQUEST),
    GIFT_CERTIFICATE_NOT_FOUND(40005, "gift.certificate.not.found", HttpStatus.NOT_FOUND),
    MESSAGE_NOT_READABLE(40007, "message.not.readable", HttpStatus.BAD_REQUEST),
    BAD_REQUEST(40008, "bad.request", HttpStatus.BAD_REQUEST),
    METHOD_ARGUMENT_TYPE_MISMATCH(40009, "method.argument.type.mismatch", HttpStatus.BAD_REQUEST),
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String messageCode) {
        this.message = messageCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
