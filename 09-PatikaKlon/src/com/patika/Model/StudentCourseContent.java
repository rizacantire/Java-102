package com.patika.Model;

import com.patika.Helper.DbConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentCourseContent {
    private int id;
    private int user_id;
    private int course_content_id;
    private boolean isSuccess;

    private User user;
    private CourseContent courseContent;

    public StudentCourseContent(int id, int user_id, int course_content_id, boolean isSuccess) {
        this.id = id;
        this.user_id = user_id;
        this.course_content_id = course_content_id;
        this.isSuccess = isSuccess;
        this.user = User.getFetch(user_id);
        this.courseContent = CourseContent.getFetch(course_content_id);
    }

    public static boolean add(int user_id,int course_content_id,boolean isSuccess){
        String query = "INSERT INTO StudentCourseContent(user_id,course_content_id,isSuccess) VALUES(?,?,?)";
        try {
            PreparedStatement pr = DbConnector.getInstace().prepareStatement(query);
            pr.setInt(1,user_id);
            pr.setInt(2,course_content_id);
            pr.setBoolean(3,isSuccess);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public static boolean update(int id,int user_id,int course_content_id, boolean isSuccess){
        String query = "UPDATE StudentCourseContent SET user_id =?,course_content_id=?, isSuccess=? WHERE user_id =?";
        try {
            PreparedStatement pr = DbConnector.getInstace().prepareStatement(query);
            pr.setInt(1,user_id);
            pr.setInt(2,course_content_id);
            pr.setBoolean(3,isSuccess);
            pr.setInt(4,id);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static ArrayList<StudentCourseContent> getAll(int userid){
        ArrayList<StudentCourseContent> contents = new ArrayList<>();
        StudentCourseContent obj;
        String query = "SELECT * FROM StudentCourseContent WHERE user_id="+userid;
        try {
            PreparedStatement pr = DbConnector.getInstace().prepareStatement(query);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new StudentCourseContent(
                        rs.getInt("id"),rs.getInt("user_id"),
                        rs.getInt("course_content_id"),rs.getBoolean("isSuccess")
                );
                contents.add(obj);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return contents;
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

    public int getCourse_content_id() {
        return course_content_id;
    }

    public void setCourse_content_id(int course_content_id) {
        this.course_content_id = course_content_id;
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

    public CourseContent getCourseContent() {
        return courseContent;
    }

    public void setCourseContent(CourseContent courseContent) {
        this.courseContent = courseContent;
    }
}
