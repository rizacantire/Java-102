package com.patika.Model;

import com.patika.Helper.DbConnector;
import com.patika.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Locale;

public class User {
    private int id;
    private String name;
    private String uname;
    private String pass;
    private String type;

    public User() {
    }

    public User(int id, String name, String uname, String pass, String type) {
        this.id = id;
        this.name = name;
        this.uname = uname;
        this.pass = pass;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static ArrayList<User> getList(){
        ArrayList<User> userList = new ArrayList<>();
        String query = "SELECT * FROM USER";
        try {
            Statement st = DbConnector.getInstace().createStatement();
            ResultSet rs = st.executeQuery(query);
            User obj;
            while (rs.next()){
                obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setPass(rs.getString("pass"));
                obj.setType(rs.getString("type"));
                userList.add(obj);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    public static boolean add(String name,String uname,String pass, String type){
        User findUser = User.getFetch(uname);
        String query = "INSERT INTO user(name,uname,pass,type) VALUES (?,?,?,?)";
        if (findUser!=null){
            Helper.showMsg("Kullanıcı adı sistemde kayıtlı.");
            return false;
        }
        try {
            PreparedStatement pr = DbConnector.getInstace().prepareStatement(query);
            pr.setString(1,name);
            pr.setString(2,uname);
            pr.setString(3,pass);
            pr.setString(4,type);

            return pr.executeUpdate() != -1;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return true;

    }

    public static boolean update(int id, String name,String uname,String pass, String type){
        User findUser = User.getFetch(uname);
        String query = "UPDATE  user SET name = ?,uname = ?,pass = ?,type =? WHERE id = ?";
        if (findUser!=null && findUser.getId() != id){
            Helper.showMsg("Kullanıcı adı sistemde kayıtlı.");
            return false;
        }
        try {
            PreparedStatement pr = DbConnector.getInstace().prepareStatement(query);
            pr.setString(1,name);
            pr.setString(2,uname);
            pr.setString(3,pass);
            pr.setString(4,type);
            pr.setInt(5,id);

            return pr.executeUpdate() != -1;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return true;
    }

    public static User getFetch(String uname){
        User obj = null;
        String query = "SELECT * FROM user WHERE uname = ?";

        try {
            PreparedStatement pr = DbConnector.getInstace().prepareStatement(query);
            pr.setString(1,uname);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setPass(rs.getString("pass"));
                obj.setType(rs.getString("type"));
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return obj;
    }

    public static User getFetch(int id){
        User obj = null;
        String query = "SELECT * FROM user WHERE id = ?";

        try {
            PreparedStatement pr = DbConnector.getInstace().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setPass(rs.getString("pass"));
                obj.setType(rs.getString("type"));
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return obj;
    }

    public static User getFetch(String uname,String pass){
        User obj = null;
        String query = "SELECT * FROM user WHERE uname = ? AND pass = ?";

        try {
            PreparedStatement pr = DbConnector.getInstace().prepareStatement(query);
            pr.setString(1,uname);
            pr.setString(2,pass);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
               obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setPass(rs.getString("pass"));
                obj.setType(rs.getString("type"));
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return obj;
    }

    public static boolean delete(int id){
        String query = "DELETE FROM user WHERE id = ?";
        ArrayList<Course> courses = Course.getListByUser(id);
        for (var c : courses){
            Course.delete(c.getId());
        }
        try {
            PreparedStatement pr = DbConnector.getInstace().prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return true;
    }

    public static String searchQuery(String name,String uname,String type){
        String query = "SELECT * FROM user WHERE name LIKE '%{{name}}%' AND uname LIKE '%{{uname}}%' AND type LIKE '%{{type}}%'";
        query = query.replace("{{name}}",name);
        query = query.replace("{{uname}}",uname);
        if(!type.isEmpty() || type != null){
            query = query.replace("{{type}}",type);
        }
        return query;
    }

    public static ArrayList<User> search(String searchQuery){
        ArrayList<User> userList = new ArrayList<>();
        try {
            Statement st = DbConnector.getInstace().createStatement();
            ResultSet rs = st.executeQuery(searchQuery);
            User obj;
            while (rs.next()){
                obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setPass(rs.getString("pass"));
                obj.setType(rs.getString("type"));
                userList.add(obj);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

}
