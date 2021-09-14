package com.patika.Model;

import com.patika.Helper.DbConnector;
import org.sqlite.core.DB;

import javax.swing.plaf.nimbus.State;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CourseContent {
    private int id;
    private int course_id;
    private String content;
    private String title;

    private Course course;

    public CourseContent(int id, int course_id, String content, String title) {
        this.id = id;
        this.course_id = course_id;
        this.content = content;
        this.title = title;
        this.course = Course.getFetch(course_id);
    }

    public static CourseContent getFetch(int id){
        CourseContent obj = null;
        String query = "SELECT * FROM CourseContent  WHERE id = ?";
        try {
            PreparedStatement pr = DbConnector.getInstace().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj = new CourseContent(rs.getInt("id"),rs.getInt("course_id"),rs.getString("content"),rs.getString("title"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static ArrayList<CourseContent> getListByCourseId(int byId){
        ArrayList<CourseContent> courseContents = new ArrayList<>();
        CourseContent obj;
        try {
            Statement statement = DbConnector.getInstace().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM CourseContent WHERE course_id = "+byId);
            while (rs.next()){
                int id = rs.getInt("id");
                int course_id = rs.getInt("course_id");
                String content = rs.getString("content");
                String title = rs.getString("title");
                obj = new CourseContent(id,course_id,content,title);
                courseContents.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseContents;
    }

    public static ArrayList<CourseContent> getList(){
        ArrayList<CourseContent> courseContents = new ArrayList<>();
        CourseContent obj;
        try {
            Statement statement = DbConnector.getInstace().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM CourseContent");
            while (rs.next()){
                int id = rs.getInt("id");
                int course_id = rs.getInt("course_id");
                String content = rs.getString("content");
                String title = rs.getString("title");
                obj = new CourseContent(id,course_id,content,title);
                courseContents.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseContents;
    }
}
