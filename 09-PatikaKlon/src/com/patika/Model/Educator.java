package com.patika.Model;

public class Educator extends User{

    public Educator() {
    }
    public Educator(User u) {
    }

    public Educator(int id, String name, String uname, String pass, String type) {
        super(id, name, uname, pass, type);
    }
}
