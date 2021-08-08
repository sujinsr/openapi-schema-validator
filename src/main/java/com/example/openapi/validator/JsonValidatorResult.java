package com.example.openapi.validator;

import java.util.ArrayList;
import java.util.List;

public class JsonValidatorResult {

    List<String> errors = new ArrayList<>();

    public void add(List<String> errors) {
        this.errors.addAll(errors);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public int errorCount() {
        return errors.size();
    }

    public List<String> getErrors() {
        return errors;
    }

}
