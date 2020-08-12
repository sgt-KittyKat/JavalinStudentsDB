package com.github.sgt_KittyKat.requests.students;

import com.github.sgt_KittyKat.database.config.DatabaseTools;

import com.github.sgt_KittyKat.database.models.Student;
import com.github.sgt_KittyKat.requests.Request;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import java.sql.SQLException;

public interface StudentRequest extends Request {
    default Dao<Student, Integer> dao() throws SQLException {
        return DaoManager.createDao(DatabaseTools.CONNECTION_SOURCE, Student.class);
    }
}
