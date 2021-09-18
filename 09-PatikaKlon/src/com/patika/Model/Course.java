package com.patika.Model;

import com.patika.Helper.DbConnector;
import com.patika.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Course {
    private int id;
    private int user_id;
    private int patika_id;
    private String name;
    private String lang;

    private Patika patika;
    private User educater;

    public Course(){}

    public Course(int id, int user_id, int patika_id, String name, String lang) {
        this.id = id;
        this.user_id = user_id;
        this.patika_id = patika_id;
        this.name = name;
        this.lang = lang;
        this.patika = Patika.getFetch(patika_id);
        this.educater = User.getFetch(user_id);

    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPatika_id() {
        return patika_id;
    }

    public void setPatika_id(int patika_id) {
        this.patika_id = patika_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Patika getPatika() {
        return patika;
    }

    public void setPatika(Patika patika) {
        this.patika = patika;
    }

    public User getEducater() {
        return educater;
    }

    public void setEducater(User educater) {
        this.educater = educater;
    }

    public static ArrayList<Course> getList() {

        ArrayList<Course> courseList = new ArrayList<>();
        Course obj;
        try {
            Statement st = DbConnector.getInstace().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM COURSE");
            while (rs.next()){
                int id = rs.getInt("id");
                int user_id = rs.getInt("user_id");
                int patika_id = rs.getInt("patika_id");
                String name = rs.getString("name");
                String lang = rs.getString("lang");
                obj = new Course(id,user_id,patika_id,name,lang);
                courseList.add(obj);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return courseList;

    }

    public static ArrayList<Course> getListByUser(int userid) {

        ArrayList<Course> courseList = new ArrayList<>();
        Course obj;
        try {
            Statement st = DbConnector.getInstace().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM course WHERE user_id = "+userid);
            while (rs.next()){
                int id = rs.getInt("id");
                int user_id = rs.getInt("user_id");
                int patika_id = rs.getInt("patika_id");
                String name = rs.getString("name");
                String lang = rs.getString("lang");
                obj = new Course(id,user_id,patika_id,name,lang);
                courseList.add(obj);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return courseList;

    }

    public static boolean delete(int id){
        String query = "DELETE FROM course WHERE id = ?";
        try {
            PreparedStatement pr = DbConnector.getInstace().prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return true;
    }
    public static boolean add(int user_id,int patika_id,String name,String lang){
        String query = "INSERT INTO course(user_id,patika_id,name,lang) VALUES (?,?,?,?)";

        try {
            PreparedStatement pr = DbConnector.getInstace().prepareStatement(query);
            pr.setInt(1,user_id);
            pr.setInt(2,patika_id);
            pr.setString(3,name);
            pr.setString(4,lang);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;

    }

    public static boolean update(int id,int user_id,int patika_id,String name,String lang){
        String query = "UPDATE  course SET user_id = ?,patika_id = ?,name = ?,lang =? WHERE id = ?";

        try {
            PreparedStatement pr = DbConnector.getInstace().prepareStatement(query);
            pr.setInt(1,user_id);
            pr.setInt(2,patika_id);
            pr.setString(3,name);
            pr.setString(4,lang);
            pr.setInt(5,id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;

    }

    public static Course getFetch(String name){
        Course obj = null;
        String query = "SELECT * FROM course WHERE name = ?";

        try {
            PreparedStatement pr = DbConnector.getInstace().prepareStatement(query);
            pr.setString(1,name);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj = new Course();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setLang(rs.getString("lang"));
                obj.setPatika_id(rs.getInt("patika_id"));
                obj.setUser_id(rs.getInt("user_id"));
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return obj;
    }

    public static Course getFetch(int id){
        Course obj = null;
        String query = "SELECT * FROM course WHERE id = ?";
        try {
            PreparedStatement pr = DbConnector.getInstace().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj = new Course(rs.getInt("id"),rs.getInt("user_id"),rs.getInt("patika_id"),rs.getString("name"),rs.getString("lang"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
