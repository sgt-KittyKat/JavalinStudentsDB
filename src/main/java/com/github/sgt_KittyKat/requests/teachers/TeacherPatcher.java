package com.github.sgt_KittyKat.requests.teachers;

import com.github.sgt_KittyKat.database.models.Teacher;

import java.sql.SQLException;

public class TeacherPatcher implements TeacherRequest {
    public void patch(Teacher teacher) throws SQLException {
        dao().update(teacher);
    }
}
