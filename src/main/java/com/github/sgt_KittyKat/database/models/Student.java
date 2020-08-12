package com.github.sgt_KittyKat.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Student {
    @DatabaseField(id = true)
    private int id;
    @DatabaseField
    private String name;
    @DatabaseField
    private String surname;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private StudentsGroup group;
    public Student(int id, String name, String surname, StudentsGroup group) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.group = group;
    }

    public Student() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public StudentsGroup getGroup() {
        return group;
    }

    public void setGroup(StudentsGroup group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", group= " + group +
                '}';
    }
}
