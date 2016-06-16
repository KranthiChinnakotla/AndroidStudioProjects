package com.medha.saxparsingdemo;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Prathyusha on 2/20/16.
 */
public class Persons {

    String name,department;
    int id,age;

    static public Persons createperson(JSONObject js) throws JSONException {

        Persons person = new Persons();
        person.setName(js.getString("name"));
        person.setDepartment(js.getString("department"));
        person.setAge(js.getInt("age"));
        person.setId(js.getInt("id"));
        return person;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Persons{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
