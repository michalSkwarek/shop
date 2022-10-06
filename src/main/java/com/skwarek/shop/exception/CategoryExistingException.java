package com.skwarek.shop.exception;

public class CategoryExistingException extends RuntimeException {

    public CategoryExistingException(String message) {
        super(message);
    }

}
