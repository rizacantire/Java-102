package com.patika.View;

import com.patika.Helper.Config;
import com.patika.Helper.Helper;
import com.patika.Helper.Item;
import com.patika.Model.*;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Locale;


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
    private JTextField fld_course_name;
    private JTextField fld_course_lang;
    private JComboBox cb_user_type;
    private JComboBox cmb_course_patika;
    private JComboBox cmb_course_user;
    private JButton btn_user_add;
    private JButton btn_course_add;
    private JTextField fld_user_id;
    private JButton btn_user_delete;
    private JPanel pnl_user_src;
    private JTextField fld_name_src;
    private JTextField fld_uname_src;
    private JComboBox cb_user_src;
    private JButton btn_user_src;
    private JButton btn_list_refresh;
    private JScrollPane scrl_patika_list;
    private JScrollPane scrl_course_list;
    private JTable tbl_patika_list;
    private JTable tbl_course_list;
    private JPanel pnl_patika_add;
    private JPanel pnl_patika_list;
    private JPanel pnl_course_list;
    private JPanel pnl_course_add;
    private JTextField fld_patika_name;
    private JButton btn_patika_add;
    private JButton btn_course_delete;
    private JTextField fld_course_id;
    private JButton btn_course_update;
    private JPanel pnl_course_content;
    private JTable tbl_course_content;
    private JLabel pnl_content_add;
    private JTextField fld_content_title;
    private JTextArea txt_content_description;
    private JButton btn_content_add;
    private JTextField fld_content_youtube;
    private JComboBox cmb_course_content_add;
    private JButton btn_content_update;
    private JTextField fld_update_content_id;
    private JButton btn_delete_content;
    private JTable tbl_list;
    private final Operator operator;
    private DefaultTableModel mdl_user_list;
    private Object[] row_user_list;
    private String user_id;
    private String course_id;
    private  DefaultTableModel mdl_patika_list;
    private  Object[] row_patika_list;
    private JPopupMenu patikaMenu;
    private JPopupMenu courseMenu;
    private DefaultTableModel mdl_content_list;
    private Object[] row_content_list;
    private DefaultTableModel mdl_course_list;
    private  Object[] row_course_list;
    private ArrayList<CourseContent> course_content_list = new ArrayList<>();
    private ArrayList<Course> course_list;


    public OperatorGUI(Operator operator) {
        this.operator = operator;

        add(wrapper);
        setSize(1000,800);
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
        loadCourseContentCombo();


        tbl_user_list.setModel(mdl_user_list);
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

        tbl_user_list.getModel().addTableModelListener(e->{
            if(e.getType() == TableModelEvent.UPDATE){
                int user_update_id = Integer.parseInt(tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),0).toString());
                String name = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),1).toString();
                String uname = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),2).toString();
                String pass = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),3).toString();
                String type = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),4).toString();
                if (User.update(user_update_id,name,uname,pass,type)) {
                    Helper.showMsg("done");
                    Helper.blankField(fld_user_uname,fld_user_pass,fld_user_name);
                }

                loadCourseList();
                loadUserModel();
                loadPatikaCombo();
                loadUserCombo();
            }
        });



        mdl_course_list = new DefaultTableModel();
        Object[] col_course_list = {"ID","Ders Adı","Programlama Dili","Patika Adı","Eğitmen"};
        mdl_course_list.setColumnIdentifiers(col_course_list);
        row_course_list = new Object[col_course_list.length];
        loadCourseList();
        loadUserModel();
        loadPatikaCombo();
        loadUserCombo();
        tbl_course_list.setModel(mdl_course_list);

        tbl_course_list.getTableHeader().setReorderingAllowed(false);
        tbl_course_list.getColumnModel().getColumn(0).setMaxWidth(75);

        mdl_content_list = new DefaultTableModel();
        Object[] col_content = {"Id","İçerik Başlığı","Açıklama","Ders","Youtue Link"};
        mdl_content_list.setColumnIdentifiers(col_content);
        tbl_course_content.setModel(mdl_content_list);
        row_content_list = new Object[col_content.length];

        course_list =  Course.getList();
        if (!course_list.isEmpty()){
            loadContentList();
        }

        tbl_course_list.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                course_id = tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(),0).toString();
                String course_name = tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(),1).toString();
                String course_lang = tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(),2).toString();
                fld_course_id.setText(course_id);
                btn_course_update.setEnabled(true);
                fld_course_name.setText(course_name);
                fld_course_lang.setText(course_lang);
            }
        });




        btn_course_add.addActionListener(e -> {
            if(Helper.fieldIsEmpty(fld_course_name) || Helper.fieldIsEmpty(fld_course_lang)){
                Helper.showMsg("fill");
            }else {
                String name = fld_course_name.getText();
                String lang = fld_course_lang.getText();
                int user_id = ((Item)cmb_course_user.getSelectedItem()).getKey();
                int patika_id =((Item)cmb_course_patika.getSelectedItem()).getKey();

                if (Course.add(user_id,patika_id,name,lang)) {
                    Helper.showMsg("done");
                    loadCourseList();
                    loadUserModel();
                    loadPatikaCombo();
                    loadUserCombo();
                    Helper.blankField(fld_course_name,fld_course_lang);
                }
                loadCourseList();
                loadUserModel();
                loadPatikaCombo();
                loadUserCombo();
            }
        });

        btn_content_add.addActionListener(e -> {
            if (Helper.fieldIsEmpty(fld_content_title)||Helper.fieldIsEmpty(fld_content_youtube)||Helper.textAreaIsEmpty(txt_content_description)){
                Helper.showMsg("fill");
            }else {
                String title = fld_content_title.getText();
                int course_id = ((Item)cmb_course_content_add.getSelectedItem()).getKey();
                String description = txt_content_description.getText();
                String yotube = fld_content_youtube.getText();
                if (CourseContent.add(course_id,description,title,yotube)){
                    Helper.showMsg("done");
                    loadContentList();
                }else {
                    Helper.showMsg("error");
                }
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
                    loadCourseList();
                    loadUserModel();
                    loadPatikaCombo();
                    loadUserCombo();
                }
            });

        });

        delete.addActionListener(e->{
            int select_id = Integer.parseInt(tbl_patika_list.getValueAt(tbl_patika_list.getSelectedRow(),0).toString());

            if (Helper.confirm("sure")){
                if (Patika.delete(select_id)){
                    Helper.showMsg("done");
                    loadCourseList();
                    loadUserModel();
                    loadPatikaCombo();
                    loadUserCombo();
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
        txt_content_description.getWidth();

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
                    loadCourseList();
                    loadUserModel();
                    loadPatikaCombo();
                    loadUserCombo();
                    Helper.blankField(fld_user_uname,fld_user_pass,fld_user_name);
                }
            }
        });


        btn_logout.addActionListener(e -> {
            closeScreen();
        });
        btn_user_delete.addActionListener(e -> {
            if (Helper.fieldIsEmpty(fld_user_id)){
                Helper.showMsg("Seçim yapmadınız");
            }else {
                if(Helper.confirm("sure")){
                    int id = Integer.parseInt(fld_user_id.getText());

                    if (User.delete(id)){
                        Helper.showMsg("done");
                        loadCourseList();
                        loadUserModel();
                        loadPatikaCombo();
                        loadUserCombo();
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
            loadCourseList();
            loadUserModel();
            loadPatikaCombo();
            loadUserCombo();
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
                    loadCourseList();
                    loadUserModel();
                    loadPatikaCombo();
                    loadUserCombo();
                    fld_patika_name.setText(null);
                }else {
                    Helper.showMsg("error");
                }
            }
        });
        btn_course_delete.addActionListener(e -> {
            if (Helper.fieldIsEmpty(fld_course_id)){
                Helper.showMsg("Seçim yapmadınız");
            }else {
                if(Helper.confirm("sure")){
                    int id = Integer.parseInt(fld_course_id.getText());

                    if (Course.delete(id)){
                        Helper.showMsg("done");
                        loadCourseList();
                        loadUserModel();
                        loadPatikaCombo();
                        loadUserCombo();
                    }
                }
            }
        });
        btn_course_update.addActionListener(e -> {
            int id = Integer.parseInt(fld_course_id.getText());
            if(Helper.fieldIsEmpty(fld_course_name) || Helper.fieldIsEmpty(fld_course_lang)){
                Helper.showMsg("fill");
            }else {
                String name = fld_course_name.getText();
                String lang = fld_course_lang.getText();
                int user_id = ((Item)cmb_course_user.getSelectedItem()).getKey();
                int patika_id =((Item)cmb_course_patika.getSelectedItem()).getKey();

                if (Course.update(id,user_id,patika_id,name,lang)) {
                    Helper.showMsg("done");
                    loadCourseList();
                    loadUserModel();
                    loadPatikaCombo();
                    loadUserCombo();
                    Helper.blankField(fld_course_name,fld_course_lang);
                }
                loadCourseList();
                loadUserModel();
                loadPatikaCombo();
                loadUserCombo();
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
                    fld_update_content_id.setText(null);
                    btn_content_update.setEnabled(false);
                    btn_content_add.setEnabled(true);
                    btn_delete_content.setEnabled(false);
                }
                loadAllList();

            }
        });

    }

    private void loadAllList(){
        loadCourseContentCombo();
        loadContentList();
        loadUserModel();
        loadCourseList();
        loadPatikaCombo();
        loadPatikaModel();
        loadCourseContentCombo();
        loadUserCombo();
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

    private void loadUserCombo() {
        cmb_course_user.removeAllItems();
        for (User user : User.getList()){
            if(user.getType().toLowerCase(Locale.ROOT).equals("educator")){
                cmb_course_user.addItem(new Item(user.getId(),user.getName()));
            }
        }
    }

    private void loadCourseContentCombo() {
        cmb_course_content_add.removeAllItems();
        for (Course c : Course.getList()){
            cmb_course_content_add.addItem(new Item(c.getId(),c.getName()));
        }
    }

    private void closeScreen(){
        if (Helper.confirm("sure")){
            dispose();
            LoginGUI l = new LoginGUI();
        }
    }

    private void loadPatikaCombo() {
        cmb_course_patika.removeAllItems();
        for (Patika item : Patika.getList()){
            cmb_course_patika.addItem(new Item(item.getId(),item.getName()));
        }
    }

    private void loadCourseList() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_course_list.getModel();
        clearModel.setRowCount(0);
        int i = 0;
        for (Course course:Course.getList()){
            i=0;
            row_course_list[i++] = course.getId();
            row_course_list[i++] = course.getName();
            row_course_list[i++] = course.getLang();
            row_course_list[i++] = course.getPatika().getName();
            row_course_list[i++] = course.getEducater().getName();
            mdl_course_list.addRow(row_course_list);


        }

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
        Helper.setLayout();
        OperatorGUI gui = new OperatorGUI(new Operator());
    }



}
