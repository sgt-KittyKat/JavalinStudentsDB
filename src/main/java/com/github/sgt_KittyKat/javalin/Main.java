package com.github.sgt_KittyKat.javalin;

import com.github.sgt_KittyKat.database.models.StudentsGroup;
import com.github.sgt_KittyKat.database.models.Student;
import com.github.sgt_KittyKat.database.models.Supervisor;
import com.github.sgt_KittyKat.database.models.Teacher;
import com.github.sgt_KittyKat.universalRequests.Crud;
import io.javalin.Javalin;

import java.util.List;

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
        app.get("/students", context ->{

            String result = new String();
            List<Student> students = crud.getAll(Student.class);
            for (Student student : students) {
                result += student.toString() + "\n";
            }
            context.result(result);
        });


        app.get("/student/:id", context ->{

            Integer id = Integer.parseInt(context.pathParam("id"));
            context.result(crud.get(id, Student.class).toString());
        });

        app.delete("/student/:id", context -> {

            Integer id = Integer.parseInt(context.pathParam("id"));
            crud.delete(id, Student.class);
            context.result("deleted student " + id);
        });

        app.patch("/student/:id/:name/:surname/:groupId/:supervisorId", context -> {

            Integer id = Integer.parseInt(context.pathParam("id"));

            String name = context.pathParam("name");
            String surname = context.pathParam("surname");

            Integer groupId = Integer.parseInt(context.pathParam("groupId"));
            Crud <StudentsGroup> groupCrud = new Crud<>();
            StudentsGroup group = groupCrud.get(groupId, StudentsGroup.class);

            Integer superId = Integer.parseInt(context.pathParam("supervisorId"));
            Crud <Supervisor> superCrud = new Crud<>();
            Supervisor supervisor = superCrud.get(groupId, Supervisor.class);
            crud.patch(new Student(id, name, surname, group, supervisor), Student.class);
        });
        app.post("/student/:id/:name/:surname/:groupId/:supervisorId", context -> {

            Integer id = Integer.parseInt(context.pathParam("id"));

            String name = context.pathParam("name");
            String surname = context.pathParam("surname");

            Integer groupId = Integer.parseInt(context.pathParam("groupId"));
            Crud <StudentsGroup> groupCrud = new Crud<>();
            StudentsGroup group = groupCrud.get(groupId, StudentsGroup.class);

            Integer superId = Integer.parseInt(context.pathParam("supervisorId"));
            Crud <Supervisor> superCrud = new Crud<>();
            Supervisor supervisor = superCrud.get(groupId, Supervisor.class);
            crud.post(new Student(id, name, surname, group, supervisor), Student.class);
        });
    }
    public static void javalinGroupsSetup(Javalin app) {
        Crud<StudentsGroup> crud = new Crud<>();
        app.get("/groups", context -> {

           List<StudentsGroup> groups = crud.getAll(StudentsGroup.class);
           String result = new String();
           for (StudentsGroup group : groups) {
               result += group.toString() + "\n";
           }
           context.result(result);
        });

        app.get("/group/:id", context -> {

            Integer id = Integer.parseInt(context.pathParam("id"));
            context.result(crud.get(id, StudentsGroup.class).toString());
        });
        app.delete("/group/:id", context -> {

            Integer id = Integer.parseInt(context.pathParam("id"));
            crud.delete(id, StudentsGroup.class);
            context.result("deleted group" + id);
        });
        app.patch("/group/:id/:name/:teacherId", context -> {

            Integer id = Integer.parseInt(context.pathParam("id"));

            String name = context.pathParam("name");

            Integer teacherId = Integer.parseInt(context.pathParam("teacherId"));
            Crud<Teacher> teachercrud = new Crud<Teacher>();
            Teacher teacher = teachercrud.get(teacherId, Teacher.class);

            crud.patch(new StudentsGroup(id, name, teacher), StudentsGroup.class);
            context.result("patched group " + id);
        });
        app.post("/group/:id/:name/:teacherId", context -> {

            Integer id = Integer.parseInt(context.pathParam("id"));

            String name = context.pathParam("name");

            Integer teacherId = Integer.parseInt(context.pathParam("teacherId"));
            Crud<Teacher> teachercrud = new Crud<Teacher>();
            Teacher teacher = teachercrud.get(teacherId, Teacher.class);

            crud.post(new StudentsGroup(id, name, teacher), StudentsGroup.class);
            context.result("posted group " + id);
        });
    }
    public static void javalinTeacherSetup(Javalin app) {
        Crud<Teacher> crud = new Crud<>();
        app.get("/teacher/:id", context -> {

            Integer id = Integer.parseInt(context.pathParam("id"));
            context.result(crud.get(id, Teacher.class).toString());
        });
        app.get("/teachers", context -> {

            List <Teacher> teachers = crud.getAll(Teacher.class);
            String result = new String();
            for (Teacher teacher : teachers) {
                result += teacher.toString() + "\n";
            }
            context.result(result);
        });
        app.delete("/teacher/:id", context -> {

            Integer id = Integer.parseInt(context.pathParam("id"));
            crud.delete(id, Teacher.class);
            context.result("deleted teacher " + id);
        });
        app.post("/teacher/:id/:name/:surname" , context -> {

            Integer id = Integer.parseInt(context.pathParam("id"));
            String name = context.pathParam("name");
            String surname = context.pathParam("surname");
            crud.post(new Teacher(id, name, surname), Teacher.class);
            context.result("posted teacher " + id);
        });
        app.patch("/teacher/:id/:name/:surname", context -> {

            Integer id = Integer.parseInt(context.pathParam("id"));
            String name = context.pathParam("name");
            String surname = context.pathParam("surname");
            crud.patch(new Teacher(id, name, surname), Teacher.class);
            context.result("patched teacher " + id);
        });
    }
    public static void javalinSupervisorSetup(Javalin app) {
        Crud<Supervisor> crud = new Crud<>();
        app.get("/supervisors", context -> {

            List<Supervisor> supervisors = crud.getAll(Supervisor.class);
            String result = new String();
            for (Supervisor supervisor : supervisors) {
                result += supervisor.toString() + "\n";
            }
            context.result(result);
        });
        app.get("/supervisor/:id" , context -> {

            Integer id = Integer.parseInt(context.pathParam("id"));
            context.result(crud.get(id, Supervisor.class).toString());
        });
        app.delete("supervisor/:id", context -> {

            Integer id = Integer.parseInt(context.pathParam("id"));
            crud.delete(id, Supervisor.class);
            context.result("deleted supervisor " + id);
        });
        app.post("supervisor/:id/:name/:surname" , context -> {

            Integer id = Integer.parseInt(context.pathParam("id"));
            String name = context.pathParam("name");
            String surname = context.pathParam("surname");
            crud.post(new Supervisor(id, name, surname), Supervisor.class);
            context.result("posted supervisor" + id);
        });
        app.patch("supervisor/:id/:name/:surname", context -> {
            Integer id = Integer.parseInt(context.pathParam("id"));
            String name = context.pathParam("name");
            String surname = context.pathParam("surname");
            crud.patch(new Supervisor(id, name, surname), Supervisor.class);
            context.result("patched supervisor " + id);
        });
    }
}
