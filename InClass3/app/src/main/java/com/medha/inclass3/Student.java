package com.medha.inclass3;

import java.io.Serializable;

/**
 * Created by Prathyusha on 2/1/16.
 */
public class Student implements Serializable {

    private String name,eMail,programmingLanguage;
    private boolean acctState;
    private int mood;

    public Student(String name, String eMail, String programmingLanguage, boolean acctState, int mood) {
        this.name = name;
        this.eMail = eMail;
        this.programmingLanguage = programmingLanguage;
        this.acctState = acctState;
        this.mood = mood;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", eMail='" + eMail + '\'' +
                ", programmingLanguage='" + programmingLanguage + '\'' +
                ", acctState=" + acctState +
                ", mood=" + mood +
                '}';
    }

    public String getName() {
        return name;
    }

    public String geteMail() {
        return eMail;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public boolean isAcctState() {
        return acctState;
    }

    public int getMood() {
        return mood;
    }
}
