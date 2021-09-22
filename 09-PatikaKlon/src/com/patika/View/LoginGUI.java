package com.patika.View;

import com.patika.Helper.Config;
import com.patika.Helper.Helper;
import com.patika.Model.Operator;
import com.patika.Model.User;

import javax.swing.*;
import java.util.Locale;

public class LoginGUI extends JFrame {
    private JPanel wrapper;
    private JPanel wtop;
    private JPanel wbottom;
    private JTextField fld_user_name;
    private JTextField fld_user_pass;
    private JButton btn_login;
    private JPanel pnl_register_user;
    private JTextField fld_student_name;
    private JButton btn_user_register;

    public LoginGUI() {
        add(wrapper);
        setSize(550,550);
        int x = Helper.screenCenter("x",getSize());
        int y = Helper.screenCenter("y",getSize());
        setLocation(x,y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);
        btn_login.addActionListener(e -> {
            if(Helper.fieldIsEmpty(fld_user_name) || Helper.fieldIsEmpty(fld_user_pass)){
                Helper.showMsg("fill");
            }else {
                User u = User.getFetch(fld_user_name.getText(),fld_user_pass.getText());
                if(u ==null){
                    Helper.showMsg("Kullanıcı bulunamadı");
                }else {
                    Operator operator = new Operator(u.getId(),u.getName(),u.getUname(),u.getPass(),u.getType());
                    switch (u.getType().toLowerCase(Locale.ROOT)){
                        case "operator":
                            OperatorGUI operatorGUI = new OperatorGUI(operator);
                            break;
                        case "educator":
                            EducatorGUI educatorGUI = new EducatorGUI(u);
                            break;
                        case "student":
                            StudentGUI studentGUI = new StudentGUI(u);
                            break;
                    }

                    dispose();

                }
            }
        });
        btn_user_register.addActionListener(e->{
            if(Helper.fieldIsEmpty(fld_user_name) || Helper.fieldIsEmpty(fld_user_pass)||Helper.fieldIsEmpty(fld_student_name)){
                Helper.showMsg("fill");
            }else {
                User u = User.getFetch(fld_user_name.getText());
                if(u==null){
                    Helper.showMsg("Kayıt Başarılı");
                    User.add(fld_student_name.getText(),fld_user_name.getText(),fld_user_pass.getText(),"Student");
                }else {

                    Helper.showMsg("Kullanıcı sistemde kayıtlı.");
                }
            }
        });
    }

    public static void main(String[] args) {
        Helper.setLayout();
        LoginGUI l = new LoginGUI();
    }
}
