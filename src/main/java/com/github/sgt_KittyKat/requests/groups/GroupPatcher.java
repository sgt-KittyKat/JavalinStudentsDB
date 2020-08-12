package com.github.sgt_KittyKat.requests.groups;

import com.github.sgt_KittyKat.database.models.StudentsGroup;

import java.sql.SQLException;

public class GroupPatcher implements GroupRequest {
    public void patch(StudentsGroup group) throws SQLException {
        dao().update(group);
    }
}
