package com.skwarek.shop.exception;

public class AccountExistingException extends RuntimeException {

    public AccountExistingException(String message) {
        super(message);
    }

}
