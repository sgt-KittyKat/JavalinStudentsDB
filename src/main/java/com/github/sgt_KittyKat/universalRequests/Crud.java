package com.github.sgt_KittyKat.universalRequests;

import com.github.sgt_KittyKat.database.config.DatabaseTools;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.spring.DaoFactory;

import java.sql.SQLException;
import java.util.List;

public class Crud <temp> {
    private Dao<temp, Integer> dao(Class daoClass) throws SQLException {
        return DaoFactory.createDao(DatabaseTools.CONNECTION_SOURCE, daoClass);
    }
    public void post(temp object, Class daoClass) throws SQLException {
        dao(daoClass).create(object);
    }
    public void patch(temp object, Class daoClass) throws SQLException {
        dao(daoClass).update(object);
    }
    public temp get(int id, Class daoClass) throws SQLException {
        return dao(daoClass).queryForId(id);
    }
    public List<temp> getAll(Class daoClass) throws SQLException {
        return dao(daoClass).queryForAll();
    }
    public void delete(int id, Class daoClass) throws SQLException {
        dao(daoClass).deleteById(id);
    }
}
