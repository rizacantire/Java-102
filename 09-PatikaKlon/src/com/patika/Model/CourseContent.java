package com.patika.Model;

import com.patika.Helper.DbConnector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CourseContent {
    private int id;
    private int course_id;
    private String description;
    private String title;
    private String youtube;

    private Course course;

    public CourseContent(int id, int course_id, String description, String title,String youtube) {
        this.id = id;
        this.course_id = course_id;
        this.description = description;
        this.title = title;
        this.youtube = youtube;
        this.course = Course.getFetch(course_id);
    }

    public int getId() {
        return id;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public static CourseContent getFetch(int id){
        CourseContent obj = null;
        String query = "SELECT * FROM CourseContent  WHERE id = ?";
        try {
            PreparedStatement pr = DbConnector.getInstace().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj = new CourseContent(rs.getInt("id"),rs.getInt("course_id"),rs.getString("description"),rs.getString("title"),rs.getString("youtube"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }
    public static boolean add(int course_id,String description,String title,String youtube){
        String query = "INSERT INTO CourseContent(course_id,title,description,youtube) VALUES(?,?,?,?)";
        try {
            PreparedStatement pr = DbConnector.getInstace().prepareStatement(query);
            pr.setInt(1,course_id);
            pr.setString(2,title);
            pr.setString(3,description);
            pr.setString(4,youtube);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public static boolean update(int id,int course_id,String description,String title,String youtube){
        String query = "UPDATE CourseContent SET course_id =?,title=?,description=?,youtube=? WHERE id = ?";
        try {
            PreparedStatement pr = DbConnector.getInstace().prepareStatement(query);
            pr.setInt(1,course_id);
            pr.setString(2,title);
            pr.setString(3,description);
            pr.setString(4,youtube);
            pr.setInt(5,id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public static boolean delete(int id){
        String  query = "DELETE FROM CourseContent WHERE id = ?";
        try {
            PreparedStatement pr = DbConnector.getInstace().prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
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
                String description = rs.getString("description");
                String title = rs.getString("title");
                String youtube= rs.getString("youtube");
                obj = new CourseContent(id,course_id,description,title,youtube);
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
                String description = rs.getString("description");
                String title = rs.getString("title");
                String youtube= rs.getString("youtube");
                obj = new CourseContent(id,course_id,description,title,youtube);
                courseContents.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseContents;
    }
}
