package com.example.openapi.parser;

import java.util.ArrayList;
import java.util.List;

public class SchemaParserResult {

    private final List<String> errors = new ArrayList<>();

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public void add(String e) {
        errors.add(e);
    }

    public void add(List<String> e) {
        errors.addAll(e);
    }

    public List<String> getErrors() {
        return errors;
    }

}
