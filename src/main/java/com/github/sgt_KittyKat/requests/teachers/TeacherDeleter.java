package com.github.sgt_KittyKat.requests.teachers;

import java.sql.SQLException;

public class TeacherDeleter implements TeacherRequest {
    public void delete(int id) throws SQLException {
        dao().deleteById(id);
    }
}
