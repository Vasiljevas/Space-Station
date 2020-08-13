package com.company;

public class InvalidNameException extends Exception {
    public InvalidNameException() {
        super("Invalid name. It should not have any commas");
    }
    public InvalidNameException(String msg) {
        super(msg);
    }
}
