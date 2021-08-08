package com.example.openapi.validator.json.schema;

import java.util.ArrayList;
import java.util.List;

public class JsonSchemaValidatorResult {

    List<String> errors = new ArrayList<>();

    public void add(List<String> e) {
       errors.addAll(e);
    }

    public void add(String e) {
        errors.add(e);
    }

    public List<String> getErrors() {
        return errors;
    }
}
