package com.example.openapi.parser;

import io.swagger.parser.OpenAPIParser;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.core.models.SwaggerParseResult;

import java.util.Optional;

/**
 * An Open api schema parser to parse and validate open api specification document.
 * It uses swagger parser library for the validation.
 *
 */
public class OpenApiSchemaParser {

    private OpenAPI openAPI;

    public SchemaParserResult parse(String schema) {
        SwaggerParseResult parserResult = new OpenAPIParser().readContents(schema, null, null);
        openAPI = parserResult.getOpenAPI();

        SchemaParserResult result = new SchemaParserResult();
        if (!parserResult.getMessages().isEmpty()) {
            result.add(parserResult.getMessages());
        }

        return result;
    }

    public OpenAPI getOpenAPI() {
        return openAPI;
    }

}
