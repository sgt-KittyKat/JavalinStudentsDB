package com.github.sgt_KittyKat.requests.supervisors;


import com.github.sgt_KittyKat.database.config.DatabaseTools;
import com.github.sgt_KittyKat.database.models.Supervisor;
import com.github.sgt_KittyKat.requests.Request;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.spring.DaoFactory;

import java.sql.SQLException;

public interface SupervisorRequest extends Request {
    default Dao<Supervisor, Integer> dao() throws SQLException {
        return DaoFactory.createDao(DatabaseTools.CONNECTION_SOURCE,Supervisor.class);
    }
}
