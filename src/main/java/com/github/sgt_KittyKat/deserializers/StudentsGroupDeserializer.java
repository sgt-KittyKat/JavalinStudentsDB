package com.github.sgt_KittyKat.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.sgt_KittyKat.database.models.StudentsGroup;
import com.github.sgt_KittyKat.database.models.Teacher;
import com.github.sgt_KittyKat.universalRequests.Crud;

import java.io.IOException;
import java.sql.SQLException;

public class StudentsGroupDeserializer extends StdDeserializer<StudentsGroup> {
    public StudentsGroupDeserializer() {
        super(StudentsGroup.class);
    }
    @Override
    public StudentsGroup deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode root = jsonParser.getCodec().readTree(jsonParser);
        String name = root.get("name").asText();
        int teacherId = root.get("teacher").asInt();
        Crud<Teacher> teacherCrud = new Crud<>();
        Teacher teacher = null;
        try {
            teacher = teacherCrud.get(teacherId, Teacher.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (teacher == null) {
            throw new IOException();
        }
        return new StudentsGroup(name, teacher);
    }
}
