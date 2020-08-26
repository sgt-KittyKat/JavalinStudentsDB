package com.github.sgt_KittyKat.javalin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.sgt_KittyKat.database.models.StudentsGroup;
import com.github.sgt_KittyKat.database.models.Student;
import com.github.sgt_KittyKat.database.models.Supervisor;
import com.github.sgt_KittyKat.database.models.Teacher;
import com.github.sgt_KittyKat.deserializers.StudentDeserializer;
import com.github.sgt_KittyKat.deserializers.StudentsGroupDeserializer;
import com.github.sgt_KittyKat.deserializers.SupervisorDeserializer;
import com.github.sgt_KittyKat.deserializers.TeacherDeserializer;
import com.github.sgt_KittyKat.universalRequests.Crud;
import io.javalin.Javalin;
import org.eclipse.jetty.websocket.server.WebSocketHandler;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create();
        javalinStudentsSetup(app);
        javalinGroupsSetup(app);
        javalinTeacherSetup(app);
        javalinSupervisorSetup(app);
        app.start(8080);
    }
    public static void javalinStudentsSetup(Javalin app) {
        Crud<Student> crud = new Crud<>();
        ObjectMapper om = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addDeserializer(Student.class, new StudentDeserializer());

        om.registerModule(module);
        app.get("/students", context ->{

            List<Student> students = crud.getAll(Student.class);

            context.result(om.writeValueAsString(students));
        });


        app.get("/student/:id", context ->{
            Integer id = Integer.parseInt(context.pathParam("id"));
            context.result(om.writeValueAsString(crud.get(id, Student.class)));
        });

        app.delete("/student/:id", context -> {

            Integer id = Integer.parseInt(context.pathParam("id"));
            crud.delete(id, Student.class);
            context.result("deleted student " + id);
        });

        app.patch("/student/:id", context -> {
            int id = Integer.parseInt(context.pathParam("id"));
            String json = context.body();
            Student student = om.readValue(json, Student.class);
            student.setId(id);
            crud.patch(student, Student.class);
            context.result("patched student " + student.getId());
        });
        app.post("/student", context -> {

            String json = context.body();
            Student student = om.readValue(json, Student.class);

            crud.post(student, Student.class);

            context.result("Posted student " + student.getId());
        });
    }
    public static void javalinGroupsSetup(Javalin app) {
        Crud<StudentsGroup> crud = new Crud<>();
        ObjectMapper om = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(StudentsGroup.class , new StudentsGroupDeserializer());
        om.registerModule(module);
        app.get("/groups", context -> {
           List<StudentsGroup> groups = crud.getAll(StudentsGroup.class);
           context.result(om.writeValueAsString(groups));
        });

        app.get("/group/:id", context -> {

            Integer id = Integer.parseInt(context.pathParam("id"));
            context.result(om.writeValueAsString(crud.get(id, StudentsGroup.class)));
        });
        app.delete("/group/:id", context -> {

            Integer id = Integer.parseInt(context.pathParam("id"));
            crud.delete(id, StudentsGroup.class);
            context.result("deleted group" + id);
        });
        app.patch("/group/:id", context -> {
            String json = context.body();
            int id = Integer.parseInt(context.pathParam("id"));
            StudentsGroup group = om.readValue(json, StudentsGroup.class);
            group.setId(id);
            crud.patch(group, StudentsGroup.class);

            context.result("patched group " + group.getId());
        });
        app.post("/group", context -> {
            String json = context.body();
            StudentsGroup group = om.readValue(json, StudentsGroup.class);

            crud.post(group, StudentsGroup.class);
            context.result("posted group " + group.getId());
        });
    }
    public static void javalinTeacherSetup(Javalin app) {
        Crud<Teacher> crud = new Crud<>();
        ObjectMapper om = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Teacher.class, new TeacherDeserializer());
        om.registerModule(module);
        app.get("/teacher/:id", context -> {

            Integer id = Integer.parseInt(context.pathParam("id"));
            context.result(om.writeValueAsString(crud.get(id, Teacher.class)));
        });
        app.get("/teachers", context -> {

            List <Teacher> teachers = crud.getAll(Teacher.class);

            context.result(om.writeValueAsString(teachers));
        });
        app.delete("/teacher/:id", context -> {

            Integer id = Integer.parseInt(context.pathParam("id"));
            crud.delete(id, Teacher.class);
            context.result("deleted teacher " + id);
        });
        app.post("/teacher" , context -> {
            String json = context.body();
            Teacher teacher = om.readValue(json, Teacher.class);
            crud.post(teacher, Teacher.class);
            context.result("posted teacher " + teacher.getId());
        });
        app.patch("/teacher/:id", context -> {
            String json = context.body();
            Integer id = Integer.parseInt(context.pathParam("id"));
            Teacher teacher = om.readValue(json, Teacher.class);
            teacher.setId(id);
            crud.patch(teacher, Teacher.class);
            context.result("patched teacher " + id);
        });
    }
    public static void javalinSupervisorSetup(Javalin app) {
        Crud<Supervisor> crud = new Crud<>();
        ObjectMapper om = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Supervisor.class, new SupervisorDeserializer());
        app.get("/supervisors", context -> {

            List<Supervisor> supervisors = crud.getAll(Supervisor.class);

            context.result(om.writeValueAsString(supervisors));
        });
        app.get("/supervisor/:id" , context -> {

            Integer id = Integer.parseInt(context.pathParam("id"));
            context.result(om.writeValueAsString(crud.get(id, Supervisor.class)));
        });
        app.delete("/supervisor/:id", context -> {

            Integer id = Integer.parseInt(context.pathParam("id"));
            crud.delete(id, Supervisor.class);
            context.result("deleted supervisor " + id);
        });
        app.post("/supervisor" , context -> {
            String json = context.body();

            Supervisor supervisor = om.readValue(json, Supervisor.class);
            crud.post(supervisor, Supervisor.class);

            context.result("posted supervisor" + supervisor.getId());
        });
        app.patch("/supervisor/:id", context -> {

            String json = context.body();
            Integer id = Integer.parseInt(context.pathParam("id"));
            Supervisor supervisor = om.readValue(json, Supervisor.class);

            crud.post(supervisor, Supervisor.class);

            context.result("patched supervisor " + id);
        });
    }
}
