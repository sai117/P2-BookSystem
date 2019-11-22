package com.tuny.demo.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ParamCheckException extends RuntimeException {

    public ParamCheckException(String message) {
        super(message);
    }

    public ParamCheckException(String message, Throwable t) {
        super(message, t);
    }

}
