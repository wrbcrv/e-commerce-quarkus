package dev.application.validation;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends dev.application.util.Error {

    record FieldError(String field, String message) {
        
    }

    private List<FieldError> errors = null;

    public ValidationError(String code, String message) {
        super(code, message);
    }

    public void addFieldError(String field, String message) {
        if (errors == null)
            errors = new ArrayList<FieldError>();
        errors.add(new FieldError(field, message));
    }

    public List<FieldError> getErrors() {
        return errors.stream().toList();
    }
}