package com.example.openapi.validator.json.schema;

import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;

public class NetworkNtJsonSchemaValidator implements JsonSchemaValidator {

    private final JsonSchemaFactory jsonSchemaFactory;

    public NetworkNtJsonSchemaValidator() {
        jsonSchemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
    }

    public NetworkNtJsonSchemaValidator(SpecVersion.VersionFlag versionFlag) {
        jsonSchemaFactory = JsonSchemaFactory.getInstance(versionFlag);
    }

    @Override
    public JsonSchemaValidatorResult validate(String schema, String json) {
        return null;
    }

/*    @Override
    public JsonSchemaValidatorResult validate(JsonNode schema, JsonNode json) {
        Set<ValidationMessage> validate = jsonSchemaFactory.getSchema(schema).validate(json);
        System.out.println(validate);
        return null;
    }*/

}
