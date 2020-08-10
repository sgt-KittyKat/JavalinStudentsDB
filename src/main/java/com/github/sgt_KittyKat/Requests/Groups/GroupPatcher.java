package com.github.sgt_KittyKat.Requests.Groups;

import com.github.sgt_KittyKat.Database.Models.Group;

import java.sql.SQLException;

public class GroupPatcher implements GroupRequest {
    public void patch(Group group) throws SQLException {
        dao().update(group);
    }
}
