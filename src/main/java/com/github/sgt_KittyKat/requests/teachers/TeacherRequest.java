package com.github.sgt_KittyKat.requests.teachers;

import com.github.sgt_KittyKat.database.config.DatabaseTools;
import com.github.sgt_KittyKat.database.models.Teacher;
import com.github.sgt_KittyKat.requests.Request;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.spring.DaoFactory;

import java.sql.SQLException;

public interface TeacherRequest extends Request {
    default Dao<Teacher, Integer> dao() throws SQLException {
        return DaoFactory.createDao(DatabaseTools.CONNECTION_SOURCE, Teacher.class);
    }
}
