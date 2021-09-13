package com.patika.View;

import com.patika.Helper.Config;
import com.patika.Helper.Helper;
import com.patika.Helper.Item;
import com.patika.Model.Course;
import com.patika.Model.Operator;
import com.patika.Model.Patika;
import com.patika.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class UpdateCourseGui extends JFrame{
    private JPanel wrapper;
    private JTextField fld_course_name;
    private JTextField fld_course_lang;
    private JComboBox cmb_course_patika;
    private JComboBox cmb_course_educator;
    private JButton btn_update_course;
    private Course course;

    public UpdateCourseGui(Course course) {
        this.course = course;
        add(wrapper);
        setSize(600,400);
        setLocation(Helper.screenCenter("x",getSize()),Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        fld_course_name.setText(course.getName());
        fld_course_lang.setText(course.getLang());
        loadPatikaCombo();
        loadUserCombo();
        cmb_course_educator.setSelectedItem(course.getEducater());
        cmb_course_patika.setSelectedItem(new Item(course.getPatika().getId(),course.getPatika().getName()));

        btn_update_course.addActionListener(e -> {
            if (Helper.fieldIsEmpty(this.fld_course_lang)&&Helper.fieldIsEmpty(this.fld_course_name)){
                Helper.showMsg("fill");
            }else{
                if (Course.update(course.getId(),((Item)cmb_course_educator.getSelectedItem()).getKey(),((Item)cmb_course_patika.getSelectedItem()).getKey(),
                        this.fld_course_name.getText(),this.fld_course_lang.getText())){
                    Helper.showMsg("done");
                }
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        Course course = new Course(1,2,4,"name","lang");

        UpdateCourseGui opGUI = new UpdateCourseGui(course);
    }

    private void loadUserCombo() {
        cmb_course_educator.removeAllItems();
        for (User user : User.getList()){
            if(user.getType().toLowerCase(Locale.ROOT).equals("educator")){
                cmb_course_educator.addItem(new Item(user.getId(),user.getName()));
            }
        }
    }

    private void loadPatikaCombo() {
        cmb_course_patika.removeAllItems();
        for (Patika item : Patika.getList()){
            cmb_course_patika.addItem(new Item(item.getId(),item.getName()));
        }
    }
}
