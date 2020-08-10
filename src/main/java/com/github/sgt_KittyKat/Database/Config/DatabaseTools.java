package com.github.sgt_KittyKat.Database.Config;

import com.github.sgt_KittyKat.Database.Models.Group;
import com.github.sgt_KittyKat.Database.Models.Student;
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
            TableUtils.createTableIfNotExists(CONNECTION_SOURCE, Group.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
