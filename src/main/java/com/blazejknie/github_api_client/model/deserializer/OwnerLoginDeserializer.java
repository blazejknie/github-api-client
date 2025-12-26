package com.blazejknie.github_api_client.model.deserializer;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.core.TreeNode;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.deser.std.StdDeserializer;

public class OwnerLoginDeserializer extends StdDeserializer<String> {

    public OwnerLoginDeserializer() {
        super(String.class);
    }

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws JacksonException {
        JsonNode jsonNode = p.readValueAsTree();
        return jsonNode.get("login").stringValue();
    }
}
