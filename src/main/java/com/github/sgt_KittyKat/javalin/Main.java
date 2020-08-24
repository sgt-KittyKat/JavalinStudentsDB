package com.github.sgt_KittyKat.javalin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sgt_KittyKat.database.models.StudentsGroup;
import com.github.sgt_KittyKat.database.models.Student;
import com.github.sgt_KittyKat.database.models.Supervisor;
import com.github.sgt_KittyKat.database.models.Teacher;
import com.github.sgt_KittyKat.universalRequests.Crud;
import io.javalin.Javalin;

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
        ObjectMapper mp = new ObjectMapper();
        app.get("/students", context ->{

            List<Student> students = crud.getAll(Student.class);

            context.result(mp.writeValueAsString(students));
        });


        app.get("/student/:id", context ->{

            Integer id = Integer.parseInt(context.pathParam("id"));
            context.result(mp.writeValueAsString(crud.get(id, Student.class)));
        });

        app.delete("/student/:id", context -> {

            Integer id = Integer.parseInt(context.pathParam("id"));
            crud.delete(id, Student.class);
            context.result("deleted student " + id);
        });

        app.patch("/student", context -> {
            Scanner scanner = new Scanner(context.body());
            Integer id = Integer.parseInt(scanner.nextLine());
            String name = scanner.nextLine();
            String surname = scanner.nextLine();
            Integer groupId = Integer.parseInt(scanner.nextLine());
            Integer superId = Integer.parseInt(scanner.nextLine());

            Crud<StudentsGroup> groupCrud = new Crud<>();
            Crud<Supervisor> superCrud = new Crud<>();

            StudentsGroup group = groupCrud.get(groupId, StudentsGroup.class);
            Supervisor supervisor = superCrud.get(superId, Supervisor.class);

            crud.patch(new Student(id, name, surname, group, supervisor), Student.class);
            context.result("patched student " + id);
        });
        app.post("/student", context -> {

            Scanner scanner = new Scanner(context.body());
            Integer id = Integer.parseInt(scanner.nextLine());
            String name = scanner.nextLine();
            String surname = scanner.nextLine();
            Integer groupId = Integer.parseInt(scanner.nextLine());
            Integer superId = Integer.parseInt(scanner.nextLine());

            Crud<StudentsGroup> groupCrud = new Crud<>();
            Crud<Supervisor> superCrud = new Crud<>();

            StudentsGroup group = groupCrud.get(groupId, StudentsGroup.class);
            Supervisor supervisor = superCrud.get(superId, Supervisor.class);

            crud.post(new Student(id, name, surname, group, supervisor), Student.class);
            context.result("Posted student " + id);
        });
    }
    public static void javalinGroupsSetup(Javalin app) {
        Crud<StudentsGroup> crud = new Crud<>();
        ObjectMapper om = new ObjectMapper();
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
        app.patch("/group", context -> {
            Scanner scanner = new Scanner(context.body());
            Integer id = Integer.parseInt(scanner.nextLine());
            String name = scanner.nextLine();
            Integer teacherId = Integer.parseInt(scanner.nextLine());

            Crud<Teacher> teacherCrud= new Crud<>();

            Teacher teacher = teacherCrud.get(teacherId, Teacher.class);

            crud.patch(new StudentsGroup(id, name, teacher), StudentsGroup.class);

            context.result("patched group " + id);
        });
        app.post("/group", context -> {
            Scanner scanner = new Scanner(context.body());
            Integer id = Integer.parseInt(scanner.nextLine());
            String name = scanner.nextLine();
            Integer teacherId = Integer.parseInt(scanner.nextLine());

            Crud<Teacher> teacherCrud= new Crud<>();

            Teacher teacher = teacherCrud.get(teacherId, Teacher.class);

            crud.post(new StudentsGroup(id, name, teacher), StudentsGroup.class);
            context.result("posted group " + id);
        });
    }
    public static void javalinTeacherSetup(Javalin app) {
        Crud<Teacher> crud = new Crud<>();
        ObjectMapper om = new ObjectMapper();
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
            Scanner scanner = new Scanner(context.body());

            Integer id = Integer.parseInt(scanner.nextLine());
            String name = scanner.nextLine();
            String surname = scanner.nextLine();

            crud.post(new Teacher(id, name, surname), Teacher.class);
            context.result("posted teacher " + id);
        });
        app.patch("/teacher", context -> {
            Scanner scanner = new Scanner(context.body());

            Integer id = Integer.parseInt(scanner.nextLine());
            String name = scanner.nextLine();
            String surname = scanner.nextLine();

            crud.patch(new Teacher(id, name, surname), Teacher.class);
            context.result("patched teacher " + id);
        });
    }
    public static void javalinSupervisorSetup(Javalin app) {
        Crud<Supervisor> crud = new Crud<>();
        ObjectMapper om = new ObjectMapper();
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
            Scanner scanner = new Scanner(context.body());

            Integer id = Integer.parseInt(scanner.nextLine());
            String name = scanner.nextLine();
            String surname = scanner.nextLine();

            crud.post(new Supervisor(id, name, surname), Supervisor.class);
            context.result("posted supervisor" + id);
        });
        app.patch("/supervisor", context -> {
            Scanner scanner = new Scanner(context.body());

            Integer id = Integer.parseInt(scanner.nextLine());
            String name = scanner.nextLine();
            String surname = scanner.nextLine();
            crud.patch(new Supervisor(id, name, surname), Supervisor.class);
            context.result("patched supervisor " + id);
        });
    }
}
