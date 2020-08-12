package com.github.sgt_KittyKat.javalin;

import com.github.sgt_KittyKat.database.models.StudentsGroup;
import com.github.sgt_KittyKat.database.models.Student;
import com.github.sgt_KittyKat.requests.groups.GroupDeleter;
import com.github.sgt_KittyKat.requests.groups.GroupGetter;
import com.github.sgt_KittyKat.requests.groups.GroupPatcher;
import com.github.sgt_KittyKat.requests.groups.GroupPoster;
import com.github.sgt_KittyKat.requests.students.StudentDeleter;
import com.github.sgt_KittyKat.requests.students.StudentGetter;
import com.github.sgt_KittyKat.requests.students.StudentPatcher;
import com.github.sgt_KittyKat.requests.students.StudentPoster;
import io.javalin.Javalin;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create();
        javalinStudentsSetup(app);
        javalinGroupsSetup(app);
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
            patcher.patch(new Student(id, name, surname, groupId));
            context.result("patched student " + id);
        });

        app.post("/student/:id/:name/:surname/:groupId", context -> {
            StudentPoster poster = new StudentPoster();
            Integer id = Integer.parseInt(context.pathParam("id"));
            String name = context.pathParam("name");
            String surname = context.pathParam("surname");
            Integer groupId = Integer.parseInt(context.pathParam("groupId"));
            poster.post(new Student(id, name, surname, groupId));
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
        app.patch("/group/:id/:name", context -> {
            GroupPatcher patcher = new GroupPatcher();
            Integer id = Integer.parseInt(context.pathParam("id"));
            String name = context.pathParam("name");
            patcher.patch(new StudentsGroup(id, name));
            context.result("patched group " + id);
        });
        app.post("/group/:id/:name", context -> {
            GroupPoster poster = new GroupPoster();
            Integer id = Integer.parseInt(context.pathParam("id"));
            String name = context.pathParam("name");
            poster.post(new StudentsGroup(id, name));
            context.result("posted group " + id);
        });
    }
}
