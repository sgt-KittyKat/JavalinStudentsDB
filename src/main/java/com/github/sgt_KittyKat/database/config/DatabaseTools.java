package com.github.sgt_KittyKat.database.config;

import com.github.sgt_KittyKat.database.models.StudentsGroup;
import com.github.sgt_KittyKat.database.models.Student;
import com.github.sgt_KittyKat.database.models.Teacher;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DatabaseTools {
    public static String JDBC_CONNECTION_STRING = "jdbc:sqlite:C:\\SQL\\DBs\\JavalinStudents.db";

    public static ConnectionSource CONNECTION_SOURCE;
    static {
        try {
            CONNECTION_SOURCE = new JdbcConnectionSource(JDBC_CONNECTION_STRING);
            TableUtils.createTableIfNotExists(CONNECTION_SOURCE, Student.class);
            TableUtils.createTableIfNotExists(CONNECTION_SOURCE, StudentsGroup.class);
            TableUtils.createTableIfNotExists(CONNECTION_SOURCE, Teacher.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
