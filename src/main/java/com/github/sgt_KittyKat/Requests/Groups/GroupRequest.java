package com.github.sgt_KittyKat.Requests.Groups;

import com.github.sgt_KittyKat.Database.Config.DatabaseTools;
import com.github.sgt_KittyKat.Database.Models.Group;
import com.github.sgt_KittyKat.Requests.Request;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import java.sql.SQLException;

public interface GroupRequest extends Request {
    default Dao<Group, Integer> dao() throws SQLException {
        return DaoManager.createDao(DatabaseTools.CONNECTION_SOURCE, Group.class);
    }
}
