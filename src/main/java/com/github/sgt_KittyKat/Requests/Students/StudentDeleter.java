package com.github.sgt_KittyKat.Requests.Students;

import java.sql.SQLException;

public class StudentDeleter implements StudentRequest {
    public void delete(int id) throws SQLException {
        dao().deleteById(id);
    }

}
