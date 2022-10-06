package com.skwarek.shop.exception;

public class AccountWrongPasswordException extends RuntimeException {

    public AccountWrongPasswordException(String message) {
        super(message);
    }

}
