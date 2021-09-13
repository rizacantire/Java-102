package com.patika.View;

import com.patika.Helper.Config;
import com.patika.Helper.Helper;

import javax.swing.*;

public class EducatorGUI extends JFrame  {
    private JPanel wrapper;

    public EducatorGUI(){
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
