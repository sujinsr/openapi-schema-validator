package com.example.openapi.validator;

import com.example.openapi.validator.json.schema.EveritJsonSchemaValidator;
import com.example.openapi.validator.json.schema.JsonSchemaValidator;
import com.example.openapi.validator.json.schema.JsonSchemaValidatorResult;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.util.Json;

import java.io.IOException;

/**
 * A Open API json validator to validate json document against Open api specification.
 * It converts open api schema into json-schema then validate using json schema validator.
 *
 * Default it uses Everit json-schema validator library for validation and it also supports
 * for NetworkNt json-schema validator
 *
 */
public class OpenApiJsonValidator {

    private final JsonSchemaValidator jsonSchemaValidator;

    public OpenApiJsonValidator() {
        this.jsonSchemaValidator = new EveritJsonSchemaValidator();
    }

    public JsonValidatorResult validate(String schema, String json, String root) throws IOException {
        JsonNode jsonSchemaNode = generateJsonSchemaNode(schema, root);
        JsonNode jsonNode = toJsonNode(json);

        String convertedSchema = Json.mapper().writeValueAsString(jsonSchemaNode);
        String convertedJson = Json.mapper().writeValueAsString(jsonNode);

        JsonSchemaValidatorResult validatorResult = jsonSchemaValidator.validate(convertedSchema, convertedJson);
        return resultMessageResolver(validatorResult);
    }

    private JsonNode generateJsonSchemaNode(String schema, String root) throws IOException {
        JsonNode schemaNode = toJsonNode(schema);
        JsonNode componentsNode = schemaNode.get("components");
        JsonNode rootNode = componentsNode.get("schemas").get(root);

        ObjectNode jsonSchemaNode = rootNode.deepCopy();
        jsonSchemaNode.set("components", componentsNode);
        return jsonSchemaNode;
    }

    private JsonNode toJsonNode(String value) throws IOException {
        return Json.mapper().readTree(value);
    }

    private JsonValidatorResult resultMessageResolver(JsonSchemaValidatorResult validatorResult) {
        JsonValidatorResult result = new JsonValidatorResult();
        result.add(validatorResult.getErrors());
        return result;
    }

}
