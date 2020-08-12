package com.github.sgt_KittyKat.requests.supervisors;

import com.github.sgt_KittyKat.database.models.Supervisor;

import java.sql.SQLException;
import java.util.List;

public class SupervisorGetter implements SupervisorRequest {
    public Supervisor get(int id) throws SQLException {
        return dao().queryForId(id);
    }
    public List<Supervisor> getAll() throws SQLException {
        return dao().queryForAll();
    }
}
