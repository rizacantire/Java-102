package com.patika.Model;

import com.patika.Helper.DbConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Quiz {
    private int id;
    private int course_content_id;
    private String question;
    private String user_answer1;
    private String user_answer2;
    private String user_answer3;
    private String user_answer4;
    private String answer;

    private CourseContent content;

    public Quiz(int id, int course_content_id, String question, String user_answer1, String user_answer2, String user_answer3, String user_answer4, String answer) {
        this.id = id;
        this.course_content_id = course_content_id;
        this.question = question;
        this.user_answer1 = user_answer1;
        this.user_answer2 = user_answer2;
        this.user_answer3 = user_answer3;
        this.user_answer4 = user_answer4;
        this.answer = answer;
        this.content = CourseContent.getFetch(course_content_id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourse_content_id() {
        return course_content_id;
    }

    public void setCourse_content_id(int course_content_id) {
        this.course_content_id = course_content_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getUser_answer1() {
        return user_answer1;
    }

    public void setUser_answer1(String user_answer1) {
        this.user_answer1 = user_answer1;
    }

    public String getUser_answer2() {
        return user_answer2;
    }

    public void setUser_answer2(String user_answer2) {
        this.user_answer2 = user_answer2;
    }

    public String getUser_answer3() {
        return user_answer3;
    }

    public void setUser_answer3(String user_answer3) {
        this.user_answer3 = user_answer3;
    }

    public String getUser_answer4() {
        return user_answer4;
    }

    public void setUser_answer4(String user_answer4) {
        this.user_answer4 = user_answer4;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public CourseContent getContent() {
        return content;
    }

    public void setContent(CourseContent content) {
        this.content = content;
    }

    public static boolean add(int course_content_id, String question, String user_answer1, String user_answer2, String user_answer3, String user_answer4, String answer){
        String query = "INSERT INTO quiz(course_content_id, question, user_answer1, user_answer2, user_answer3, user_answer4, answer) VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = DbConnector.getInstace().prepareStatement(query);
            pr.setInt(1,course_content_id);
            pr.setString(2,question);
            pr.setString(3,user_answer1);
            pr.setString(4,user_answer2);
            pr.setString(5,user_answer3);
            pr.setString(6,user_answer4);
            pr.setString(7,answer);
            return  pr.executeUpdate() !=-1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static  boolean update(int id,int course_content_id, String question, String user_answer1, String user_answer2, String user_answer3, String user_answer4, String answer){

        String query = "UPDATE Quiz Set course_content_id = ?, question= ?, user_answer1= ?, user_answer2= ?, user_answer3= ?, user_answer4= ?, answer= ? WHERE id = ?";
        try {
            PreparedStatement pr = DbConnector.getInstace().prepareStatement(query);
            pr.setInt(1,course_content_id);
            pr.setString(2,question);
            pr.setString(3,user_answer1);
            pr.setString(4,user_answer2);
            pr.setString(5,user_answer3);
            pr.setString(6,user_answer4);
            pr.setString(7,answer);
            pr.setInt(8,id);
            return  pr.executeUpdate() !=-1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }



    public static boolean delete(int id){
        String query = "DELETE FROM quiz WHERE id = ?";
        try {
            PreparedStatement pr = DbConnector.getInstace().prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate() !=-1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
    public static Quiz getFetch(int quiz_id){
        Quiz obj = null;
        String query = "SELECT * FROM Quiz  WHERE id = ?";
        try {
            PreparedStatement pr = DbConnector.getInstace().prepareStatement(query);
            pr.setInt(1,quiz_id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj = new Quiz(rs.getInt("id"),rs.getInt("course_content_id"),
                        rs.getString("question"),
                        rs.getString("user_answer1"),rs.getString("user_answer2"),
                        rs.getString("user_answer3"),rs.getString("user_answer4"),
                        rs.getString("answer"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static ArrayList<Quiz> getList(){
        ArrayList<Quiz> quizzes = new ArrayList<>();
        Quiz obj;
        try {
            Statement statement = DbConnector.getInstace().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Quiz");
            while (rs.next()){
                int id = rs.getInt("id");
                int course_content_id = rs.getInt("course_content_id");
                String answer = rs.getString("answer");
                String question = rs.getString("question");
                String user_answer1 =rs.getString("user_answer1");
                String user_answer2 = rs.getString("user_answer2");
                String user_answer3 = rs.getString("user_answer3");
                String user_answer4 = rs.getString("user_answer4");
                obj = new Quiz(id,course_content_id,question,user_answer1,user_answer2,user_answer3,user_answer4,answer);
                quizzes.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quizzes;
    }
}
