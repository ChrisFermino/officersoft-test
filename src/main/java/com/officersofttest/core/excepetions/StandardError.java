package com.officersofttest.core.excepetions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StandardError {

    private Integer status;
    private String message;
    private long timestamp;

    public StandardError(Integer status, String message, long timestamp) {
        super();
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }
}