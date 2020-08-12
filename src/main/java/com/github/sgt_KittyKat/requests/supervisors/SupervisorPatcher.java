package com.github.sgt_KittyKat.requests.supervisors;

import com.github.sgt_KittyKat.database.models.Supervisor;

import java.sql.SQLException;

public class SupervisorPatcher implements SupervisorRequest{
    public void patch(Supervisor supervisor) throws SQLException {
        dao().update(supervisor);
    }
}
