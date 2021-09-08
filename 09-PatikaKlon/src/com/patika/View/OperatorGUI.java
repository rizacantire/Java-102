package com.patika.View;

import com.patika.Helper.Config;
import com.patika.Helper.Helper;
import com.patika.Model.Operator;
import com.patika.Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class OperatorGUI extends JFrame {

    private JPanel wrapper;
    private JLabel lbl_welcome;
    private JPanel tbl_top;
    private JButton btn_logout;
    private JTabbedPane tab_operator;
    private JPanel pnl_user_list;
    private JScrollPane scrl_user_list;
    private JTable tbl_user_list;
    private JTable tbl_list;
    private final Operator operator;
    private DefaultTableModel mdl_user_list;
    private Object[] row_user_list;


    public OperatorGUI(Operator operator) {
        this.operator = operator;
        Helper.setLaout();
        add(wrapper);
        setSize(1000,500);
        int x = Helper.screenCenter("x",getSize());
        int y = Helper.screenCenter("y",getSize());
        setLocation(x,y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        lbl_welcome.setText(lbl_welcome.getText() + " " +operator.getName());

        mdl_user_list = new DefaultTableModel();
        Object[] col_user_list= new Object[]{"ID","Ad Soyad","Üye Adı","Şifre","Üyelik Tipi"};
        mdl_user_list.setColumnIdentifiers(col_user_list);


        tbl_user_list.getTableHeader().setReorderingAllowed(false);
        tbl_user_list.setModel(mdl_user_list);
        for (var i : User.getList()){
            row_user_list = new Object[]{i.getId(),i.getName(),i.getUname(),i.getPass(),i.getType()};
            mdl_user_list.addRow(row_user_list);

        }
        tbl_user_list.setModel(mdl_user_list);



    }

    public static void main(String[] args) {
        Operator op = new Operator();

        OperatorGUI opGUI = new OperatorGUI(op);
    }


}
