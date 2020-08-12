package com.github.sgt_KittyKat.requests.students;

import java.sql.SQLException;

public class StudentDeleter implements StudentRequest {
    public void delete(int id) throws SQLException {
        dao().deleteById(id);
    }

}
