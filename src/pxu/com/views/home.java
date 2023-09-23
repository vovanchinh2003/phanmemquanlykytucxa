/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pxu.com.views;

import pxu.com.dialogchek.imageheiper;
import pxu.com.dialogchek.showuser;
import pxu.com.connect.connecting;
import pxu.com.dao.CheckoutDao;
import pxu.com.dao.StudentDao;
import pxu.com.dao.RoomrentalDao;
import pxu.com.dao.RoomtransferDao;
import pxu.com.model.CheckoutModel;
import pxu.com.model.StudentModel;
import pxu.com.model.RoomrentalModel;
import pxu.com.model.RoomtransferModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.Timer;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import pxu.com.dao.ElectricitywaterDao;
import pxu.com.dao.RoomDao;
import pxu.com.model.ElectricitywaterModel;
import pxu.com.model.RoomModel;

/**
 *
 * @author chinh
 */
public class home extends javax.swing.JFrame implements MouseListener {

    private byte[] resonalImage;

    /**
     * Creates new form homee
     */
    public home() throws SQLException {
        initComponents();
        this.setSize(1500, 750);
        this.setLocationRelativeTo(null);
        taophong();
        pro();
        showTime();
        showdate();
        rightmouse();
        loadcombomaphong();
        this.setSize(1500, 750);
        this.setLocationRelativeTo(null);
    }

//    public void HIENTHI() {
//        try {
//            Connection conn = connecting.getConnection();
//            Statement s = conn.createStatement();
//            ResultSet rs = s.executeQuery("SELECT MAX(student_id) FROM student");
//            rs.next();
//            rs.getString("MAX(student_id)");
//            if (rs.getString("MAX(student_id)") == null) {
//                txtmasv.setText("C1001001");
//            } else {
//                long id = Long.parseLong(rs.getString("MAX(student_id)").substring(3, rs.getString("MAX(student_id)").length()));
//                id++;
//                txtmasv.setText("C1001" + String.format("%03d", id));
//                txtmasv.setEditable(false);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
    public void HIENTHI() {
        try {
            Connection conn = connecting.getConnection();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT MAX(student_id) FROM student");
            rs.next();
            String maxStudentId = rs.getString(1); // Use rs.getString(1) to get the value
            if (maxStudentId == null) {
                txtmasv.setText("C1001001");
            } else {
                long id = Long.parseLong(maxStudentId.substring(3));
                id++;
                txtmasv.setText("C100" + String.format("%03d", id)); // Format id with leading zeros
//                txtmasv.setEditable(false);
            }
            rs.close();
            s.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void HIENTHIphong() {
        try {
            Connection conn = connecting.getConnection();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT MAX(room_id) AS max_room_id FROM ROOM");
            rs.next();
            String maxRoomId = rs.getString("max_room_id");

            if (maxRoomId == null) {
                txtmaphong.setText("Phòng 01");
            } else {
                // Extract the numeric part of maxRoomId
                String numericPart = maxRoomId.replaceAll("\\D+", ""); // Remove non-numeric characters

                if (!numericPart.isEmpty()) {
                    long id = Long.parseLong(numericPart);
                    id++;
                    txtmaphong.setText("Phòng " + String.format("%01d", id));
//                    txtmaphong.setEditable(false);
                } else {

                }
            }

            // Close the database resources in a finally block
            rs.close();
            s.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            // Handle exceptions as needed
        }
    }

    private void pro() {
        txtmand.setText(showuser.nguoiDangNhap.getFull_name());
        txtvaitro.setText(showuser.nguoiDangNhap.getPosition());
        if (showuser.nguoiDangNhap.getPosition().equals("Quản lý")) {

        } else if (showuser.nguoiDangNhap.getPosition().equals("Nhân viên")) {
            jButton1.setEnabled(false);
            jButton2.setEnabled(false);
            jButton3.setEnabled(false);
//            jButton7.setEnabled(false);
        }
    }

    private void rightmouse() {
        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    JPopupMenu popup = new JPopupMenu();
                    JMenuItem xoaphong = new JMenuItem("Chuyển phòng");
                    xoaphong.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            jFrame3.setVisible(true);
                        }
                    });
                    popup.add(xoaphong);
                    JMenuItem XEMHDD = new JMenuItem("Trả phòng");
                    XEMHDD.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            jFrame4.setVisible(true);
                        }
                    });
                    popup.add(XEMHDD);
                    popup.show(jTable1, e.getX(), e.getY());
                }
            }
        });
    }

    private void showdate() {
        java.util.Date d = new java.util.Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        String dat = s.format(d);
        lbtDate.setText(dat);

    }

    private void showTime() {
        new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.util.Date d = new java.util.Date();
                SimpleDateFormat s = new SimpleDateFormat("hh - mm - ss");
                String tim = s.format(d);
                lblTime.setText(tim);
            }
        }).start();
    }

    private void taophong() {
        try {
            Connection connection = connecting.getConnection();
            Statement statement = connection.createStatement();
            String sql = "select * from room";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                createRoomPanel(resultSet);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        pack();
        setLocationRelativeTo(null);
    }

    // Modify the createRoomPanel method
    private void createRoomPanel(ResultSet resultSet) {
        try {
            JPanel panel = createBasicRoomPanel(resultSet);
            setRoomDetails(resultSet, panel);

            // Add the context menu for each room panel
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON3) {
                        // Replace this comment with the code to showuser the context menu
                        JPopupMenu popup = createContextMenu();
                        popup.show(panel, e.getX(), e.getY());
                    }
                }
            });
            // Add the panel to the main panel
            addRoomToMainPanel(panel);
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

// Add this method to create the context menu
    private JPopupMenu createContextMenu() {
        JPopupMenu popup = new JPopupMenu();
//         Replace these comments with your actual menu items
//        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pxu/com/images/hoadon.png"))); // NOI18N

        ImageIcon icon = new ImageIcon(getClass().getResource("/pxu/com/images/xem.jpg"));
        JMenuItem XEMHDD = new JMenuItem("Xem chi tiết thuê phòng", icon);
        XEMHDD.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    laysinhvien();
                    jFrame2.setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
//        Color XEMHDDh = new Color(0, 128, 0);
//        XEMHDD.setForeground(XEMHDDh);
        popup.add(XEMHDD);
        ImageIcon icona = new ImageIcon(getClass().getResource("/pxu/com/images/moi.png"));
        JMenuItem themp = new JMenuItem("Thuê phòng", icona);
        themp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HIENTHI();
                jFrame1.setVisible(true);
            }
        });
//        Color thempp = new Color(0, 128, 0);
//        themp.setForeground(thempp);
        popup.add(themp);
        ImageIcon icond = new ImageIcon(getClass().getResource("/pxu/com/images/hoatdong1.png"));
        JMenuItem themphong = new JMenuItem("Thêm phòng", icond);
        themphong.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jFrame5.setVisible(true);
            }
        });
        popup.add(themphong);
        ImageIcon icons = new ImageIcon(getClass().getResource("/pxu/com/images/exit.png"));
        JMenuItem xoaphong = new JMenuItem("Xoá phòng", icons);
        xoaphong.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    RoomModel model = new RoomModel();
                    model.setRoom_id(txtmp.getText());
                    RoomDao dao = new RoomDao();
                    dao.delete(model);
                    reloadRoomStatus();
                    JOptionPane.showMessageDialog(rootPane, "Xoá phòng thành công !!!");
                } catch (SQLException ex) {
                    Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
//        Color xoaphongp = new Color(255, 0, 0);
//        xoaphong.setForeground(xoaphongp);
        popup.add(xoaphong);
        ImageIcon iconw = new ImageIcon(getClass().getResource("/pxu/com/images/dn.png"));
        JMenuItem diennuoc = new JMenuItem("Tính điện nước", iconw);
        diennuoc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    laychisocu();
                    laydiennuoc();
                    jFrame6.setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
//        Color diennuocn = new Color(0, 0, 0);
//        diennuoc.setForeground(diennuocn);
        popup.add(diennuoc);
        return popup;
    }

    private void laydiennuoc() throws SQLException, ClassNotFoundException {
        Connection conn = connecting.getConnection();
        try {
            String sqll = "select * from ELECTRICITY_WATER where status=N'Đã thanh toán' and  room_id=N'" + txtmp.getText() + "'";
            String[] aray = {"Mã phòng", "Mã quản lý", "Chỉ số điện cũ", "Chỉ số điện mới", "Chỉ số nước cũ", "Chỉ số nước mới", "Tổng tiền", "Thời gian trả", "Trạng thái"};
            DefaultTableModel model = new DefaultTableModel(aray, 0);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqll);
            while (rs.next()) {
                Vector vector = new Vector();
                vector.add(rs.getString("room_id"));
                vector.add(rs.getString("user_id"));
                vector.add(rs.getInt("old_electricity_reading"));
                vector.add(rs.getInt("new_electricity_reading"));
                vector.add(rs.getInt("old_water_reading"));
                vector.add(rs.getInt("new_water_reading"));
                vector.add(rs.getFloat("electricity_price"));
                vector.add(rs.getDate("payment_time"));
                vector.add(rs.getString("status"));
                model.addRow(vector);
            }
            tablediennuo.setModel(model);
        } catch (SQLException e) {
        }
    }

    private void laychisocu() {
        String sql = "select * from ELECTRICITY_WATER where status=N'Đã thanh toán' and  room_id=N'" + txtmp.getText() + "'";
        try {
            Connection conn = connecting.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                txtsodiencu.setText(String.valueOf(rs.getInt("new_electricity_reading")));
                txtsonuocu.setText(String.valueOf(rs.getInt("old_water_reading")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private JPanel createBasicRoomPanel(ResultSet resultSet) throws SQLException {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 6, 3));
        panel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        panel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        int numberOfGuests = resultSet.getInt("occupancy_count");
        Color backgroundColor = (numberOfGuests > 0) ? new Color(34, 139, 34) : new Color(255, 69, 0);
        panel.setBackground(backgroundColor);
        // Thêm sự kiện xử lý chuột cho panel
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Khi chuột vào, thực hiện các hành động mong muốn
                JPanel clickedPanel = (JPanel) e.getSource();
                JLabel roomNumberLabel = (JLabel) clickedPanel.getComponent(0);
                String roomNumber = roomNumberLabel.getText();
                // Hiển thị thông tin phòng hoặc thực hiện các hành động khác
                txtmp.setText(roomNumber);
                txtmaphongsvdango.setText(roomNumber);
                Color a = new Color(128, 128, 128);
                clickedPanel.setBackground(a);
                HIENTHIphong();
                HIENTHI();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Khi chuột ra, trở về màu nền ban đầu
                JPanel clickedPanel = (JPanel) e.getSource();
                clickedPanel.setBackground(backgroundColor);
            }
        });
        return panel;
    }

    private void setRoomDetails(ResultSet resultSet, JPanel panel) {
        try {
            String maphong = resultSet.getString("room_id");
            int songuoi = resultSet.getInt("occupancy_count");
            int songiuong = resultSet.getInt("bed_count");
//            double giatien = resultSet.getInt("room_price");
            String loaiphong = resultSet.getString("room_type");

            JLabel label1 = createLabel(maphong);
            JLabel label2 = createLabel("Số người: " + String.valueOf(songuoi));
            JLabel label3 = createLabel("Loại phòng: " + String.valueOf(loaiphong));
            JLabel label4 = createLabel("Số giường: " + String.valueOf(songiuong));
            JLabel iconLabel = createLabel("");
            ImageIcon icon = new ImageIcon(getClass().getResource("/pxu/com/images/phong.png"));
            iconLabel.setIcon(icon);
            // nút nhận sự kiện
            panel.addMouseListener(this);

            panel.add(label1);
            panel.add(label2);
            panel.add(label3);
            panel.add(label4);
            panel.add(iconLabel);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("", Font.BOLD, 15));
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setHorizontalTextPosition(JLabel.CENTER);
        return label;
    }

    private void addRoomToMainPanel(JPanel roomPanel) {
        panel1.add(roomPanel);

    }

    private void reloadRoomStatus() {
        panel1.removeAll(); // Xóa các phòng hiện tại trên giao diện
        try {
            Connection connection = connecting.getConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM room";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                createRoomPanel(resultSet);
            }

            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        panel1.revalidate(); // Cập nhật giao diện sau khi thêm lại các phòng
        panel1.repaint();
    }

    private void searchRooms(String searchQuery) {
        panel1.removeAll(); // Xóa các phòng hiện tại trên giao diện
        try {
            Connection connection = connecting.getConnection();
            Statement statement = connection.createStatement();
            String sql;

            sql = "SELECT * FROM room WHERE room_id LIKE '%" + searchQuery + "%' OR room_type LIKE '%" + searchQuery + "%'";

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                createRoomPanel(resultSet);
            }

            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        panel1.revalidate(); // Cập nhật giao diện sau khi thêm lại các phòng
        panel1.repaint();
    }

    private void capnhatsonguoiphongmoi() {
        try {
            String sqlsqlc = "select * from room where room_id=N'" + txtmp.getText() + "'";
            Connection conn = connecting.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlsqlc);
            if (rs.next()) {
                String sqlUpdate = "UPDATE room SET occupancy_count=" + (rs.getInt("occupancy_count") + 1) + " where  room_id=N'" + txtmp.getText() + "'";
                stmt.executeUpdate(sqlUpdate);
            }
//            JOptionPane.showMessageDialog(rootPane, "Thuê phòng thành công !!!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void loadcombomaphong() throws SQLException {
        String sql = "select room_id from room";
        Connection conn = connecting.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            combomap.addItem(rs.getString(1));
        }
        rs.close();
        conn.close();
        stmt.close();
    }

    private boolean chekngayvao() {
        try {
            String sqlsqlc = "select * from student where student_id=N'" + txtmasv1.getText() + "'";
            Connection conn = connecting.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlsqlc);
            while (rs.next()) {
                if (datengaychuyen.getDate().after(rs.getDate("check_in_date"))) {
                    return true;
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Ngày chuyển không được nhỏ hơn ngày vào !!!");
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    private boolean checksonguoiophongmoi() {
        try {
            String sqlsqlc = "select * from room where room_id=N'" + combomap.getSelectedItem() + "'";
            Connection conn = connecting.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlsqlc);
            while (rs.next()) {
                if (rs.getInt("occupancy_count") < rs.getInt("bed_count")) {
                    return true;
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Phòng đã đủ người !!!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void capnhatsonguoiphongcu() {
        try {
            String sqlsqlc = "select * from room where room_id=N'" + txtmaphongsvdango.getText() + "'";
            Connection conn = connecting.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlsqlc);
            if (rs.next()) {
                String sqlUpdate = "UPDATE room SET occupancy_count=" + (rs.getInt("occupancy_count") - 1) + " where  room_id=N'" + txtmaphongsvdango.getText() + "'";
                stmt.executeUpdate(sqlUpdate);
            }
//            JOptionPane.showMessageDialog(rootPane, "Trả phòng thành công !!!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void capnhatsonguoiphongmoiii() {
        try {
            String sqlsqlc = "select * from room where room_id=N'" + combomap.getSelectedItem() + "'";
            Connection conn = connecting.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlsqlc);
            if (rs.next()) {
                String sqlUpdate = "UPDATE room SET occupancy_count=" + (rs.getInt("occupancy_count") + 1) + " where  room_id=N'" + combomap.getSelectedItem() + "'";
                stmt.executeUpdate(sqlUpdate);
            }
//            JOptionPane.showMessageDialog(rootPane, "Thuê phòng thành công !!!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame2 = new javax.swing.JFrame();
        jPanel11 = new javax.swing.JPanel();
        jPanel42 = new javax.swing.JPanel();
        jPanel43 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtmaphongsvdango = new javax.swing.JLabel();
        jPanel44 = new javax.swing.JPanel();
        jPanel45 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jFrame1 = new javax.swing.JFrame();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        txtmp = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jButton13 = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        jlableanh = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txtmasv = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txthoten = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        combokhoa = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        combonganh = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        datengaysinh = new com.toedter.calendar.JDateChooser();
        jPanel26 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        txtsdt = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtquequan = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        comgioitinh = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        txtgmail = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        datengayvao = new com.toedter.calendar.JDateChooser();
        jFrame3 = new javax.swing.JFrame();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        txtmasv1 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        combomap = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        datengaychuyen = new com.toedter.calendar.JDateChooser();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lydo = new javax.swing.JTextPane();
        jButton9 = new javax.swing.JButton();
        jFrame4 = new javax.swing.JFrame();
        jPanel15 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        txtmasv2 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        datengaychuyen1 = new com.toedter.calendar.JDateChooser();
        jLabel32 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lydo1 = new javax.swing.JTextPane();
        jButton10 = new javax.swing.JButton();
        jFrame5 = new javax.swing.JFrame();
        jPanel31 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        txtmaphong = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        comboloaiphong = new javax.swing.JComboBox<>();
        jButton14 = new javax.swing.JButton();
        jFrame6 = new javax.swing.JFrame();
        jPanel28 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        jPanel35 = new javax.swing.JPanel();
        jPanel36 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        txtsodiencu = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        txtsodienmoi = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        txtsonuocu = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        jPanel38 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        txtsonuocmoi = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        txttongtien = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        datetra = new com.toedter.calendar.JDateChooser();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablediennuo = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jSeparator9 = new javax.swing.JToolBar.Separator();
        jButton1 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jButton2 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jButton3 = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        jButton4 = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        jButton5 = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        jButton6 = new javax.swing.JButton();
        jSeparator8 = new javax.swing.JToolBar.Separator();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtmand = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbtDate = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtvaitro = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        roomNameTextField = new javax.swing.JTextField();
        scrollPane1 = new java.awt.ScrollPane();
        panel1 = new java.awt.Panel();
        jPanel39 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel40 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel41 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        jFrame2.setTitle("Chi tiết sinh viên thuê phòng");
        jFrame2.setLocation(new java.awt.Point(200, 50));
        jFrame2.setMinimumSize(new java.awt.Dimension(1000, 580));

        jPanel11.setLayout(new java.awt.BorderLayout());

        jPanel42.setLayout(new java.awt.CardLayout());

        jPanel43.setBackground(new java.awt.Color(204, 0, 204));
        jPanel43.setLayout(new java.awt.GridLayout());

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Sinh viên trong phòng: ");
        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel11.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel43.add(jLabel11);

        txtmaphongsvdango.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtmaphongsvdango.setForeground(new java.awt.Color(255, 255, 255));
        txtmaphongsvdango.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtmaphongsvdango.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel43.add(txtmaphongsvdango);

        jPanel42.add(jPanel43, "card2");

        jPanel11.add(jPanel42, java.awt.BorderLayout.PAGE_START);

        jPanel44.setLayout(new java.awt.CardLayout(10, 20));

        jPanel45.setLayout(new java.awt.BorderLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel45.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel44.add(jPanel45, "card2");

        jPanel11.add(jPanel44, java.awt.BorderLayout.CENTER);

        jFrame2.getContentPane().add(jPanel11, java.awt.BorderLayout.CENTER);

        jFrame1.setTitle("Thuê phòng");
        jFrame1.setLocation(new java.awt.Point(300, 100));
        jFrame1.setMinimumSize(new java.awt.Dimension(1000, 545));

        jPanel9.setBackground(new java.awt.Color(204, 0, 204));
        jPanel9.setLayout(new java.awt.BorderLayout());

        txtmp.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtmp.setForeground(new java.awt.Color(255, 255, 255));
        txtmp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtmp.setText("0");
        jPanel9.add(txtmp, java.awt.BorderLayout.CENTER);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thêm thông tin sinh viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel7.setLayout(new java.awt.CardLayout());

        jPanel16.setLayout(new java.awt.GridLayout(1, 0));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 447, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 123, Short.MAX_VALUE)
        );

        jPanel16.add(jPanel17);

        jPanel18.setLayout(new java.awt.CardLayout(50, 35));

        jPanel19.setLayout(new java.awt.GridLayout(1, 20, 20, 10));

        jButton11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton11.setText("Huỷ");
        jButton11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel19.add(jButton11);

        jButton12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton12.setText("Xác nhận");
        jButton12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel19.add(jButton12);

        jPanel18.add(jPanel19, "card2");

        jPanel16.add(jPanel18);

        jPanel7.add(jPanel16, "card2");

        jPanel6.add(jPanel7, java.awt.BorderLayout.PAGE_END);

        jPanel10.setLayout(new java.awt.GridLayout(1, 0));

        jPanel12.setLayout(new java.awt.BorderLayout());

        jPanel20.setLayout(new java.awt.CardLayout());

        jPanel21.setLayout(new java.awt.BorderLayout());

        jButton13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton13.setText("Ảnh");
        jButton13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel21.add(jButton13, java.awt.BorderLayout.CENTER);

        jPanel20.add(jPanel21, "card2");

        jPanel12.add(jPanel20, java.awt.BorderLayout.PAGE_END);

        jPanel22.setLayout(new java.awt.BorderLayout());
        jPanel22.add(jlableanh, java.awt.BorderLayout.CENTER);

        jPanel12.add(jPanel22, java.awt.BorderLayout.CENTER);

        jPanel10.add(jPanel12);

        jPanel23.setLayout(new java.awt.CardLayout());

        jPanel24.setLayout(new java.awt.GridLayout(5, 0, 0, 40));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Mã sinh viên:");
        jPanel24.add(jLabel16);
        jPanel24.add(txtmasv);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("Họ tên sinh viên:");
        jPanel24.add(jLabel17);
        jPanel24.add(txthoten);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("Khoa:");
        jPanel24.add(jLabel18);

        combokhoa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Công Nghệ - Kinh Doanh", "Ngôn Ngữ - Du Lịch" }));
        jPanel24.add(combokhoa);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Ngành:");
        jPanel24.add(jLabel19);

        combonganh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Công Nghệ Thông Tin", "Quản Trị - Kinh Doanh", "Công Nghệ Ô Tô", "Ngôn Ngữ Anh", "Ngôn Ngữ Trung Quốc" }));
        jPanel24.add(combonganh);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Ngày sinh:");
        jPanel24.add(jLabel20);
        jPanel24.add(datengaysinh);

        jPanel23.add(jPanel24, "card2");

        jPanel10.add(jPanel23);

        jPanel26.setLayout(new java.awt.CardLayout());

        jPanel27.setLayout(new java.awt.GridLayout(5, 0, 0, 40));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("SĐT:");
        jPanel27.add(jLabel21);
        jPanel27.add(txtsdt);

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setText("Địa chỉ:");
        jPanel27.add(jLabel22);
        jPanel27.add(txtquequan);

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setText("Gioi tính:");
        jPanel27.add(jLabel23);

        comgioitinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        jPanel27.add(comgioitinh);

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel24.setText("Gmail:");
        jPanel27.add(jLabel24);
        jPanel27.add(txtgmail);

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setText("Ngày vào:");
        jPanel27.add(jLabel25);
        jPanel27.add(datengayvao);

        jPanel26.add(jPanel27, "card2");

        jPanel10.add(jPanel26);

        jPanel6.add(jPanel10, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 916, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 904, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 508, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jFrame1.getContentPane().add(jPanel8, java.awt.BorderLayout.CENTER);

        jFrame3.setTitle("Chuyển phòng");
        jFrame3.setLocation(new java.awt.Point(550, 300));
        jFrame3.setMinimumSize(new java.awt.Dimension(400, 300));

        jPanel14.setLayout(new java.awt.GridLayout(3, 0, 0, 10));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel26.setText("Mã sinh viên:");
        jPanel14.add(jLabel26);
        jPanel14.add(txtmasv1);

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel27.setText("Mã phòng mới:");
        jPanel14.add(jLabel27);

        jPanel14.add(combomap);

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel28.setText("Ngày chuyển:");
        jPanel14.add(jLabel28);
        jPanel14.add(datengaychuyen);

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel29.setText("Lý do:");

        jScrollPane2.setViewportView(lydo);

        jButton9.setBackground(new java.awt.Color(51, 255, 51));
        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Chuyển phòng");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton9)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel13Layout.createSequentialGroup()
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(33, 33, 33)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jButton9)
                .addGap(32, 32, 32))
        );

        jFrame3.getContentPane().add(jPanel13, java.awt.BorderLayout.CENTER);

        jFrame4.setTitle("Trả phòng");
        jFrame4.setLocation(new java.awt.Point(550, 300));
        jFrame4.setMinimumSize(new java.awt.Dimension(400, 300));

        jPanel25.setLayout(new java.awt.GridLayout(3, 0, 0, 10));

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel30.setText("Mã sinh viên:");
        jPanel25.add(jLabel30);
        jPanel25.add(txtmasv2);

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel31.setText("Ngày trả:");
        jPanel25.add(jLabel31);
        jPanel25.add(datengaychuyen1);

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel32.setText("Lý do:");

        jScrollPane3.setViewportView(lydo1);

        jButton10.setBackground(new java.awt.Color(51, 255, 51));
        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Trả Phòng");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton10)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel15Layout.createSequentialGroup()
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(33, 33, 33)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jButton10)
                .addGap(32, 32, 32))
        );

        jFrame4.getContentPane().add(jPanel15, java.awt.BorderLayout.CENTER);

        jFrame5.setTitle("Thêm phòng");
        jFrame5.setLocation(new java.awt.Point(550, 300));
        jFrame5.setMinimumSize(new java.awt.Dimension(400, 170));

        jPanel32.setLayout(new java.awt.GridLayout(2, 0, 0, 10));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel33.setText("Mã phòng:");
        jPanel32.add(jLabel33);
        jPanel32.add(txtmaphong);

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel34.setText("Loại phòng:");
        jPanel32.add(jLabel34);

        comboloaiphong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "VIP", "THƯỜNG" }));
        jPanel32.add(comboloaiphong);

        jButton14.setBackground(new java.awt.Color(51, 255, 51));
        jButton14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton14.setForeground(new java.awt.Color(255, 255, 255));
        jButton14.setText("Thêm phòng");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton14)
                    .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton14)
                .addGap(32, 32, 32))
        );

        jFrame5.getContentPane().add(jPanel31, java.awt.BorderLayout.CENTER);

        jFrame6.setLocation(new java.awt.Point(400, 95));
        jFrame6.setMinimumSize(new java.awt.Dimension(716, 665));

        jPanel28.setLayout(new java.awt.BorderLayout());

        jPanel29.setLayout(new java.awt.BorderLayout());

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(102, 102, 0));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("Tính tiền điện nước");
        jPanel29.add(jLabel35, java.awt.BorderLayout.CENTER);

        jPanel28.add(jPanel29, java.awt.BorderLayout.PAGE_START);

        jPanel30.setLayout(new java.awt.GridLayout(2, 0));

        jPanel33.setLayout(new java.awt.BorderLayout());

        jPanel34.setLayout(new java.awt.GridLayout(1, 0));

        jPanel35.setLayout(new java.awt.CardLayout(10, 20));

        jPanel36.setLayout(new java.awt.GridLayout(5, 0, 0, 15));

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel36.setText("ID:");
        jPanel36.add(jLabel36);
        jPanel36.add(txtid);

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel37.setText("Chỉ số điện cũ:");
        jPanel36.add(jLabel37);
        jPanel36.add(txtsodiencu);

        jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel38.setText("Chỉ số điện mới:");
        jPanel36.add(jLabel38);
        jPanel36.add(txtsodienmoi);

        jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel39.setText("Chỉ số nước cũ:");
        jPanel36.add(jLabel39);
        jPanel36.add(txtsonuocu);
        jPanel36.add(jLabel40);

        jPanel35.add(jPanel36, "card2");

        jPanel34.add(jPanel35);

        jPanel37.setLayout(new java.awt.CardLayout(10, 20));

        jPanel38.setLayout(new java.awt.GridLayout(5, 0, 0, 15));

        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel41.setText("Chỉ số nước mới:");
        jPanel38.add(jLabel41);

        txtsonuocmoi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsonuocmoiKeyReleased(evt);
            }
        });
        jPanel38.add(txtsonuocmoi);

        jLabel42.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel42.setText("Tổng tiền:");
        jPanel38.add(jLabel42);
        jPanel38.add(txttongtien);

        jLabel43.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel43.setText("Thời gian trả:");
        jPanel38.add(jLabel43);
        jPanel38.add(datetra);
        jPanel38.add(jLabel44);
        jPanel38.add(jLabel45);

        jButton15.setBackground(new java.awt.Color(0, 255, 0));
        jButton15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton15.setForeground(new java.awt.Color(255, 255, 255));
        jButton15.setText("Thêm");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jPanel38.add(jButton15);

        jPanel37.add(jPanel38, "card2");

        jPanel34.add(jPanel37);

        jPanel33.add(jPanel34, java.awt.BorderLayout.CENTER);

        jPanel30.add(jPanel33);

        tablediennuo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tablediennuo);

        jPanel30.add(jScrollPane4);

        jPanel28.add(jPanel30, java.awt.BorderLayout.CENTER);

        jFrame6.getContentPane().add(jPanel28, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jToolBar1.setRollover(true);
        jToolBar1.add(jSeparator9);

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pxu/com/images/sinhivnektx.png"))); // NOI18N
        jButton1.setText("Sinh Viên");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);
        jToolBar1.add(jSeparator3);

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pxu/com/images/kyluiat.png"))); // NOI18N
        jButton2.setText("Kỷ Luật");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);
        jToolBar1.add(jSeparator4);

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pxu/com/images/nhaxe.png"))); // NOI18N
        jButton3.setText("Nhà Xe");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);
        jToolBar1.add(jSeparator5);

        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pxu/com/images/taikhoan.png"))); // NOI18N
        jButton4.setText("User");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton4);
        jToolBar1.add(jSeparator6);

        jButton5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pxu/com/images/hoadon.png"))); // NOI18N
        jButton5.setText("Hoá đơn");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton5);
        jToolBar1.add(jSeparator7);

        jButton6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pxu/com/images/thongke.png"))); // NOI18N
        jButton6.setText("Thống kê");
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton6);
        jToolBar1.add(jSeparator8);

        jPanel3.setLayout(new java.awt.GridLayout(2, 0));
        jPanel3.add(jLabel1);
        jPanel3.add(jLabel13);
        jPanel3.add(jLabel9);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Tên quản lý:");
        jPanel3.add(jLabel5);

        txtmand.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtmand.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel3.add(txtmand);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Ngày:");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel3.add(jLabel7);

        lbtDate.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbtDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel3.add(lbtDate);
        jPanel3.add(jLabel2);
        jPanel3.add(jLabel12);
        jPanel3.add(jLabel14);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Vai trò:");
        jPanel3.add(jLabel4);

        txtvaitro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtvaitro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel3.add(txtvaitro);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Giờ:");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel3.add(jLabel8);

        lblTime.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel3.add(lblTime);

        jToolBar1.add(jPanel3);

        jPanel2.add(jToolBar1, java.awt.BorderLayout.CENTER);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Tìm kiềm:");
        jLabel15.setPreferredSize(new java.awt.Dimension(61, 30));
        jPanel4.add(jLabel15);

        roomNameTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        roomNameTextField.setPreferredSize(new java.awt.Dimension(200, 30));
        roomNameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                roomNameTextFieldKeyReleased(evt);
            }
        });
        jPanel4.add(roomNameTextField);
        roomNameTextField.getAccessibleContext().setAccessibleName("");
        roomNameTextField.getAccessibleContext().setAccessibleDescription("");

        panel1.setLayout(new java.awt.GridLayout(4, 0));
        scrollPane1.add(panel1);

        jPanel39.setBackground(new java.awt.Color(34, 139, 3));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Có người");

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel3)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel39Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(41, 41, 41))
        );

        jPanel40.setBackground(new java.awt.Color(255, 69, 0));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Còn trống");

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel6)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel40Layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(40, 40, 40))
        );

        jPanel41.setBackground(new java.awt.Color(128, 128, 128));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Đang chọn");

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel10)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel41Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(41, 41, 41))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel39, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel41, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jMenu1.setText("File");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setText("Thống kê");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);
        jMenu1.add(jSeparator1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem2.setText("Đổi mật khẩu");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);
        jMenu1.add(jSeparator2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem3.setText("Đăng xuất");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        jFrame1.dispose();
    }//GEN-LAST:event_jButton11ActionPerformed
    private boolean checkmasv() {
        try {
            Connection conn = connecting.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "select * from STUDENT";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if (rs.getString("student_id").toString().trim().equals(txtmasv.getText())) {
                    jFrame1.setVisible(true);
                    JOptionPane.showMessageDialog(rootPane, "Mã sinh viên đã tồn tại !!!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean chek() {
        try {
            String sqlsqlc = "select * from room where room_id=N'" + txtmp.getText() + "'";
            Connection conn = connecting.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlsqlc);
            while (rs.next()) {
                if (rs.getInt("occupancy_count") < rs.getInt("bed_count")) {
                    return true;
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Phòng đã đủ người !!!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        if (checkmasv()) {
            if (chek()) {
                try {
                    StringBuilder sb = new StringBuilder();
                    if (sb.length() > 0) {
                        JOptionPane.showMessageDialog(rootPane, sb);
                        return;
                    }
                    /// thêm sinh viên

                    StudentModel s = new StudentModel();
                    s.setStudent_id(txtmasv.getText());
                    s.setStudent_name(txthoten.getText());
                    s.setFaculty(combokhoa.getSelectedItem().toString());
                    s.setMajor(combonganh.getSelectedItem().toString());
                    SimpleDateFormat date = new SimpleDateFormat("yyy-MM-dd");
                    String ngaysinh = date.format(datengaysinh.getDate());
                    s.setBirth_date(Date.valueOf(ngaysinh));
                    s.setGmail(txtgmail.getText());
                    s.setPhone_number(txtsdt.getText());
                    s.setGender(comgioitinh.getSelectedItem().toString());
                    s.setHometown(txtquequan.getText());
                    s.setRoom_id(txtmp.getText());
                    s.setViolation_count(0);
                    String ngayvao = date.format(datengayvao.getDate());
                    s.setCheck_in_date(Date.valueOf(ngayvao));
                    s.setStatus("ĐANG THUÊ");
                    s.setStudent_image(resonalImage);
                    StudentDao dao = new StudentDao();
                    dao.insert(s);
                    /// thêm vào hóa đơn thuê phòng
                    String sql = "select * from users where full_name=N'" + txtmand.getText() + "'";
                    Connection conn = connecting.getConnection();
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        RoomrentalModel t = new RoomrentalModel();
                        t.setStudent_id(txtmasv.getText());
                        t.setUser_id(rs.getString("user_id"));
                        t.setRoom_id(txtmp.getText());
                        String ngaythue = date.format(datengayvao.getDate());
                        t.setRental_date(Date.valueOf(ngaythue));
                        t.setStatus("ĐANG THUÊ");
                        RoomrentalDao daoo = new RoomrentalDao();
                        daoo.insert(t);
                        ///Cập nhật số người ở phòng mới
                        capnhatsonguoiphongmoi();
                        JOptionPane.showMessageDialog(rootPane, "Thuê Phòng thành công !!!");
                    }
                    reloadRoomStatus();
                } catch (SQLException ex) {
                    Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        try {
            ArrayList<String> list = new ArrayList<>();
            list.add(txtgmail.getText());
            for (int i = 0; i < list.size(); i++) {

                Properties p = new Properties();
                p.put("mail.smtp.auth", "true");
                p.put("mail.smtp.starttls.enable", "true");
                p.put("mail.smtp.host", "smtp.gmail.com");
                p.put("mail.smtp.port", "587");

                Session session = Session.getInstance(p, new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("vvc132003@gmail.com", "mwvfwkbyknepohte");
                    }
                });

                String from = "vvc132003@gmail.com"; // Replace with the sender's email address
                String tos = list.get(i).toString();
                String subj = "Gửi đến bạn";
                SimpleDateFormat date = new SimpleDateFormat("yyy-MM-dd");
                String ngayvao = date.format(datengayvao.getDate());
                String body = "Mã SV: " + txtmasv.getText()
                        + "\nTên: " + txthoten.getText()
                        + "\nNgày vào: " + ngayvao
                        + "\nKhoa: " + combokhoa.getSelectedItem().toString()
                        + "\nNgành: " + combonganh.getSelectedItem().toString()
                        + "\nĐịa chỉ: " + txtquequan.getText()
                        + "\nGiới tính: " + comgioitinh.getSelectedItem().toString()
                        + "\nSĐT: " + txtsdt.getText()
                        + "\nSố phòng bạn thuê: " + txtmp.getText();
                Message msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress(from));
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(tos));
                msg.setSubject(subj);
                msg.setText(body);

                try (Transport transport = session.getTransport("smtp")) {
                    transport.connect("smtp.gmail.com", "vvc132003@gmail.com", "qyqgwwjbbajzrrex");
                    transport.sendMessage(msg, msg.getAllRecipients());
                }
                System.out.println("Email sent successfully to: " + tos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        txtmasv.setText("");
        txthoten.setText("");
        txtgmail.setText("");
        txtsdt.setText("");
        txtquequan.setText("");
        txtmp.setText("");
        jFrame1.dispose();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    return f.getName().toLowerCase().endsWith(".jpg");
                }
            }

            @Override
            public String getDescription() {
                return "Image File (*.jpg)";
            }
        });
        if (chooser.showOpenDialog(rootPane) == JFileChooser.CANCEL_OPTION) {
            return;
        }
        File file = chooser.getSelectedFile();
        try {
            ImageIcon icon = new ImageIcon(file.getPath());
            Image img = imageheiper.resize(icon.getImage(), 313, 271);
            ImageIcon resizeIcon = new ImageIcon(img);
            jlableanh.setIcon(resizeIcon);
            resonalImage = imageheiper.toByteArray(img, "jpg");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(rootPane, "looix");
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        if (checksonguoiophongmoi()) {
            if (chekngayvao()) {
                try {
                    /// cập nhật số phòng của khách hàng đang ở hiện tại

                    StudentModel s = new StudentModel();
                    s.setStudent_id(txtmasv1.getText());
                    s.setRoom_id(combomap.getSelectedItem().toString());
                    StudentDao daoo = new StudentDao();
                    daoo.update(s);
                    //// cập nhật số người ở phòng cũ
                    capnhatsonguoiphongcu();
                    //// cập nhật số người ở phòng mới
                    capnhatsonguoiphongmoiii();
                    /// thêm vào hóa đơn chuyển phòng
                    String sql = "select * from users where full_name=N'" + txtmand.getText() + "'";
                    Connection conn = connecting.getConnection();
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        RoomtransferModel c = new RoomtransferModel();
                        c.setStudent_id(txtmasv1.getText());
                        c.setUser_id(rs.getString("user_id"));
                        c.setPrevious_room_id(txtmp.getText());
                        c.setRoom_id(combomap.getSelectedItem().toString());
                        SimpleDateFormat date = new SimpleDateFormat("yyy-MM-dd");
                        String ngaychuyen = date.format(datengaychuyen.getDate());
                        c.setTransfer_date(Date.valueOf(ngaychuyen));
                        c.setReason(lydo.getText());
                        RoomtransferDao dao = new RoomtransferDao();
                        dao.insert(c);
                        JOptionPane.showMessageDialog(rootPane, "Chuyển phòng thành công !!!");
                    }
                    reloadRoomStatus();
                } catch (SQLException ex) {
                    Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else {
        }
        jFrame3.dispose();
        jFrame2.dispose();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        try {
            /// cập nhật số phòng của khách hàng đang ở hiện tại
            StudentModel s = new StudentModel();
            s.setStudent_id(txtmasv1.getText());
            s.setStatus("ĐÃ TRẢ");
            StudentDao daoo = new StudentDao();
            daoo.updatetrangthai(s);
            //// cập nhật số người ở phòng cũ
            capnhatsonguoiphongcu();
            /// thêm vào hóa đơn trả phòng
            String sql = "select * from users where full_name=N'" + txtmand.getText() + "'";
            Connection conn = connecting.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                CheckoutModel c = new CheckoutModel();
                c.setStudent_id(txtmasv1.getText());
                c.setUser_id(rs.getString("user_id"));
                c.setRoom_id(txtmp.getText());
                SimpleDateFormat date = new SimpleDateFormat("yyy-MM-dd");
                String ngaytra = date.format(datengaychuyen1.getDate());
                c.setCheckout_date(Date.valueOf(ngaytra));
                c.setReason(lydo1.getText());
                CheckoutDao dao = new CheckoutDao();
                dao.insert(c);
                JOptionPane.showMessageDialog(rootPane, "Trả phòng thành công !!!");
            }
            reloadRoomStatus();
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
        jFrame4.dispose();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        try {
            RoomModel p = new RoomModel();
            p.setRoom_id(txtmaphong.getText());
            p.setRoom_type(comboloaiphong.getSelectedItem().toString());
            if (p.getRoom_type().equals("VIP")) {
                p.setBed_count(8);
            } else if (p.getRoom_type().equals("THƯỜNG")) {
                p.setBed_count(4);
            }
            if (p.getRoom_type().equals("VIP")) {
                p.setRoom_price(p.getBed_count() * 600);
            } else if (p.getRoom_type().equals("THƯỜNG")) {
                p.setRoom_price(p.getBed_count() * 300);
            }
            p.setOccupancy_count(0);
            RoomDao dao = new RoomDao();
            dao.insert(p);
            reloadRoomStatus();
            JOptionPane.showMessageDialog(rootPane, "Thêm phòng thành công !!!");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        txtmaphong.setText("");
        jFrame5.dispose();
    }//GEN-LAST:event_jButton14ActionPerformed
    private void tinhtiendien() {
        int chisodiencu = Integer.parseInt(txtsodiencu.getText());
        int chisodienmoi = Integer.parseInt(txtsodienmoi.getText());
        int chisonuoccu = Integer.parseInt(txtsonuocu.getText());
        int chisonuocmoi = Integer.parseInt(txtsonuocmoi.getText());
        int tiendien = (chisodienmoi - chisodiencu) * 3;
        int tiennuoc = (chisonuocmoi - chisonuoccu) * 10;
        int tong = tiendien + tiennuoc;
        txttongtien.setText(String.valueOf(tong));
    }
    private void txtsonuocmoiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsonuocmoiKeyReleased
        tinhtiendien();
    }//GEN-LAST:event_txtsonuocmoiKeyReleased

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed

        try {
            StringBuilder sb = new StringBuilder();
            if (sb.length() > 0) {
                JOptionPane.showMessageDialog(rootPane, sb);
                return;
            }
            ElectricitywaterModel d = new ElectricitywaterModel();
            d.setUser_id(showuser.nguoiDangNhap.getUser_id());
            d.setRoom_id(txtmp.getText());
            d.setOld_electricity_reading(Integer.parseInt(txtsodiencu.getText()));
            d.setNew_electricity_reading(Integer.parseInt(txtsodienmoi.getText()));
            d.setOld_water_reading(Integer.parseInt(txtsonuocu.getText()));
            d.setNew_water_reading(Integer.parseInt(txtsonuocmoi.getText()));
            d.setElectricity_price(Float.parseFloat(txttongtien.getText()));
            d.setStatus("Đã thanh toán");
            SimpleDateFormat date = new SimpleDateFormat("yyy-MM-dd");
            String tgtra = date.format(datetra.getDate());
            d.setPayment_time(Date.valueOf(tgtra));
            ElectricitywaterDao dao = new ElectricitywaterDao();
            dao.insert(d);
            laydiennuoc();
            txtsodiencu.setText("");
            txtsonuocu.setText("");
            txtsodienmoi.setText("");
            txtsonuocmoi.setText("");
            txttongtien.setText("");
            JOptionPane.showMessageDialog(rootPane, "Đã thanh toán !!!");
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton15ActionPerformed

    private void roomNameTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_roomNameTextFieldKeyReleased
        String roomNameQuery = roomNameTextField.getText(); // Lấy giá trị từ trường văn bản nhập tên phòng
        searchRooms(roomNameQuery); // Gọi hàm searchRooms với tên phòng và loại phòng tương ứng
    }//GEN-LAST:event_roomNameTextFieldKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            student h = new student();
            h.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            violation h = new violation();
            h.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            crarental h = new crarental();
            h.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            user h = new user();
            h.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            roomrto h = new roomrto();
            h.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        thongke h = new thongke();
        h.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        doimatkhau h = new doimatkhau();
        h.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed

    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        try {
            int row = jTable1.getSelectedRow();
            if (row >= 0) {
                String maSV = (String) jTable1.getValueAt(row, 0);
                StudentDao dao = new StudentDao();
                StudentModel p = dao.FindManv(maSV);
                if (dao != null) {
                    txtmasv1.setText(p.getStudent_id());
                    txtmasv2.setText(p.getStudent_id());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "looix");
        }
    }//GEN-LAST:event_jTable1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new home().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> combokhoa;
    private javax.swing.JComboBox<String> comboloaiphong;
    private javax.swing.JComboBox<String> combomap;
    private javax.swing.JComboBox<String> combonganh;
    private javax.swing.JComboBox<String> comgioitinh;
    private com.toedter.calendar.JDateChooser datengaychuyen;
    private com.toedter.calendar.JDateChooser datengaychuyen1;
    private com.toedter.calendar.JDateChooser datengaysinh;
    private com.toedter.calendar.JDateChooser datengayvao;
    private com.toedter.calendar.JDateChooser datetra;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton9;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JFrame jFrame3;
    private javax.swing.JFrame jFrame4;
    private javax.swing.JFrame jFrame5;
    private javax.swing.JFrame jFrame6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JToolBar.Separator jSeparator8;
    private javax.swing.JToolBar.Separator jSeparator9;
    private javax.swing.JTable jTable1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel jlableanh;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lbtDate;
    private javax.swing.JTextPane lydo;
    private javax.swing.JTextPane lydo1;
    private java.awt.Panel panel1;
    private javax.swing.JTextField roomNameTextField;
    private java.awt.ScrollPane scrollPane1;
    private javax.swing.JTable tablediennuo;
    private javax.swing.JTextField txtgmail;
    private javax.swing.JTextField txthoten;
    private javax.swing.JTextField txtid;
    private javax.swing.JLabel txtmand;
    private javax.swing.JTextField txtmaphong;
    private javax.swing.JLabel txtmaphongsvdango;
    private javax.swing.JTextField txtmasv;
    private javax.swing.JTextField txtmasv1;
    private javax.swing.JTextField txtmasv2;
    private javax.swing.JLabel txtmp;
    private javax.swing.JTextField txtquequan;
    private javax.swing.JTextField txtsdt;
    private javax.swing.JTextField txtsodiencu;
    private javax.swing.JTextField txtsodienmoi;
    private javax.swing.JTextField txtsonuocmoi;
    private javax.swing.JTextField txtsonuocu;
    private javax.swing.JTextField txttongtien;
    private javax.swing.JLabel txtvaitro;
    // End of variables declaration//GEN-END:variables
 private void laysinhvien() throws SQLException, ClassNotFoundException {
        Connection conn = connecting.getConnection();
        try {
            String sqll = "select * from student where status=N'ĐANG THUÊ' and room_id =N'" + txtmp.getText() + "'";
            String[] aray = {"Mã sinh viên", "Họ tên sinh viên", "Mã phòng", "Ngày vào", "Số lần vi phạm", "Trạng thái"};
            DefaultTableModel model = new DefaultTableModel(aray, 0);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqll);
            while (rs.next()) {
                Vector vector = new Vector();
                vector.add(rs.getString("student_id"));
                vector.add(rs.getString("student_name"));
                vector.add(rs.getString("room_id"));
                vector.add(rs.getDate("check_in_date"));
                vector.add(rs.getInt("violation_count"));
                vector.add(rs.getString("status"));
                model.addRow(vector);
            }
            jTable1.setModel(model);
        } catch (SQLException e) {
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            // Get the clicked JPanel
            JPanel clickedPanel = (JPanel) e.getSource();
            // Get the first JLabel within the clicked panel
            JLabel roomNumberLabel = (JLabel) clickedPanel.getComponent(0);
            // Extract the room number from the label
            String roomNumber = roomNumberLabel.getText();
            // Use the room number as needed
            // For example, you can display it in a text field
            txtmp.setText(roomNumber);
            // Call the method to fetch additional information (laychisocu method)
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
