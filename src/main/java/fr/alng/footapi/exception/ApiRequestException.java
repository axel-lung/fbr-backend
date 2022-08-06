/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.exception;

public class ApiRequestException extends RuntimeException{

    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
