package com.patika.View;

import com.patika.Helper.Config;
import com.patika.Helper.Helper;
import com.patika.Model.Operator;
import com.patika.Model.Patika;
import com.patika.Model.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import static javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION;


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
    private JPanel pnl_user_src;
    private JTextField fld_name_src;
    private JTextField fld_uname_src;
    private JComboBox cb_user_src;
    private JButton btn_user_src;
    private JButton btn_list_refresh;
    private JScrollPane scrl_patika_list;
    private JTable tbl_patika_list;
    private JPanel pnl_patika_add;
    private JTextField fld_patika_name;
    private JButton btn_patika_add;
    private JTable tbl_list;
    private final Operator operator;
    private DefaultTableModel mdl_user_list;
    private Object[] row_user_list;
    private String user_id;
    private  DefaultTableModel mdl_patika_list;
    private  Object[] row_patika_list;
    private JPopupMenu patikaMenu;


    public OperatorGUI(Operator operator) {
        this.operator = operator;

        add(wrapper);
        setSize(1000,500);
        int x = Helper.screenCenter("x",getSize());
        int y = Helper.screenCenter("y",getSize());
        setLocation(x,y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        lbl_welcome.setText(lbl_welcome.getText() + " " +operator.getName());
        mdl_user_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return  false;
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_user_list= new Object[]{"ID","Ad Soyad","Üye Adı","Şifre","Üyelik Tipi"};
        mdl_user_list.setColumnIdentifiers(col_user_list);

        tbl_user_list.getTableHeader().setReorderingAllowed(false);
        tbl_user_list.setModel(mdl_user_list);
        row_user_list = new Object[col_user_list.length];
        loadUserModel();

        tbl_user_list.setModel(mdl_user_list);

        tbl_user_list.getModel().addTableModelListener(e->{
            if(e.getType() == TableModelEvent.UPDATE){
                int user_update_id = Integer.parseInt(tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),0).toString());
                String name = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),1).toString();
                String uname = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),2).toString();
                String pass = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),3).toString();
                String type = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),4).toString();
                System.out.println(user_update_id+name+uname+pass+type);
                if (User.update(user_update_id,name,uname,pass,type)) {
                    Helper.showMsg("done");
                    Helper.blankField(fld_user_uname,fld_user_pass,fld_user_name);
                }
                loadUserModel();
            }
        });
        patikaMenu = new JPopupMenu();

        JMenuItem update = new JMenuItem("Güncelle");
        JMenuItem delete = new JMenuItem("Sil");
        patikaMenu.add(update);
        patikaMenu.add(delete);

        update.addActionListener(e->{
            int select_id = Integer.parseInt(tbl_patika_list.getValueAt(tbl_patika_list.getSelectedRow(),0).toString());
            UpdatePatikaGUI updatePatikaGUI = new UpdatePatikaGUI(Patika.getFetch(select_id));
            updatePatikaGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    super.windowClosing(e);
                    loadPatikaModel();
                }
            });

        });

        delete.addActionListener(e->{
            int select_id = Integer.parseInt(tbl_patika_list.getValueAt(tbl_patika_list.getSelectedRow(),0).toString());

            if (Helper.confirm("sure")){
                if (Patika.delete(select_id)){
                    Helper.showMsg("done");
                    loadPatikaModel();
                }
            }
        });




        mdl_patika_list = new DefaultTableModel();
        Object[] col_patika_list = {"ID","Patika Adı"};
        mdl_patika_list.setColumnIdentifiers(col_patika_list);
        row_patika_list = new Object[col_patika_list.length];
        loadPatikaModel();
        tbl_patika_list.setModel(mdl_patika_list);
        tbl_patika_list.setComponentPopupMenu(patikaMenu);
        tbl_patika_list.getTableHeader().setReorderingAllowed(false);
        tbl_patika_list.getColumnModel().getColumn(0).setMaxWidth(75);

        tbl_patika_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                Point point = e.getPoint();
                int selected_row = tbl_patika_list.rowAtPoint(point);
                tbl_patika_list.setRowSelectionInterval(selected_row,selected_row);
            }
        });

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
            dispose();
        });
        btn_user_delete.addActionListener(e -> {
            if (Helper.fieldIsEmpty(fld_user_id)){
                Helper.showMsg("Seçim yapmadınız");
            }else {
                if(Helper.confirm("sure")){
                    int id = Integer.parseInt(fld_user_id.getText());

                    if (User.delete(id)){
                        Helper.showMsg("done");
                        loadUserModel();
                    }
                }
            }
        });
        tbl_user_list.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                user_id = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),0).toString();
                fld_user_id.setText(user_id);


            }
        });
        btn_list_refresh.addActionListener(e -> {
            loadUserModel();
        });
        btn_user_src.addActionListener(e -> {
            String name =fld_name_src.getText();
            String uname = fld_uname_src.getText();
            String type = cb_user_src.getSelectedItem().toString();

            loadUserModel(User.search(User.searchQuery(name,uname,type)));
        });
        btn_patika_add.addActionListener(e -> {
            if(Helper.fieldIsEmpty(fld_patika_name)){
                Helper.showMsg("fill");
            }else {
                if (Patika.add(fld_patika_name.getText())){
                    Helper.showMsg("done");
                    loadPatikaModel();
                    fld_patika_name.setText(null);
                }else {
                    Helper.showMsg("error");
                }
            }
        });
    }

    private void loadPatikaModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_patika_list.getModel();
        clearModel.setRowCount(0);
        int i =0;
        for (Patika obj : Patika.getList()){
            i = 0;
            row_patika_list[i++] = obj.getId();
            row_patika_list[i++] = obj.getName();
            mdl_patika_list.addRow(row_patika_list);
        }
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

    public void loadUserModel(ArrayList<User> searchList){
        DefaultTableModel clear = (DefaultTableModel) tbl_user_list.getModel();
        clear.setRowCount(0);
        for (var item : searchList){
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
