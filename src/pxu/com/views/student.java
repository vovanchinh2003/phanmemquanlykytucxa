/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pxu.com.views;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pxu.com.connect.Image_nckh;
import pxu.com.dao.CrarentalDao;
import pxu.com.dao.StudentDao;
import pxu.com.dialogchek.showuser;
import pxu.com.model.CrarentalModel;
import pxu.com.model.StudentModel;

/**
 *
 * @author chinh
 */
public class student extends javax.swing.JFrame {

    private ArrayList<StudentModel> studentModels;
    private DefaultTableModel tblModel;
    private TableRowSorter sorter;

    /**
     * Creates new form Quanlynhaxe
     */
    public student() throws SQLException, ClassNotFoundException {
        initComponents();
        initTable();
        LoadnhaxeTable();
        timkiem();
        chuotphaijtable();
        this.setSize(1400, 750);
        this.setLocationRelativeTo(null);
        jFrame2.setSize(1000, 350);
    }

    private void chuotphaijtable() {
        tblsinhvien.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    JPopupMenu popup = new JPopupMenu();
                    JMenuItem capnhat = new JMenuItem("Cập nhật sinh viên");
                    capnhat.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            jFrame2.setVisible(true);
                        }
                    });
                    capnhat.setForeground(Color.ORANGE);
                    popup.add(capnhat);
                    JMenuItem xoa = new JMenuItem("Xoá sinh viên");
                    xoa.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            try {
                                if (JOptionPane.showConfirmDialog(rootPane, "Mời bạn chọn !") == JOptionPane.NO_OPTION) {
                                    return;
                                }
                                CrarentalModel x = new CrarentalModel();
//                                x.setCar_id(Integer.parseInt(txtmaxe1.getText()));
                                CrarentalDao dao = new CrarentalDao();
                                dao.delete(x);
                                LoadnhaxeTable();
                                JOptionPane.showMessageDialog(rootPane, "Xoá thành công !!!");
                            } catch (SQLException ex) {
                                Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    xoa.setForeground(Color.RED);
                    popup.add(xoa);
                    popup.show(tblsinhvien, e.getX(), e.getY());
                }
            }
        });
    }

    private void initTable() {
        tblModel = new DefaultTableModel();
        tblModel.setColumnIdentifiers(new String[]{"Mã sinh viên", "Họ tên", "Khoa", "Ngành", "Ngày sinh", "Gmail", "SĐT", "Giới tính", "Quê quán", "Số phòng", "Số lần vi phạm", "Ngày vào", "Trạng thái"});
        tblsinhvien.setModel(tblModel);
    }

    private void LoadnhaxeTable() throws SQLException, ClassNotFoundException {
        studentModels = StudentDao.getAll();
        sorter = new TableRowSorter(tblModel);
        tblsinhvien.setRowSorter(sorter);
        tblModel.setRowCount(0);
        for (StudentModel m : studentModels) {
            Object[] object = new Object[]{m.getStudent_id(), m.getStudent_name(), m.getFaculty(), m.getMajor(), m.getBirth_date(), m.getGmail(), m.getPhone_number(), m.getGender(), m.getHometown(), m.getRoom_id(), m.getViolation_count(), m.getCheck_in_date(), m.getStatus()};
            tblModel.addRow(object);
        }
        tblModel.fireTableDataChanged();
    }

    private void timkiem() {
        txtTimHoTen.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(txtTimHoTen.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(txtTimHoTen.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(txtTimHoTen.getText());
            }
        });
    }

    //Ham tim kiem theo chuoi str
    private void search(String str) {
        if (str.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter(str));
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
        jPanel15 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtMaSV = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtTenSV = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cbTrangThai = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JFormattedTextField();
        cbDiaChi = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        txtCCCD = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        birthdate = new com.toedter.calendar.JDateChooser();
        checkoutdate = new com.toedter.calendar.JDateChooser();
        cbKhoa = new javax.swing.JComboBox<>();
        cbNganh = new javax.swing.JComboBox<>();
        cbGT = new javax.swing.JComboBox<>();
        cbPhong = new javax.swing.JComboBox<>();
        spSLVP = new javax.swing.JSpinner();
        btnanh = new javax.swing.JButton();
        lblanh = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        txtTimHoTen = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblsinhvien = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();

        jFrame2.setTitle("Cập nhật thông tin sinh viên");
        jFrame2.setLocation(new java.awt.Point(380, 200));
        jFrame2.setMinimumSize(new java.awt.Dimension(360, 380));

        jPanel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel15.setMinimumSize(new java.awt.Dimension(980, 222));
        jPanel15.setName(""); // NOI18N
        jPanel15.setPreferredSize(new java.awt.Dimension(980, 222));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Giới Tính  :");
        jPanel15.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 80, -1));

        txtMaSV.setColumns(40);
        txtMaSV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaSVKeyReleased(evt);
            }
        });
        jPanel15.add(txtMaSV, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 180, -1));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Quê Quán :");
        jPanel15.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 50, 80, -1));

        txtTenSV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTenSVKeyReleased(evt);
            }
        });
        jPanel15.add(txtTenSV, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 180, -1));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Ngày Vào  :");
        jLabel12.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel15.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, 120, -1));

        cbTrangThai.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        cbTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ĐÃ TRẢ", "CHƯA TRẢ" }));
        cbTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTrangThaiActionPerformed(evt);
            }
        });
        jPanel15.add(cbTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 170, 180, -1));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Số Lần Vi Phạm :");
        jPanel15.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 140, 110, -1));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Phòng ở số  :");
        jPanel15.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 110, -1, -1));

        try {
            txtSDT.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-###-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });
        txtSDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSDTKeyReleased(evt);
            }
        });
        jPanel15.add(txtSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, 180, -1));

        cbDiaChi.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        cbDiaChi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "An Giang", "Bà Rịa – Vũng Tàu", "Bắc Giang", "Bắc Kạn", "Bạc Liêu", "Bắc Ninh", "Bến Tre", "Bình Định", "Bình Dương", "Bình Phước", "Bình Thuận", "Cà Mau", "Cần Thơ", "Cao Bằng", "Đà Nẵng", "Đắk Lắk", "Đắk Nông", "Điện Biên", "Đồng Nai", "Đồng Tháp", "Gia Lai", "Hà Giang", "Hà Nam", "Hà Nội", "Hà Tĩnh", "Hải Dương", "Hải Phòng", "Hậu Giang", "Hòa Bình", "Hưng Yên", "Khánh Hòa", "Kiên Giang", "Kon Tum", "Lai Châu", "Lâm Đồng", "Lạng Sơn", "Lào Cai", "Long An", "Nam Định", "Nghệ An", "Ninh Bình", "Ninh Thuận", "Phú Thọ", "Phú Yên", "Quảng Bình", "Quảng Nam", "Quảng Ngãi", "Quảng Ninh", "Quảng Trị", "Sóc Trăng", "Sơn La", "Tây Ninh", "Thái Bình", "Thái Nguyên", "Thanh Hóa", "Thừa Thiên Huế", "Tiền Giang", "Thành phố Hồ Chí Minh", "Trà Vinh", "Tuyên Quang", "Vĩnh Long", "Vĩnh Phúc", "Yên Bái" }));
        cbDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDiaChiActionPerformed(evt);
            }
        });
        jPanel15.add(cbDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 50, 180, -1));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel16.setText("Trạng Thái :");
        jPanel15.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(405, 170, -1, -1));

        txtCCCD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCCCDKeyReleased(evt);
            }
        });
        jPanel15.add(txtCCCD, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 180, -1));

        jLabel17.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Mã Sinh Viên :");
        jPanel15.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Tên Sinh Viên :");
        jPanel15.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 50, 90, -1));

        jLabel20.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Khoa :");
        jLabel20.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel20.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel15.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 70, -1));

        jLabel21.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("Ngành :");
        jPanel15.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, -1));

        jLabel22.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Ngày Sinh :");
        jPanel15.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        jLabel23.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel23.setText("Số CCCD :");
        jPanel15.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        jLabel24.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel24.setText("Số Điện Thoại :");
        jPanel15.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));
        jPanel15.add(birthdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 180, -1));
        jPanel15.add(checkoutdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 80, 180, -1));

        cbKhoa.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        cbKhoa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ngôn Ngữ", "Công Nghệ - Kinh Doanh", "Du Lịch" }));
        cbKhoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKhoaActionPerformed(evt);
            }
        });
        jPanel15.add(cbKhoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 180, -1));

        cbNganh.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        cbNganh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CNTT", "CNKTOTO", "NNA", "NNT", "Du lich - Lữ Hành", "Kế Toán", "TKNT", "TTDPT", " " }));
        cbNganh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNganhActionPerformed(evt);
            }
        });
        jPanel15.add(cbNganh, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 180, -1));

        cbGT.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        cbGT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ", " " }));
        cbGT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbGTActionPerformed(evt);
            }
        });
        jPanel15.add(cbGT, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, 180, -1));

        cbPhong.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        cbPhong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "101", "102", "103", "104", "201", "202", "203", "204", "301", "302", "303", "304", "401", "402", "403", "404", "501", "502", "503", "504" }));
        cbPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPhongActionPerformed(evt);
            }
        });
        jPanel15.add(cbPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 110, 180, -1));
        jPanel15.add(spSLVP, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 140, 180, -1));

        btnanh.setText("Chọn ảnh");
        btnanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnanhActionPerformed(evt);
            }
        });
        jPanel15.add(btnanh, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 190, -1, -1));

        lblanh.setText("jLabel17");
        jPanel15.add(lblanh, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 20, 160, 150));

        jButton4.setBackground(new java.awt.Color(255, 153, 0));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Cập nhật");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel15.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(541, 280, 100, -1));

        jButton5.setBackground(new java.awt.Color(255, 0, 0));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Huỷ");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel15.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 280, 100, -1));

        jFrame2.getContentPane().add(jPanel15, java.awt.BorderLayout.CENTER);

        setTitle("Quản lý nhà xe");
        setMinimumSize(new java.awt.Dimension(710, 422));

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.CardLayout(100, 15));

        jPanel7.setLayout(new java.awt.GridLayout(1, 0, 10, 10));
        jPanel7.add(jLabel9);

        jButton6.setBackground(new java.awt.Color(0, 0, 204));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Xuất excel");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton6);
        jPanel7.add(jLabel2);

        jButton3.setBackground(new java.awt.Color(255, 153, 51));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Tìm kiếm:");
        jPanel7.add(jButton3);
        jPanel7.add(txtTimHoTen);

        jPanel2.add(jPanel7, "card2");

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel9.setLayout(new java.awt.CardLayout(10, 5));

        tblsinhvien.setModel(new javax.swing.table.DefaultTableModel(
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
        tblsinhvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblsinhvienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblsinhvien);

        jPanel9.add(jScrollPane1, "card2");

        jPanel8.add(jPanel9, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel10.setLayout(new java.awt.CardLayout(50, 10));

        jPanel11.setLayout(new java.awt.BorderLayout());

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 102, 0));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Quản lý sinh viên");
        jPanel11.add(jLabel19, java.awt.BorderLayout.CENTER);

        jPanel10.add(jPanel11, "card2");

        jPanel1.add(jPanel10, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private byte[] resonalImage;
    private void tblsinhvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblsinhvienMouseClicked
        try {
            int row = tblsinhvien.getSelectedRow();
            if (row >= 0) {
                String maSV = (String) tblsinhvien.getValueAt(row, 0);
                StudentDao dao = new StudentDao();
                StudentModel b = dao.FindMaSv(maSV);
                if (dao != null) {
                    txtMaSV.setText(b.getStudent_id());
                    txtTenSV.setText(b.getStudent_name());
                    cbKhoa.setSelectedItem(b.getFaculty());
                    cbNganh.setSelectedItem(b.getMajor());
                    birthdate.setDate(b.getBirth_date());
                    txtCCCD.setText(b.getGmail());
                    txtSDT.setText(b.getPhone_number());
                    cbGT.setSelectedItem(b.getGender());
                    cbDiaChi.setSelectedItem(b.getHometown());
                    checkoutdate.setDate(b.getCheck_in_date());
                    cbPhong.setSelectedItem(b.getRoom_id());
                    spSLVP.setValue(b.getViolation_count());
                    cbTrangThai.setSelectedItem(b.getStatus());
                    if (b.getStudent_image() != null) {
                        Image img = Image_nckh.createImageFromByteArray(b.getStudent_image(), "jpg");
                        lblanh.setIcon(new ImageIcon(img));
                        resonalImage = b.getStudent_image();
                    } else {
                        resonalImage = b.getStudent_image();
//                        ImageIcon icon = new ImageIcon(getClass()
//                                .getResource("/com/teamvietdev/qlhv/images/cute.jpg"));
//                        lblanh.setIcon(icon);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Lỗi");
        }
    }//GEN-LAST:event_tblsinhvienMouseClicked

    private void txtMaSVKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaSVKeyReleased
//        validater();
    }//GEN-LAST:event_txtMaSVKeyReleased

    private void txtTenSVKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenSVKeyReleased
//        validater();
    }//GEN-LAST:event_txtTenSVKeyReleased

    private void cbTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTrangThaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTrangThaiActionPerformed

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed

    }//GEN-LAST:event_txtSDTActionPerformed

    private void txtSDTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDTKeyReleased

    }//GEN-LAST:event_txtSDTKeyReleased

    private void cbDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDiaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbDiaChiActionPerformed

    private void txtCCCDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCCCDKeyReleased

    }//GEN-LAST:event_txtCCCDKeyReleased

    private void cbKhoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKhoaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbKhoaActionPerformed

    private void cbNganhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNganhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbNganhActionPerformed

    private void cbGTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbGTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbGTActionPerformed

    private void cbPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPhongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbPhongActionPerformed

    private void btnanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnanhActionPerformed
//        JFileChooser chooser = new JFileChooser();
//        chooser.setFileFilter(new FileFilter() {
//            @Override
//            public boolean accept(File f) {
//                if (f.isDirectory()) {
//                    return true;
//                } else {
//                    return f.getName().toLowerCase().endsWith(".jpg");
//                }
//            }
//
//            @Override
//            public String getDescription() {
//                return "Image Fide(*.jpg)";
//            }
//
//        });
//        if (chooser.showOpenDialog(jLabel1) == JFileChooser.CANCEL_OPTION) {
//            return;
//        }
//        File file = chooser.getSelectedFile();
//        String fileName = file.getName();
//        try {
//            ImageIcon icon = new ImageIcon(file.getPath());
//            Image img = Image_nckh.resize(icon.getImage(), 150, 150);
//            ImageIcon resizeIcon = new ImageIcon(img);
//            lblanh.setIcon(resizeIcon);
//            porsonimage = Image_nckh.toByteArray(img, "jpg");
//            //                jLabel7.setText(fileName);
//        } catch (Exception e) {
//            e.printStackTrace();
//            messconec.showMessageDialog(jPanel1, e.getMessage(), "lỗi");
//        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnanhActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        StringBuilder sb = new StringBuilder();

        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "BẠN CHẮC CHẮN Sửa") == JOptionPane.NO_OPTION) {
            return;
        }
        try {
            StudentModel bk = new StudentModel();
            bk.setStudent_id(txtMaSV.getText());
            bk.setStudent_name(txtTenSV.getText());
            bk.setPhone_number(txtSDT.getText().replace("-", ""));
            bk.setHometown(cbDiaChi.getSelectedItem().toString());
            bk.setStatus(cbTrangThai.getSelectedItem().toString());
            bk.setGmail(txtCCCD.getText());
            bk.setGender(cbGT.getSelectedItem().toString());
            bk.setFaculty(cbKhoa.getSelectedItem().toString());
            bk.setMajor(cbNganh.getSelectedItem().toString());
            bk.setRoom_id(cbPhong.getSelectedItem().toString());
            bk.setBirth_date(birthdate.getDate());
            bk.setCheck_in_date(checkoutdate.getDate());
            bk.setStudent_image(resonalImage);
            bk.setViolation_count((int) spSLVP.getValue());
            try {
                StudentDao.updateStudent(bk);
                JOptionPane.showMessageDialog(rootPane, "Cập nhật sinh viên thành công");
                LoadnhaxeTable();
            } catch (Exception ex) {
                Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
//                JOptionPane.showMessageDialog(rootPane, "Sinh viên đã tồn tại hoặc có lỗi xảy ra.");
            }

        } catch (Exception ex) {
            Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(this);
            File saveFile = jFileChooser.getSelectedFile();

            if (saveFile != null) {
                saveFile = new File(saveFile.toString() + ".xlsx");
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("customer");

                // Tạo hàng tiêu đề cho bảng
                Row rowCol = sheet.createRow(0);
                for (int i = 0; i < tblsinhvien.getColumnCount(); i++) {
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(tblsinhvien.getColumnName(i));
                }

                // Lấy tất cả dữ liệu từ bảng
                for (int j = 0; j < tblsinhvien.getRowCount(); j++) {
                    TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblsinhvien.getModel());
                    tblsinhvien.setRowSorter(sorter);
                    int modelRow = tblsinhvien.convertRowIndexToModel(j);
                    Row row = sheet.createRow(j + 1);
                    for (int k = 0; k < tblsinhvien.getColumnCount(); k++) {
                        Object cellValue = tblsinhvien.getModel().getValueAt(modelRow, k);
                        Cell cell = row.createCell(k);
                        if (cellValue != null) {
                            cell.setCellValue(cellValue.toString());
                        }
                    }
                }

                FileOutputStream out = new FileOutputStream(saveFile);
                wb.write(out);
                out.close();
                openFile(saveFile.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        jFrame2.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed
    public void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

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
            java.util.logging.Logger.getLogger(student.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(student.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(student.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(student.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                    new student().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser birthdate;
    private javax.swing.JButton btnanh;
    private javax.swing.JComboBox<String> cbDiaChi;
    private javax.swing.JComboBox<String> cbGT;
    private javax.swing.JComboBox<String> cbKhoa;
    private javax.swing.JComboBox<String> cbNganh;
    private javax.swing.JComboBox<String> cbPhong;
    private javax.swing.JComboBox<String> cbTrangThai;
    private com.toedter.calendar.JDateChooser checkoutdate;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblanh;
    private javax.swing.JSpinner spSLVP;
    private javax.swing.JTable tblsinhvien;
    private javax.swing.JTextField txtCCCD;
    private javax.swing.JTextField txtMaSV;
    private javax.swing.JFormattedTextField txtSDT;
    private javax.swing.JTextField txtTenSV;
    private javax.swing.JTextField txtTimHoTen;
    // End of variables declaration//GEN-END:variables
}
