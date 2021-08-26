package com.epam.esm.exeption;

public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;
    private final Object[] params;

    public CustomException(ErrorCode errorCode, Object... params) {
        this.errorCode = errorCode;
        this.params = params;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public Object[] getParams() {
        return params;
    }
}
