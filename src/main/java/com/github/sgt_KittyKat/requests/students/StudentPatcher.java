package com.github.sgt_KittyKat.requests.students;

import com.github.sgt_KittyKat.database.models.Student;
import com.github.sgt_KittyKat.requests.groups.GroupGetter;

import java.sql.SQLException;

public class StudentPatcher implements StudentRequest {
    public void patch(Student student) throws SQLException {
        GroupGetter getter = new GroupGetter();
        student.setGroup(getter.get(student.getGroupId()));
        dao().update(student);
    }
}
