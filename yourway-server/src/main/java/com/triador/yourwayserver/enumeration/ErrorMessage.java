package com.triador.yourwayserver.enumeration;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorMessage {

    ERROR_NOT_FOUND(10003, "ERROR_NOT_FOUND", "Ничего не найдено");

    private final int id;
    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorMessage(int id, String code, String message) {
        this(id, code, message, HttpStatus.BAD_REQUEST);
    }

    ErrorMessage(int id, String code, String message, HttpStatus httpStatus) {
        this.id = id;
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
