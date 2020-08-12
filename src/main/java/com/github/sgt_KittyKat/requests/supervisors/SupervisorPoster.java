package com.github.sgt_KittyKat.requests.supervisors;

import com.github.sgt_KittyKat.database.models.Supervisor;

import java.sql.SQLException;

public class SupervisorPoster implements SupervisorRequest{
    public void post(Supervisor supervisor) throws SQLException {
        dao().create(supervisor);
    }
}
