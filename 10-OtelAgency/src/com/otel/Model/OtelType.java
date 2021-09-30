package com.otel.Model;

import com.otel.Helper.DbConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OtelType {
    private int id;
    private String type;

    public OtelType(int id, String type) {
        this.id = id;
        this.type = type;
    }


    public static ArrayList<OtelType> getList() {

        ArrayList<OtelType> otelTypes = new ArrayList<>();
        OtelType obj;

        try {
            Statement st = DbConnector.getInstace().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM OtelType");
            while (rs.next()){
                int id = rs.getInt("id");
                String type = rs.getString("type");
                obj = new OtelType(id,type);
                otelTypes.add(obj);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return otelTypes;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
