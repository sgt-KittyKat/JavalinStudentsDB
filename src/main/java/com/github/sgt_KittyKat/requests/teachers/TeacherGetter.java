package com.github.sgt_KittyKat.requests.teachers;

import com.github.sgt_KittyKat.database.models.Teacher;

import java.sql.SQLException;
import java.util.List;

public class TeacherGetter implements TeacherRequest {
    public Teacher get(int id) throws SQLException {
        return dao().queryForId(id);
    }
    public List<Teacher> getAll() throws SQLException {
        return dao().queryForAll();
    }
}
