package com.patika.Model;

public class Student extends User{
    public Student() {
    }
    public Student(User u) {
    }

    public Student(int id, String name, String uname, String pass, String type) {

        super(id, name, uname, pass, type);
    }
}
