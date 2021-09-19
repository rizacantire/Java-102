package com.patika.Model;

import com.patika.Helper.DbConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentCourse {
    private int id;
    private int user_id;
    private int course_id;
    private boolean isSuccess;

    private User user;
    private Course course;

    public StudentCourse(int id, int user_id, int course_id, boolean isSuccess) {
        this.id = id;
        this.user_id = user_id;
        this.course_id = course_id;
        this.isSuccess = isSuccess;
        this.user = User.getFetch(user_id);
        this.course = Course.getFetch(course_id);
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

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public static ArrayList<StudentCourse> getAll(int user_id){
        ArrayList<StudentCourse> studentCourses = new ArrayList<>();
        StudentCourse obj;
        String query = "SELECT * FROM StudentCourse WHERE user_id = "+user_id;
        try {
            PreparedStatement pr = DbConnector.getInstace().prepareStatement(query);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new StudentCourse(rs.getInt("id"),rs.getInt("user_id"),
                        rs.getInt("course_id"),rs.getBoolean("isSuccess"));
                studentCourses.add(obj);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return studentCourses;
    }

    public static boolean add(int user_id,int course_id, boolean isSuccess){
        String query = "INSERT INTO StudentCourse(user_id,course_id,isSuccess) VALUES(?,?,?)";
        try {
            PreparedStatement pr = DbConnector.getInstace().prepareStatement(query);
            pr.setInt(1,user_id);
            pr.setInt(2,course_id);
            pr.setBoolean(3,isSuccess);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  true;
    }

    public static boolean update(int id,int user_id,int course_id, boolean isSuccess){
        String query = "UPDATE StudentCourse SET user_id = ?,course_id = ?, isSuccess = ? WHERE id = ?";
        try {
            PreparedStatement pr = DbConnector.getInstace().prepareStatement(query);
            pr.setInt(1,user_id);
            pr.setInt(2,course_id);
            pr.setBoolean(3,isSuccess);
            pr.setInt(4,id);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return true;
    }
}
