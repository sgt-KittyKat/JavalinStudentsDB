package com.github.sgt_KittyKat.Requests.Groups;

import com.github.sgt_KittyKat.Database.Models.Group;


import java.sql.SQLException;
import java.util.List;

public class GroupGetter implements GroupRequest {
    public Group get(int id) throws SQLException {
        return dao().queryForId(id);
    }
    public List<Group> getAll() throws SQLException {
        return dao().queryForAll();
    }
}
