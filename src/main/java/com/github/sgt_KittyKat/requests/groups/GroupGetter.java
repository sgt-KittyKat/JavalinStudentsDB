package com.github.sgt_KittyKat.requests.groups;

import com.github.sgt_KittyKat.database.models.StudentsGroup;


import java.sql.SQLException;
import java.util.List;

public class GroupGetter implements GroupRequest {
    public StudentsGroup get(int id) throws SQLException {
        return dao().queryForId(id);
    }
    public List<StudentsGroup> getAll() throws SQLException {
        return dao().queryForAll();
    }
}
