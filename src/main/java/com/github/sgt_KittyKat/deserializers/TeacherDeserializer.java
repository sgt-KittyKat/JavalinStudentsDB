package com.github.sgt_KittyKat.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.sgt_KittyKat.database.models.Teacher;

import java.io.IOException;

public class TeacherDeserializer extends StdDeserializer<Teacher> {
    public TeacherDeserializer() {
        super(Teacher.class);
    }
    @Override
    public Teacher deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode root = jsonParser.getCodec().readTree(jsonParser);
        String name = root.get("name").asText();
        String surname = root.get("surname").asText();
        return new Teacher(name, surname);
    }
}
