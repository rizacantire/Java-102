package com.patika.View;

import com.patika.Helper.Config;
import com.patika.Helper.Helper;
import com.patika.Model.Operator;
import com.patika.Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class OperatorGUI extends JFrame {

    private JPanel wrapper;
    private JLabel lbl_welcome;
    private JPanel tbl_top;
    private JButton btn_logout;
    private JTabbedPane tab_operator;
    private JPanel pnl_user_list;
    private JScrollPane scrl_user_list;
    private JTable tbl_user_list;
    private JPanel pnl_user_registry;
    private JTextField fld_user_name;
    private JTextField fld_user_uname;
    private JTextField fld_user_pass;
    private JComboBox cb_user_type;
    private JButton btn_user_add;
    private JTextField fld_user_id;
    private JButton btn_user_delete;
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
        row_user_list = new Object[col_user_list.length];
        loadUserModel();

        tbl_user_list.setModel(mdl_user_list);


        btn_user_add.addActionListener(e -> {
            if(Helper.fieldIsEmpty(fld_user_name) || Helper.fieldIsEmpty(fld_user_uname) || Helper.fieldIsEmpty(fld_user_pass)){
                Helper.showMsg("fill");
            }else {
                String name = fld_user_name.getText();
                String uname = fld_user_uname.getText();
                String pass = fld_user_pass.getText();
                String type = cb_user_type.getSelectedItem().toString();

                if (User.add(name,uname,pass,type)) {
                    Helper.showMsg("done");
                    loadUserModel();
                    Helper.blankField(fld_user_uname,fld_user_pass,fld_user_name);

                }
            }
        });
        btn_logout.addActionListener(e -> {
            System.exit(0);

        });
        btn_user_delete.addActionListener(e -> {
            if (Helper.fieldIsEmpty(fld_user_id)){
                Helper.showMsg("Seçim yapmadınız");
            }else {
                int id = Integer.parseInt(fld_user_id.getText());
                if (User.delete(id)){
                    Helper.showMsg("done");
                    loadUserModel();
                }
            }
        });
    }

    public void loadUserModel(){
        DefaultTableModel clear = (DefaultTableModel) tbl_user_list.getModel();
        clear.setRowCount(0);
        for (var item : User.getList()){
            int i = 0;
            row_user_list[i++] = item.getId();
            row_user_list[i++] = item.getName();
            row_user_list[i++] = item.getUname();
            row_user_list[i++] = item.getPass();
            row_user_list[i++] = item.getType();
            mdl_user_list.addRow(row_user_list);
        }

    }

    public static void main(String[] args) {
        Operator op = new Operator();

        OperatorGUI opGUI = new OperatorGUI(op);
    }


}
