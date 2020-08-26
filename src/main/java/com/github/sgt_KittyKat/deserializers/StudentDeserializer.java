package com.github.sgt_KittyKat.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.sgt_KittyKat.database.models.Student;
import com.github.sgt_KittyKat.database.models.StudentsGroup;
import com.github.sgt_KittyKat.database.models.Supervisor;
import com.github.sgt_KittyKat.universalRequests.Crud;

import java.io.IOException;
import java.sql.SQLException;

public class StudentDeserializer extends StdDeserializer<Student> {

    public StudentDeserializer() {
        super(Student.class);
    }

    @Override
    public Student deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode root = jsonParser.getCodec().readTree(jsonParser);
        String name = root.get("name").asText();
        String surname = root.get("surname").asText();
        int groupId = root.get("groupId").asInt();
        int superId = root.get("superId").asInt();

        Crud<StudentsGroup> gc = new Crud<>();
        Crud<Supervisor> sc = new Crud<>();
        StudentsGroup group = null;
        try {
            group = gc.get(groupId, StudentsGroup.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Supervisor supervisor = null;
        try {
            supervisor = sc.get(superId, Supervisor.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (group == null) {
            throw new IOException();
        }
        if (supervisor == null) {
            throw new IOException();
        }
        return new Student(name, surname, group, supervisor);
    }
}
