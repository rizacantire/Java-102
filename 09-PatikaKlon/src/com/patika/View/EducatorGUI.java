package com.patika.View;

import com.patika.Helper.Config;
import com.patika.Helper.Helper;
import com.patika.Model.Course;
import com.patika.Model.Educator;
import com.patika.Model.Patika;
import com.patika.Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EducatorGUI extends JFrame  {
    private final User user;
    private JPanel wrapper;
    private JPanel pln_educator_detail;
    private JTable tbl_educator_task;
    private JLabel lbl_educator_welcome;
    private DefaultTableModel mdl_educator_task_list;
    private Object[] row_course_list;

    public EducatorGUI(User user){
        this.user = user;
        add(wrapper);
        setSize(400,400);
        int x = Helper.screenCenter("x",getSize());
        int y = Helper.screenCenter("y",getSize());
        setLocation(x,y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        lbl_educator_welcome.setText("Sayın "+user.getName()+ " Eğitmen Portalına Hoşgeldiniz");



        mdl_educator_task_list = new DefaultTableModel();
        Object[] col_educator_course_list = new Object[]{"Kurs Adı","Programlama Dili","Patika Adı"};
        mdl_educator_task_list.setColumnIdentifiers(col_educator_course_list);
        row_course_list = new Object[col_educator_course_list.length];
        
        loadEducatorCourseModel();
        tbl_educator_task.setModel(mdl_educator_task_list);
        tbl_educator_task.getTableHeader().setReorderingAllowed(false);
        tbl_educator_task.getColumnModel().getColumn(0).setMaxWidth(75);
        mdl_educator_task_list =  new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return  false;
                return super.isCellEditable(row, column);
            }
        };


    }

    private void loadEducatorCourseModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_educator_task.getModel();
        clearModel.setRowCount(0);
        int i =0;
        for (Course obj : Course.getListByUser(user.getId())){
            i = 0;
            row_course_list[i++] = obj.getId();
            row_course_list[i++] = obj.getName();
            mdl_educator_task_list.addRow(row_course_list);
        }
    }

    public static void main(String[] args) {
        EducatorGUI eg = new EducatorGUI(new User(1,"Ali Emmi","ali","1234","Educator"));
    }


}
