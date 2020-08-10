package com.github.sgt_KittyKat.Requests.Groups;

import com.github.sgt_KittyKat.Database.Models.Group;

import java.sql.SQLException;

public class GroupPoster implements GroupRequest {
    public void post(Group group) throws SQLException {
        dao().create(group);
    }
}
