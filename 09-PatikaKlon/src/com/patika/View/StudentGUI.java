package com.patika.View;

import com.patika.Helper.Config;
import com.patika.Helper.Helper;
import com.patika.Model.Patika;
import com.patika.Model.StudentPatika;
import com.patika.Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Optional;

public class StudentGUI extends JFrame {
    private JPanel wrapper;
    private JTabbedPane pnl_patika;
    private JTable tbl_patika_list;
    private JLabel lbl_student_welcome;
    private JPanel scrl_patika_list;
    private JPanel pnl_patika_registry;
    private JTextField fld_patika_name;
    private JButton btn_patika_register;
    private JPanel pnl_register_patika;
    private JScrollPane scrl_register_patika;
    private JTable tbl_register_patika;
    private JPanel pnl_course_content;
    private JScrollPane scrl_course_content;
    private JTable tbl_course_content;
    private JPanel pnl_course_raiting;
    private JComboBox cmb_course_raiting;
    private JButton btn_course_raiting;
    private DefaultTableModel mdl_patika_list;
    private Object[] row_patika_list;
    private DefaultTableModel mdl_regiter_patika_list;
    private Object[] row_register_patika_list;
    private final User user;
    private ArrayList<StudentPatika> user_register_patika;

    public StudentGUI(User user){
        this.user = user;
        add(wrapper);
        setSize(1024,860);
        int x = Helper.screenCenter("x",getSize());
        int y = Helper.screenCenter("y",getSize());
        setLocation(x,y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        lbl_student_welcome.setText(user.getName() + " patika sistemine hoş geldiniz. Ders seçimi yapabilirsiniz.");

        mdl_patika_list = new DefaultTableModel();
        Object[] col_patika_list = {"Id","Name"};
        mdl_patika_list.setColumnIdentifiers(col_patika_list);
        row_patika_list = new Object[col_patika_list.length];
        tbl_patika_list.setModel(mdl_patika_list);
        tbl_patika_list.getColumnModel().getColumn(0).setMaxWidth(70);
        loadPatikaList();
        user_register_patika = StudentPatika.getRegisterPatika(user.getId());

        mdl_regiter_patika_list = new DefaultTableModel();
        Object[] col_register_patika_list = {"Id","Name"};
        mdl_regiter_patika_list.setColumnIdentifiers(col_patika_list);
        row_register_patika_list = new Object[col_register_patika_list.length];
        tbl_register_patika.setModel(mdl_regiter_patika_list);
        tbl_register_patika.getColumnModel().getColumn(0).setMaxWidth(70);
        loadAllList();


        tbl_patika_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                var selectedPatika = tbl_patika_list.getValueAt(tbl_patika_list.getSelectedRow(),0).toString();
                fld_patika_name.setText(selectedPatika);
            }
        });
        btn_patika_register.addActionListener(e -> {
            int patika_id = Integer.parseInt(fld_patika_name.getText());
            var isRegister = user_register_patika.stream().filter(o->o.getPatika_id() == patika_id).findAny().isEmpty();
            if (isRegister){
                if (StudentPatika.add(patika_id,user.getId())) {
                    Helper.showMsg("done");
                    user_register_patika = StudentPatika.getRegisterPatika(user.getId());
                    loadAllList();
                }
            }else {
                Helper.showMsg("Derse kaydınız bulunmakta.");
            }

        });
    }
    private void loadAllList(){
        loadRegisterPatikaList();
        loadPatikaList();
    }

    private void loadPatikaList() {
        clearModel(tbl_patika_list);
        int i =0;
        for (Patika p : Patika.getList()){
            i =0;
            row_patika_list[i++] = p.getId();
            row_patika_list[i++] = p.getName();
            mdl_patika_list.addRow(row_patika_list);
        }
    }

    private void loadRegisterPatikaList(){
        clearModel(tbl_register_patika);
        int i =0;

        for (var patika : user_register_patika){
            i = 0;
            row_register_patika_list[i++] = patika.getPatika().getId();
            row_register_patika_list[i++] = patika.getPatika().getName();
            mdl_regiter_patika_list.addRow(row_register_patika_list);
        }

    }
    private void clearModel(JTable model){
        DefaultTableModel clearModel = (DefaultTableModel) model.getModel();
        clearModel.setRowCount(0);
    }

    public static void main(String[] args) {
        Helper.setLayout();
        StudentGUI sg = new StudentGUI(new User(1,"Ali","ali","123","student"));
    }

}
