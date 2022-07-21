package com.officersofttest.core.excepetions;

public class WrongParameter extends RuntimeException {

    public WrongParameter(String message) {
        super(message);
    }
}