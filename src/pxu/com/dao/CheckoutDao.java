package pxu.com.dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import pxu.com.connect.connecting;
import pxu.com.model.CheckoutModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author chinh
 */
public class CheckoutDao {

    public static ArrayList<CheckoutModel> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<CheckoutModel> lst = new ArrayList<>();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        Connection conn = connecting.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select * from check_out ";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                CheckoutModel student = new CheckoutModel();
                student.setCheckout_id(rs.getInt(1));
                student.setStudent_id(rs.getString(2));
                student.setUser_id(rs.getString(3));
                student.setRoom_id(rs.getString(4));
                student.setCheckout_date(rs.getDate(5));
                student.setReason(rs.getString(6));
                lst.add(student);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lst;
    }

    public boolean insert(CheckoutModel t) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO check_out (student_id,user_id,room_id,checkout_date,reason) VALUES (?,?,?,?,?)";
        try (Connection conn = connecting.getConnection(); PreparedStatement prstt = conn.prepareStatement(sql);) {
            prstt.setString(1, t.getStudent_id());
            prstt.setString(2, t.getUser_id());
            prstt.setString(3, t.getRoom_id());
            prstt.setDate(4, t.getCheckout_date());
            prstt.setString(5, t.getReason());
            return prstt.executeUpdate() > 0;
        }
    }
}
