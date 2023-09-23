package pxu.com.dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import pxu.com.connect.connecting;
import pxu.com.model.StudentModel;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.sql.rowset.serial.SerialBlob;
import java.util.Date;

/**
 *
 * @author chinh
 */
public class StudentDao {

    public static ArrayList<StudentModel> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<StudentModel> lst = new ArrayList<>();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        Connection conn = connecting.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select * from student ";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                StudentModel student = new StudentModel();
                student.setStudent_id(rs.getString(1));
                student.setStudent_name(rs.getString(2));
                student.setFaculty(rs.getString(3));
                student.setMajor(rs.getString(4));
                student.setBirth_date(rs.getDate(5));
                student.setGmail(rs.getString(6));
                student.setPhone_number(rs.getString(7));
                student.setGender(rs.getString(8));
                student.setHometown(rs.getString(9));
                student.setRoom_id(rs.getString(10));
                student.setViolation_count(rs.getInt(11));
                student.setCheck_in_date(rs.getDate(12));
                student.setStatus(rs.getString(13));
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

    public boolean insert(StudentModel s) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO student (student_id,student_name,faculty,major,birth_date,gmail,phone_number,"
                + " gender,hometown,room_id,violation_count,"
                + " check_in_date,status,student_image) VALUES (?,?, ?, ?, ?, ?,?,?,?,?,?,?,?,?)";
        try (Connection conn = connecting.getConnection(); PreparedStatement prstt = conn.prepareStatement(sql);) {
            prstt.setString(1, s.getStudent_id());
            prstt.setString(2, s.getStudent_name());
            prstt.setString(3, s.getFaculty());
            prstt.setString(4, s.getMajor());
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String ngaysinh = df.format(s.getBirth_date());
            prstt.setString(5, ngaysinh);
            prstt.setString(6, s.getGmail());
            prstt.setString(7, s.getPhone_number());
            prstt.setString(8, s.getGender());
            prstt.setString(9, s.getHometown());
            prstt.setString(10, s.getRoom_id());
            prstt.setInt(11, s.getViolation_count());
            DateFormat dff = new SimpleDateFormat("yyyy-MM-dd");
            String ngayvao = dff.format(s.getCheck_in_date());
            prstt.setString(12, ngayvao);
            prstt.setString(13, s.getStatus());
            if (s.getStudent_image() != null) {
                Blob hinh = new SerialBlob(s.getStudent_image());
                prstt.setBlob(14, hinh);
            } else {
                Blob hinh = null;
                prstt.setBlob(14, hinh);
            }
            return prstt.executeUpdate() > 0;
        }
    }

    public boolean update(StudentModel s) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE student set room_id=?"
                + " where student_id=?";
        try (Connection conn = connecting.getConnection(); PreparedStatement prstt = conn.prepareStatement(sql);) {
            prstt.setString(1, s.getRoom_id());
            prstt.setString(2, s.getStudent_id());
            return prstt.executeUpdate() > 0;
        }
    }

    public boolean updatetrangthai(StudentModel s) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE student set status=?"
                + " where student_id=?";
        try (Connection conn = connecting.getConnection(); PreparedStatement prstt = conn.prepareStatement(sql);) {
            prstt.setString(1, s.getStatus());
            prstt.setString(2, s.getStudent_id());
            return prstt.executeUpdate() > 0;
        }
    }

    public boolean delete(StudentModel ph) throws SQLException, ClassNotFoundException {
        String sql = "delete from student where student_id=?";
        Connection conn = connecting.getConnection();
        PreparedStatement prstt = conn.prepareStatement(sql);
        {
            prstt.setString(1, ph.getStudent_id());
            return prstt.executeUpdate() > 0;
        }
    }

    public StudentModel FindManv(String maSV) throws SQLException, ClassNotFoundException {
        String sql = "select * from student where student_id=?";
        try (Connection conn = connecting.getConnection(); PreparedStatement prst = conn.prepareStatement(sql);) {
            prst.setString(1, maSV);
            try (ResultSet rs = prst.executeQuery();) {
                if (rs.next()) {
                    StudentModel s = new StudentModel();
                    s.setStudent_id(rs.getString("student_id"));
                    return s;
                }
            }
            return null;
        }
    }

    public StudentModel FindMaSv(String maSV) throws SQLException, ClassNotFoundException {
        String sql = "select * from student where student_id=?";
        try (Connection conn = connecting.getConnection(); PreparedStatement prst = conn.prepareStatement(sql);) {
            prst.setString(1, maSV);
            try (ResultSet rs = prst.executeQuery();) {
                if (rs.next()) {
                    StudentModel student = new StudentModel();
                    student.setStudent_id(rs.getString("student_id"));
                    student.setStudent_name(rs.getString("student_name"));
                    student.setFaculty(rs.getString("faculty"));
                    student.setMajor(rs.getString("major"));
                    student.setBirth_date(rs.getDate("birth_date"));
                    student.setGmail(rs.getString("gmail"));
                    student.setPhone_number(rs.getString("phone_number"));
                    student.setGender(rs.getString("gender"));
                    student.setHometown(rs.getString("hometown"));
                    student.setRoom_id(rs.getString("room_id"));
                    student.setViolation_count(rs.getInt("violation_count"));
                    student.setCheck_in_date(rs.getDate("check_in_date"));
                    student.setStatus(rs.getString("status"));
//                    student.setStudent_image(rs.getBytes("student_image"));
                    Blob blob = rs.getBlob("student_image");
                    if (blob != null) {
                        student.setStudent_image(blob.getBytes(1, (int) blob.length()));
                    }
                    return student;
                }
            }
            return null;
        }
    }

    public static boolean updateStudent(StudentModel student) throws SQLException, ClassNotFoundException {
        Connection conn = connecting.getConnection();
        try {
            String sql = "update student set student_name = ?, faculty = ?, major = ?, birth_date = ?, "
                    + "id_card = ?, phone_number = ?, gender = ?, hometown = ?, room_id = ?, "
                    + "violation_count = ?, check_in_date = ?, status = ? ,student_image=? where student_id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, student.getStudent_name());
            pstmt.setString(2, student.getFaculty());
            pstmt.setString(3, student.getMajor());
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String ngaysinh = df.format(student.getBirth_date());
            pstmt.setString(4, ngaysinh);
            pstmt.setString(5, student.getGmail());
            pstmt.setString(6, student.getPhone_number());
            pstmt.setString(7, student.getGender());
            pstmt.setString(8, student.getHometown());
            pstmt.setString(9, student.getRoom_id());
            pstmt.setInt(10, student.getViolation_count());
            DateFormat dff = new SimpleDateFormat("yyyy-MM-dd");
            String ngayvao = dff.format(student.getCheck_in_date());
            pstmt.setString(11, ngayvao);
            pstmt.setString(12, student.getStatus());
            if (student.getStudent_image() != null) {
                Blob hinh = new SerialBlob(student.getStudent_image());
                pstmt.setBlob(13, hinh);
            } else {
                Blob hinh = null;
                pstmt.setBlob(13, hinh);
            }
            pstmt.setString(14, student.getStudent_id());
            int rowsAffected = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return rowsAffected > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
