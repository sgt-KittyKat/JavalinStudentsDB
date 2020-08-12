package com.github.sgt_KittyKat.requests.students;

import com.github.sgt_KittyKat.database.models.Student;

import java.sql.SQLException;
import java.util.List;

public class StudentGetter implements StudentRequest {
    public List<Student> getAll() throws  SQLException{
        return dao().queryForAll();
    }
    public Student get(int id) throws SQLException {
        return dao().queryForId(id);
    }
}
