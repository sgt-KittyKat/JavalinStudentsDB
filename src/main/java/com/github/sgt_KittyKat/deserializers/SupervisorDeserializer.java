package com.github.sgt_KittyKat.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.sgt_KittyKat.database.models.Supervisor;

import java.io.IOException;

public class SupervisorDeserializer extends StdDeserializer<Supervisor> {
    public SupervisorDeserializer() {
        super(Supervisor.class);
    }
    @Override
    public Supervisor deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode root = jsonParser.getCodec().readTree(jsonParser);
        String name = root.get("name").asText();
        String surname = root.get("surname").asText();
        return new Supervisor(name, surname);
    }
}
