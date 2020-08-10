package com.github.sgt_KittyKat.Requests.Students;

import com.github.sgt_KittyKat.Database.Models.Student;
import com.github.sgt_KittyKat.Requests.Groups.GroupGetter;

import java.sql.SQLException;

public class StudentPoster implements StudentRequest {
    public void post(Student student) throws SQLException {
        GroupGetter getter = new GroupGetter();
        student.setGroup(getter.get(student.getGroupId()));
        dao().create(student);
    }
}
