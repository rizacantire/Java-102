package com.patika.View;

import com.patika.Helper.Config;
import com.patika.Helper.Helper;
import com.patika.Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EducatorGUI extends JFrame  {
    private final User user;
    private JPanel wrapper;
    private JTabbedPane tab_educator;
    private JPanel pnl_course;
    private JPanel pnl_content;
    private JLabel lbl_educator;
    private JScrollPane scrl_course;
    private JTable tbl_educator_task;
    private JTable tbl_course_content;
    private JTextField fld_content_title;
    private JLabel pnl_content_add;
    private JTextArea txt_content_add;
    private JButton btn_content_add;
    private JTextField fld_content_youtube;
    private JTextField fld_content_question;
    private JTextField fld_quiz_question_1;
    private JTextField fld_quiz_question_2;
    private JTextField fld_quiz_question_3;
    private JTextField fld_quiz_question_4;
    private JTextField fld_quiz_answer;
    private DefaultTableModel mdl_educator_task_list;
    private Object[] row_course_list;
    private DefaultTableModel mdl_content_list;
    private Object[] row_content_list;



    public EducatorGUI(User user){
        this.user = user;
        add(wrapper);
        setSize(1000,600);
        int x = Helper.screenCenter("x",getSize());
        int y = Helper.screenCenter("y",getSize());
        setLocation(x,y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        lbl_educator.setText("Sayın "+user.getName()+ " Eğitmen Portalına Hoşgeldiniz");

        mdl_educator_task_list = new DefaultTableModel();
        Object[] col_course = {"Ders Adı","Programlama Dili","Patika Adı"};
        mdl_educator_task_list.setColumnIdentifiers(col_course);
        tbl_educator_task.setModel(mdl_educator_task_list);
        row_course_list = new Object[col_course.length];
        loadCourseList();

        mdl_content_list = new DefaultTableModel();
        Object[] col_content = {"Id","İçerik Başlığı","Açıklama"};
        mdl_content_list.setColumnIdentifiers(col_content);
        tbl_course_content.setModel(mdl_content_list);
        row_content_list = new Object[col_content.length];
        loadContentList();
    }

    private void loadContentList() {
        DefaultTableModel clear = (DefaultTableModel) tbl_course_content.getModel();
        clear.setRowCount(0);
        var list = CourseContent.getListByCourseId(1);
        for (CourseContent i : list){
            System.out.println(i.toString());
        }

       for (CourseContent item : list){
            int i = 0;
            row_content_list[i++] = item.g
            row_content_list[i++] = item.getLang();
            row_content_list[i++] = item.getPatika().getName();

            mdl_educator_task_list.addRow(row_course_list);
        }
    }

    private void loadCourseList() {
        DefaultTableModel clear = (DefaultTableModel) tbl_educator_task.getModel();
        clear.setRowCount(0);
        var list =  Course.getListByUser(user.getId());
        System.out.println(list.isEmpty());
        for (var item : list){
            int i = 0;
            row_course_list[i++] = item.getName();
            row_course_list[i++] = item.getLang();
            row_course_list[i++] = item.getPatika().getName();

            mdl_educator_task_list.addRow(row_course_list);
        }
    }


    public static void main(String[] args) {
        Helper.setLayout();
        EducatorGUI eg = new EducatorGUI(new User(2,"Ali Emmi","ali","1234","Educator"));
    }


}
