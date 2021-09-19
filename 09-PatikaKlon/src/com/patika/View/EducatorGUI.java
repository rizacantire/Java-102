package com.patika.View;

import com.patika.Helper.Config;
import com.patika.Helper.Helper;
import com.patika.Helper.Item;
import com.patika.Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Locale;

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
    private JTextArea txt_content_description;
    private JButton btn_content_add;
    private JTextField fld_content_youtube;
    private JTextField fld_content_question;
    private JTextField fld_quiz_question_1;
    private JTextField fld_quiz_question_2;
    private JTextField fld_quiz_question_3;
    private JTextField fld_quiz_question_4;
    private JTextField fld_quiz_answer;
    private JComboBox cmb_course_content;
    private JPanel pnl_quiz_add;
    private JComboBox cmb_course_content_add;
    private JButton btn_quiz_add;
    private JPanel pnl_quiz_list;
    private JScrollPane scrl_quiz_list;
    private JTable tbl_quiz_list;
    private JButton btn_content_update;
    private JTextField fld_update_content_id;
    private JButton btn_delete_content;
    private JButton btn_quiz_delete;
    private JButton btn_quiz_update;
    private JLabel lbl_quiz_id;
    private DefaultTableModel mdl_educator_task_list;
    private DefaultTableModel mld_quiz_list;
    private Object[] row_quiz_list;
    private ArrayList<Quiz> quizzes;
    private Object[] row_course_list;
    private DefaultTableModel mdl_content_list;
    private Object[] row_content_list;
    private ArrayList<Course> course_list;
    private ArrayList<CourseContent> course_content_list = new ArrayList<>();


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
        Object[] col_content = {"Id","İçerik Başlığı","Açıklama","Ders","Youtue Link"};
        mdl_content_list.setColumnIdentifiers(col_content);
        tbl_course_content.setModel(mdl_content_list);
        row_content_list = new Object[col_content.length];
        loadAllList();

        mld_quiz_list = new DefaultTableModel();
        Object[] col_quiz = {"Id","Soru","Şık 1","Şık 2","Şık 3","Şık 4","Cevap","İçerik"};
        mld_quiz_list.setColumnIdentifiers(col_quiz);
        tbl_quiz_list.setModel(mld_quiz_list);
        row_quiz_list = new Object[col_quiz.length];
        if (!course_list.isEmpty()){
            loadQuizList();
        }



        btn_content_add.addActionListener(e -> {
            if (Helper.fieldIsEmpty(fld_content_title)||Helper.fieldIsEmpty(fld_content_youtube)||Helper.textAreaIsEmpty(txt_content_description)){
                Helper.showMsg("fill");
            }else {
                String title = fld_content_title.getText();
                int course_id = ((Item)cmb_course_content_add.getSelectedItem()).getKey();
                String description = txt_content_description.getText();
                String yotube = fld_content_youtube.getText();
                if (CourseContent.add(course_id,title,description,yotube)){
                    Helper.showMsg("done");
                    loadAllList();
                }else {
                    Helper.showMsg("error");
                }
            }
        });
        tbl_course_content.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                String content_id  = tbl_course_content.getValueAt(tbl_course_content.getSelectedRow(),0).toString();
                String title = tbl_course_content.getValueAt(tbl_course_content.getSelectedRow(),1).toString();
                String description = tbl_course_content.getValueAt(tbl_course_content.getSelectedRow(),2).toString();
                String youtube = tbl_course_content.getValueAt(tbl_course_content.getSelectedRow(),4).toString();
                fld_update_content_id.setText(content_id);
                fld_content_title.setText(title);
                txt_content_description.setText(description);
                fld_content_youtube.setText(youtube);
                btn_content_add.setEnabled(false);
                btn_delete_content.setEnabled(true);
                btn_content_update.setEnabled(true);
            }
        });
        btn_content_update.addActionListener(e -> {
            if (Helper.fieldIsEmpty(fld_content_title)||Helper.fieldIsEmpty(fld_content_youtube)||Helper.textAreaIsEmpty(txt_content_description)){
                Helper.showMsg("fill");
            }else{
                int content_id = Integer.parseInt(fld_update_content_id.getText().toString());
                int course_id = ((Item)cmb_course_content_add.getSelectedItem()).getKey();
               if (CourseContent.update(content_id,course_id,txt_content_description.getText(),fld_content_title.getText(),fld_content_youtube.getText())){
                    Helper.showMsg("done");
                }
               loadAllList();

            }
        });
        btn_delete_content.addActionListener(e -> {
            int selected_id = Integer.parseInt(fld_update_content_id.getText());
            if (Helper.confirm("sure")){
                if(CourseContent.delete(selected_id)){
                    loadAllList();
                }
            }
        });
        btn_quiz_add.addActionListener(e -> {
           if(Helper.fieldIsEmpty(fld_content_question)||Helper.fieldIsEmpty(fld_quiz_question_1)||Helper.fieldIsEmpty(fld_quiz_question_2)||Helper.fieldIsEmpty(fld_quiz_question_3)
                   ||Helper.fieldIsEmpty(fld_quiz_question_4)||Helper.fieldIsEmpty(fld_quiz_answer)){
               Helper.showMsg("fill");
            }else {
               String q = fld_content_question.getText();
               String a = fld_quiz_answer.getText();
               String q1 = fld_quiz_question_1.getText();
               String q2 = fld_quiz_question_2.getText();
               String q3 = fld_quiz_question_3.getText();
               String q4 = fld_quiz_question_4.getText();
               var course_conten_id = ((Item)cmb_course_content.getSelectedItem()).getKey();
               if (Quiz.add(course_conten_id,q,q1,q2,q3,q4,a)){
                   Helper.showMsg("done");
                   loadQuizList();
               }

           }
        });
        tbl_quiz_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                String q = tbl_quiz_list.getValueAt(tbl_quiz_list.getSelectedRow(),1).toString();
                String q1 = tbl_quiz_list.getValueAt(tbl_quiz_list.getSelectedRow(),2).toString();
                String q2 = tbl_quiz_list.getValueAt(tbl_quiz_list.getSelectedRow(),3).toString();
                String q3 = tbl_quiz_list.getValueAt(tbl_quiz_list.getSelectedRow(),4).toString();
                String q4 = tbl_quiz_list.getValueAt(tbl_quiz_list.getSelectedRow(),5).toString();
                String a = tbl_quiz_list.getValueAt(tbl_quiz_list.getSelectedRow(),6).toString();

                fld_quiz_answer.setText(a);
                fld_quiz_question_1.setText(q1);
                fld_quiz_question_2.setText(q2);
                fld_quiz_question_3.setText(q3);
                fld_quiz_question_4.setText(q4);
                fld_content_question.setText(q);
                lbl_quiz_id.setText(tbl_quiz_list.getValueAt(tbl_quiz_list.getSelectedRow(),0).toString());

                super.mousePressed(e);
                btn_quiz_delete.setEnabled(true);
                btn_quiz_update.setEnabled(true);
            }
        });
        btn_quiz_delete.addActionListener(e -> {
            if(Helper.confirm("sure")){
                if (Quiz.delete(Integer.parseInt(lbl_quiz_id.getText()))){
                    Helper.showMsg("done");
                    lbl_quiz_id.setText(null);
                    loadQuizList();
                }
            }
        });
        btn_quiz_update.addActionListener(e -> {
            String q = fld_content_question.getText();
            String a = fld_quiz_answer.getText();
            String q1 = fld_quiz_question_1.getText();
            String q2 = fld_quiz_question_2.getText();
            String q3 = fld_quiz_question_3.getText();
            String q4 = fld_quiz_question_4.getText();
            int c = ((Item)cmb_course_content.getSelectedItem()).getKey();
            int id = Integer.parseInt(lbl_quiz_id.getText());
            System.out.println(id+c+q+q1+q2+q3+q4+a);
           if(Helper.confirm("sure")){
               if(Quiz.update(id,c,q,q1,q2,q3,q4,a)){
                   Helper.showMsg("done");
                   loadQuizList();
               }
           }
        });
    }

    private void loadAllList(){
        loadContentList();

        loadContentCombo();
        loadCourseList();
        loadCourseContentCombo();
    }
    private void loadQuizList(){
        DefaultTableModel clear = (DefaultTableModel) tbl_quiz_list.getModel();
        clear.setRowCount(0);
        int i =0;
        if (!Quiz.getList().isEmpty()){
            this.quizzes = Quiz.getList();

            for (var q :quizzes){
                i =0;
                row_quiz_list[i++] =q.getId();
                row_quiz_list[i++] =q.getQuestion();
                row_quiz_list[i++] =q.getUser_answer1();
                row_quiz_list[i++] =q.getUser_answer2();
                row_quiz_list[i++] =q.getUser_answer3();
                row_quiz_list[i++] =q.getUser_answer4();
                row_quiz_list[i++] =q.getAnswer();
                row_quiz_list[i++] =q.getContent().getTitle();
                mld_quiz_list.addRow(row_quiz_list);
            }
        }

    }

    private void loadContentList() {
        DefaultTableModel clear = (DefaultTableModel) tbl_course_content.getModel();
        clear.setRowCount(0);
        ArrayList<CourseContent> courseContents = CourseContent.getList();
        course_content_list = new ArrayList<>();

        int i = 0;
        for(var a : course_list){
            for(var cc : courseContents){
                if (a.getId() == cc.getCourse_id()){
                    course_content_list.add(cc);
                }
            }
        }
        for(var cci : course_content_list){
            i = 0;
            row_content_list[i++] = cci.getId();
            row_content_list[i++] = cci.getTitle();
            row_content_list[i++] = cci.getDescription();
            row_content_list[i++] = cci.getCourse().getName();
            row_content_list[i++] = cci.getYoutube();
            mdl_content_list.addRow(row_content_list);
        }
    }

    private void loadCourseList() {
        DefaultTableModel clear = (DefaultTableModel) tbl_educator_task.getModel();
        clear.setRowCount(0);
        course_list =  Course.getListByUser(user.getId());
        for (var item : course_list){
            int i = 0;
            row_course_list[i++] = item.getName();
            row_course_list[i++] = item.getLang();
            row_course_list[i++] = item.getPatika().getName();
            mdl_educator_task_list.addRow(row_course_list);
        }
    }

    private void loadContentCombo() {
        cmb_course_content.removeAllItems();
        for (CourseContent cc : course_content_list){
                cmb_course_content.addItem(new Item(cc.getId(),cc.getTitle()));
        }
    }

    private void loadCourseContentCombo() {
        cmb_course_content_add.removeAllItems();
        for (Course c : course_list){
            cmb_course_content_add.addItem(new Item(c.getId(),c.getName()));
        }
    }





    public static void main(String[] args) {
        Helper.setLayout();
        EducatorGUI eg = new EducatorGUI(new User(2,"Ali Emmi","ali","1234","Educator"));
    }

}
