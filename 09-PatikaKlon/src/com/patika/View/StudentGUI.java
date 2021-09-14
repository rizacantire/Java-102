package com.patika.View;

import com.patika.Helper.Config;
import com.patika.Helper.Helper;
import com.patika.Model.User;

import javax.swing.*;

public class StudentGUI extends JFrame {
    private JPanel wrapper;
    private final User user;

    public StudentGUI(User user){
        this.user = user;
        add(wrapper);
        setSize(400,400);
        int x = Helper.screenCenter("x",getSize());
        int y = Helper.screenCenter("y",getSize());
        setLocation(x,y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

    }
}
