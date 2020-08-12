package com.github.sgt_KittyKat.requests.groups;

import com.github.sgt_KittyKat.database.models.StudentsGroup;

import java.sql.SQLException;

public class GroupPoster implements GroupRequest {
    public void post(StudentsGroup group) throws SQLException {
        dao().create(group);
    }
}
