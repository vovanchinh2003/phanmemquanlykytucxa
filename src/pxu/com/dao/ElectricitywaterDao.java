/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pxu.com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import pxu.com.connect.connecting;
import pxu.com.model.ElectricitywaterModel;

/**
 *
 * @author chinh
 */
public class ElectricitywaterDao {

    public static ArrayList<ElectricitywaterModel> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<ElectricitywaterModel> lst = new ArrayList<>();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        Connection conn = connecting.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select * from ELECTRICITY_WATER ";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ElectricitywaterModel d = new ElectricitywaterModel();
                d.setId(rs.getInt(1));
                d.setRoom_id(rs.getString(2));
                d.setUser_id(rs.getString(3));
                d.setOld_electricity_reading(rs.getInt(4));
                d.setNew_electricity_reading(rs.getInt(5));
                d.setOld_water_reading(rs.getInt(6));
                d.setNew_water_reading(rs.getInt(7));
                d.setElectricity_price(rs.getFloat(8));
                d.setPayment_time(rs.getDate(9));
                d.setStatus(rs.getString(10));
                lst.add(d);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lst;
    }

    public boolean insert(ElectricitywaterModel ph) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO ELECTRICITY_WATER (room_id,user_id,old_electricity_reading,new_electricity_reading,old_water_reading,new_water_reading,electricity_price,payment_time,status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = connecting.getConnection(); PreparedStatement prstt = conn.prepareStatement(sql);) {
            prstt.setString(1, ph.getRoom_id());
            prstt.setString(2, ph.getUser_id());
            prstt.setInt(3, ph.getOld_electricity_reading());
            prstt.setInt(4, ph.getNew_electricity_reading());
            prstt.setInt(5, ph.getOld_water_reading());
            prstt.setInt(6, ph.getNew_water_reading());
            prstt.setFloat(7, ph.getElectricity_price());
            prstt.setDate(8, Date.valueOf(ph.getPayment_time().toString()));
            prstt.setString(9, ph.getStatus());
            return prstt.executeUpdate() > 0;
        }
    }

}
