package com.github.sgt_KittyKat.Requests.Groups;

import java.sql.SQLException;

public class GroupDeleter implements GroupRequest {
    public void delete(int id) throws SQLException {
        dao().deleteById(id);
    }
    public void deleteAll() throws SQLException{

    }
}
