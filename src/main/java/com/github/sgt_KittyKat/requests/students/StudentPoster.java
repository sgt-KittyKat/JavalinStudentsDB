package com.github.sgt_KittyKat.requests.students;

import com.github.sgt_KittyKat.database.models.Student;
import com.github.sgt_KittyKat.requests.groups.GroupGetter;

import java.sql.SQLException;

public class StudentPoster implements StudentRequest {
    public void post(Student student) throws SQLException {
        GroupGetter getter = new GroupGetter();
        student.setGroup(getter.get(student.getGroupId()));
        dao().create(student);
        System.out.println(student);
    }
}
