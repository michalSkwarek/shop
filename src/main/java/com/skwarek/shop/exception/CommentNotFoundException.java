package com.skwarek.shop.exception;

public class CommentNotFoundException extends RuntimeException {

    public CommentNotFoundException(String message) {
        super(message);
    }

}
