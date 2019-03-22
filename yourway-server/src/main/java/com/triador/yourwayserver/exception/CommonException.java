package com.triador.yourwayserver.exception;

import com.triador.yourwayserver.enumeration.ErrorMessage;

public class CommonException extends RuntimeException {

    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonException(String message) {
        super(message);
    }
}
