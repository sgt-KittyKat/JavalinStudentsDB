package com.github.sgt_KittyKat.javalin;

import com.github.sgt_KittyKat.database.models.StudentsGroup;
import com.github.sgt_KittyKat.database.models.Student;
import com.github.sgt_KittyKat.database.models.Teacher;
import com.github.sgt_KittyKat.requests.groups.GroupDeleter;
import com.github.sgt_KittyKat.requests.groups.GroupGetter;
import com.github.sgt_KittyKat.requests.groups.GroupPatcher;
import com.github.sgt_KittyKat.requests.groups.GroupPoster;
import com.github.sgt_KittyKat.requests.students.StudentDeleter;
import com.github.sgt_KittyKat.requests.students.StudentGetter;
import com.github.sgt_KittyKat.requests.students.StudentPatcher;
import com.github.sgt_KittyKat.requests.students.StudentPoster;
import com.github.sgt_KittyKat.requests.teachers.TeacherDeleter;
import com.github.sgt_KittyKat.requests.teachers.TeacherGetter;
import com.github.sgt_KittyKat.requests.teachers.TeacherPatcher;
import com.github.sgt_KittyKat.requests.teachers.TeacherPoster;
import io.javalin.Javalin;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create();
        javalinStudentsSetup(app);
        javalinGroupsSetup(app);
        javalinTeacherSetup(app);
        app.start(8080);
    }
    public static void javalinStudentsSetup(Javalin app) {
        app.get("/students", context ->{
            StudentGetter getter = new StudentGetter();
            String result = new String();
            List<Student> students = getter.getAll();
            for (Student student : students) {
                result += student.toString() + "\n";
            }
            context.result(result);
        });


        app.get("/student/:id", context ->{
            StudentGetter getter = new StudentGetter();
            Integer id = Integer.parseInt(context.pathParam("id"));
            context.result(getter.get(id).toString());
        });

        app.delete("/student/:id", context -> {
            StudentDeleter deleter = new StudentDeleter();
            Integer id = Integer.parseInt(context.pathParam("id"));
            deleter.delete(id);
            context.result("deleted student " + id);
        });

        app.patch("/student/:id/:name/:surname/:groupId", context -> {
            StudentPatcher patcher = new StudentPatcher();
            Integer id = Integer.parseInt(context.pathParam("id"));
            String name = context.pathParam("name");
            String surname = context.pathParam("surname");

            Integer groupId = Integer.parseInt(context.pathParam("groupId"));
            GroupGetter getter = new GroupGetter();
            StudentsGroup group = getter.get(id);

            patcher.patch(new Student(id, name, surname, group));
            context.result("patched student " + id);
        });

        app.post("/student/:id/:name/:surname/:groupId", context -> {
            StudentPoster poster = new StudentPoster();
            Integer id = Integer.parseInt(context.pathParam("id"));
            String name = context.pathParam("name");
            String surname = context.pathParam("surname");

            Integer groupId = Integer.parseInt(context.pathParam("groupId"));
            GroupGetter getter = new GroupGetter();
            StudentsGroup group = getter.get(id);

            poster.post(new Student(id, name, surname, group));
            context.result("posted student " + id);
        });
    }
    public static void javalinGroupsSetup(Javalin app) {

        app.get("/groups", context -> {
           GroupGetter getter = new GroupGetter();
           List<StudentsGroup> groups = getter.getAll();
           String result = new String();
           for (StudentsGroup group : groups) {
               result += group.toString() + "\n";
           }
           context.result(result);
        });

        app.get("/group/:id", context -> {
            GroupGetter getter = new GroupGetter();
            Integer id = Integer.parseInt(context.pathParam("id"));
            context.result(getter.get(id).toString());
        });
        app.delete("/group/:id", context -> {
            GroupDeleter deleter = new GroupDeleter();
            Integer id = Integer.parseInt(context.pathParam("id"));
            deleter.delete(id);
            context.result("deleted group" + id);
        });
        app.patch("/group/:id/:name/:teacherId", context -> {
            GroupPatcher patcher = new GroupPatcher();
            Integer id = Integer.parseInt(context.pathParam("id"));

            String name = context.pathParam("name");

            Integer teacherId = Integer.parseInt(context.pathParam("teacherId"));
            TeacherGetter getter = new TeacherGetter();
            Teacher teacher = getter.get(teacherId);

            patcher.patch(new StudentsGroup(id, name, teacher));
            context.result("patched group " + id);
        });
        app.post("/group/:id/:name/:teacherId", context -> {
            GroupPoster poster = new GroupPoster();
            Integer id = Integer.parseInt(context.pathParam("id"));

            String name = context.pathParam("name");

            Integer teacherId = Integer.parseInt(context.pathParam("teacherId"));
            TeacherGetter getter = new TeacherGetter();
            Teacher teacher = getter.get(teacherId);

            poster.post(new StudentsGroup(id, name, teacher));
            context.result("posted group " + id);
        });
    }
    public static void javalinTeacherSetup(Javalin app) {
        app.get("/teacher/:id", context -> {
            TeacherGetter getter = new TeacherGetter();
            Integer id = Integer.parseInt(context.pathParam("id"));
            context.result(getter.get(id).toString());
        });
        app.get("/teachers", context -> {
            TeacherGetter getter = new TeacherGetter();
            List <Teacher> teachers = getter.getAll();
            String result = new String();
            for (Teacher teacher : teachers) {
                result += teacher.toString() + "\n";
            }
            context.result(result);
        });
        app.delete("/teacher/:id", context -> {
            TeacherDeleter deleter = new TeacherDeleter();
            Integer id = Integer.parseInt(context.pathParam("id"));
            deleter.delete(id);
            context.result("deleted teacher " + id);
        });
        app.post("/teacher/:id/:name/:surname" , context -> {
            TeacherPoster poster = new TeacherPoster();
            Integer id = Integer.parseInt(context.pathParam("id"));
            String name = context.pathParam("name");
            String surname = context.pathParam("surname");
            poster.post(new Teacher(id, name, surname));
            context.result("posted teacher " + id);
        });
        app.patch("/teacher/:id/:name/:surname", context -> {
            TeacherPatcher patcher = new TeacherPatcher();
            Integer id = Integer.parseInt("context.pathParam");
            String name = context.pathParam("name");
            String surname = context.pathParam("surname");
            patcher.patch(new Teacher(id, name, surname));
            context.result("patched teacher " + id);
        });
    }
}
