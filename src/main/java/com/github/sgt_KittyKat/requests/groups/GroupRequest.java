package com.github.sgt_KittyKat.requests.groups;

import com.github.sgt_KittyKat.database.config.DatabaseTools;
import com.github.sgt_KittyKat.database.models.StudentsGroup;
import com.github.sgt_KittyKat.requests.Request;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import java.sql.SQLException;

public interface GroupRequest extends Request {
    default Dao<StudentsGroup, Integer> dao() throws SQLException {
        return DaoManager.createDao(DatabaseTools.CONNECTION_SOURCE, StudentsGroup.class);
    }
}
