package pxu.com.dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import pxu.com.connect.connecting;
import pxu.com.model.RoomtransferModel;
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
public class RoomtransferDao {

    public static ArrayList<RoomtransferModel> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<RoomtransferModel> lst = new ArrayList<>();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        Connection conn = connecting.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select * from room_transfer ";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                RoomtransferModel student = new RoomtransferModel();
                student.setTransfer_id(rs.getInt(1));
                student.setStudent_id(rs.getString(2));
                student.setUser_id(rs.getString(3));
                student.setPrevious_room_id(rs.getString(4));
                student.setRoom_id(rs.getString(5));
                student.setTransfer_date(rs.getDate(6));
                student.setReason(rs.getString(7));
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

    public boolean insert(RoomtransferModel c) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO room_transfer (student_id,user_id,previous_room_id,room_id,transfer_date,reason) VALUES (?,?,?,?,?,?)";
        try (Connection conn = connecting.getConnection(); PreparedStatement prstt = conn.prepareStatement(sql);) {
            prstt.setString(1, c.getStudent_id());
            prstt.setString(2, c.getUser_id());
            prstt.setString(3, c.getPrevious_room_id());
            prstt.setString(4, c.getRoom_id());
            prstt.setDate(5, c.getTransfer_date());
            prstt.setString(6, c.getReason());
            return prstt.executeUpdate() > 0;
        }
    }
}
