package com.github.sgt_KittyKat.Requests.Students;

import com.github.sgt_KittyKat.Database.Config.DatabaseTools;
import com.github.sgt_KittyKat.Database.Models.Group;
import com.github.sgt_KittyKat.Database.Models.Student;
import com.github.sgt_KittyKat.Requests.Groups.GroupGetter;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.spring.DaoFactory;

import java.sql.SQLException;

public class StudentPatcher implements StudentRequest {
    public void patch(Student student) throws SQLException {
        GroupGetter getter = new GroupGetter();
        student.setGroup(getter.get(student.getGroupId()));
        dao().update(student);
    }
}
