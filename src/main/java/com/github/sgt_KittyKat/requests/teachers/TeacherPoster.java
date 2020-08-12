package com.github.sgt_KittyKat.requests.teachers;

import com.github.sgt_KittyKat.database.models.Teacher;

import java.sql.SQLException;

public class TeacherPoster implements TeacherRequest {
    public void post(Teacher teacher) throws SQLException {
        dao().create(teacher);
    }
}
