package com.skwarek.shop.exception;

public class AccountDuplicateException extends RuntimeException {

    public AccountDuplicateException(String message) {
        super(message);
    }

}
