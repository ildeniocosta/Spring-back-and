package com.ildenio.curso.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
    private List<FieldMessage> Errors = new ArrayList<>();

    public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }
    public List<FieldMessage> getErrors() {
        return Errors;
    }

    public void addError(String fieldName,String messagem) {
        Errors.add(new FieldMessage(fieldName,messagem));
    }
}
