package com.patika.Model;

import com.patika.Helper.DbConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StudentPatika {
    private int id;
    private int patika_id;
    private int student_id;
    private Patika patika;
    private User student;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatika_id() {
        return patika_id;
    }

    public void setPatika_id(int patika_id) {
        this.patika_id = patika_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }
    public StudentPatika(){

    }



    public Patika getPatika() {
        return patika;
    }

    public void setPatika(Patika patika) {
        this.patika = patika;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public StudentPatika(int id, int patika_id, int student_id) {
        this.id = id;
        this.patika_id = patika_id;
        this.student_id = student_id;

        this.patika = Patika.getFetch(patika_id);
        this.student = User.getFetch(student_id);
    }

    public static ArrayList<Patika> getPatikaList(int patika_id){
        ArrayList<Patika> patikas = new ArrayList<>();
        String query = "SELECT * FROM Patika WHERE patika_id ="+patika_id;
        try {
            PreparedStatement st = DbConnector.getInstace().prepareStatement(query);
            ResultSet rs = st.executeQuery();
            Patika obj;
            while (rs.next()){
                obj = new Patika(rs.getInt("id"),rs.getString("name"));
                patikas.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patikas;
    }
    public static ArrayList<StudentPatika> getRegisterPatika(int user_id){
        ArrayList<StudentPatika> studentPatikas = new ArrayList<>();
        String query = "SELECT * FROM StudentPatika WHERE user_id = "+user_id;
        StudentPatika obj;
        try {
            PreparedStatement pr = DbConnector.getInstace().prepareStatement(query);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj=new StudentPatika(rs.getInt("id"),rs.getInt("patika_id"),rs.getInt("user_id"));
                studentPatikas.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentPatikas;
    }

    public static boolean add(int patika_id,int student_id){
        String query = "INSERT INTO StudentPatika(patika_id,user_id) VALUES(?,?)";
        try {
            PreparedStatement pr = DbConnector.getInstace().prepareStatement(query);
            pr.setInt(1,patika_id);
            pr.setInt(2,student_id);
            return pr.executeUpdate() != -1;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

}
