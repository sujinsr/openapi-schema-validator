package com.example.openapi.validator.json.schema;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;

import java.util.List;
import java.util.stream.Collectors;

public class EveritJsonSchemaValidator implements JsonSchemaValidator {

    @Override
    public JsonSchemaValidatorResult validate(String schema, String json) {
        JSONObject schemaObject = new JSONObject(schema);
        JSONObject jsonObject = new JSONObject(json);

        SchemaLoader schemaLoader = SchemaLoader.builder().schemaJson(schemaObject).draftV7Support().build();
        Schema schemaValidator = schemaLoader.load().build();

        JsonSchemaValidatorResult result = new JsonSchemaValidatorResult();
        try {
            schemaValidator.validate(jsonObject);
        } catch (ValidationException ve) {
            errorMessageResolver(ve, result);
        }

        return result;
    }

    private void errorMessageResolver(ValidationException exception, JsonSchemaValidatorResult result) {
        if (exception.getCausingExceptions() != null && !exception.getCausingExceptions().isEmpty()) {
            List<String> errors = exception.getCausingExceptions().stream()
                    .map(e -> e.toJSON().toString())
                    .collect(Collectors.toList());
            result.add(errors);
        } else if (exception.getErrorMessage() != null) {
            result.add(exception.getErrorMessage());
        } else {
            result.add("Unknown schema validation error");
        }
    }

}
