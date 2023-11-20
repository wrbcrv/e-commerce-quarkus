package dev.application.validation;

public class ValidationException extends RuntimeException {

    private String field;

    public ValidationException(String field, String message) {
        super(message);
        this.field = field;
    }

    public String getFieldName() {
        return field;
    }
}