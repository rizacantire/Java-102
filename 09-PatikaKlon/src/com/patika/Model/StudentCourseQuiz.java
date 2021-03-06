package com.patika.Model;

import com.patika.Helper.DbConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StudentCourseQuiz {
    private int id;
    private int user_id;
    private int quiz_id;
    private boolean isSuccess;

    private User user;
    private Quiz quiz;

    public StudentCourseQuiz(int id, int user_id, int quiz_id, boolean isSuccess) {
        this.id = id;
        this.user_id = user_id;
        this.quiz_id = quiz_id;
        this.isSuccess = isSuccess;
        this.user = User.getFetch(user_id);
        this.quiz = Quiz.getFetch(quiz_id);
    }
    public static boolean add(int user_id,int quiz_id,boolean isSuccess){
        String query = "INSERT INTO StudentCourseQuiz(user_id,quiz_id,isSuccess) VALUES(?,?,?)";

        try {
            PreparedStatement pr = DbConnector.getInstace().prepareStatement(query);
            pr.setInt(1,user_id);
            pr.setInt(2,quiz_id);
            pr.setBoolean(3,isSuccess);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;

    }

    public static boolean update(int id,boolean isSuccess){
        String query = "UPDATE StudentCourseQuiz SET isSuccess = ? WHERE id = ?";
        try {
            PreparedStatement pr = DbConnector.getInstace().prepareStatement(query);
            pr.setBoolean(1,isSuccess);
            pr.setInt(2,id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static ArrayList<StudentCourseQuiz> getList(){
        ArrayList<StudentCourseQuiz> courseQuizs = new ArrayList<>();
        StudentCourseQuiz obj;
        try {
            Statement pr = DbConnector.getInstace().createStatement();
            ResultSet rs = pr.executeQuery("SELECT * FROM StudentCourseQuiz");
            int id = rs.getInt("id");
            int user_id = rs.getInt("user_id");
            int quiz_id = rs.getInt("quiz_id");
            boolean isSuccess = rs.getBoolean("isSuccess");
            rs.close();
            obj = new StudentCourseQuiz(id,user_id,quiz_id,isSuccess);
            courseQuizs.add(obj);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courseQuizs;
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

    public int getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean success) {
        isSuccess = success;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
