package com.example.openapi.validator.json.schema;

public interface JsonSchemaValidator {

    JsonSchemaValidatorResult validate(String schema, String json);

}
