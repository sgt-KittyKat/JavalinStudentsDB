package com.github.sgt_KittyKat.requests.supervisors;

import java.sql.SQLException;

public class SupervisorDeleter implements SupervisorRequest{
    public void delete(int id) throws SQLException {
        dao().deleteById(id);
    }
}
