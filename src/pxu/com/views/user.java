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
import javax.swing.filechooser.FileFilter;
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
import pxu.com.dao.UserDao;
import pxu.com.dialogchek.imageheiper;
import pxu.com.dialogchek.showuser;
import pxu.com.model.CrarentalModel;
import pxu.com.model.UserModel;

/**
 *
 * @author chinh
 */
public class user extends javax.swing.JFrame {
    
    private ArrayList<UserModel> userModels;
    private DefaultTableModel tblModel;
    private TableRowSorter sorter;

    /**
     * Creates new form Quanlynhaxe
     */
    public user() throws SQLException, ClassNotFoundException {
        initComponents();
        initTable();
        LoadnhaxeTable();
        timkiem();
        chuotphaijtable();
        this.setSize(900, 580);
        this.setLocationRelativeTo(null);
        jFrame2.setSize(900, 180);
        jFrame3.setSize(900, 180);
        
    }
    
    private void chuotphaijtable() {
        tblnhanSu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    JPopupMenu popup = new JPopupMenu();
                    JMenuItem capnhat = new JMenuItem("Cập nhật");
                    capnhat.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            jFrame2.setVisible(true);
                        }
                    });
                    capnhat.setForeground(Color.ORANGE);
                    popup.add(capnhat);
                    JMenuItem xoa = new JMenuItem("Xoá");
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
                                Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    xoa.setForeground(Color.RED);
                    popup.add(xoa);
                    popup.show(tblnhanSu, e.getX(), e.getY());
                }
            }
        });
    }
    
    private void initTable() {
        tblModel = new DefaultTableModel();
        tblModel.setColumnIdentifiers(new String[]{"Mã nhân viên", "Họ và tên", "Địa chỉ", "SĐT", "Vai trò", "Tên tài khoản", "Mật khẩu"});
        tblnhanSu.setModel(tblModel);
    }
    
    private void LoadnhaxeTable() throws SQLException, ClassNotFoundException {
        userModels = UserDao.getall();
        sorter = new TableRowSorter(tblModel);
        tblnhanSu.setRowSorter(sorter);
        tblModel.setRowCount(0);
        for (UserModel m : userModels) {
            Object[] object = new Object[]{m.getUser_id(), m.getFull_name(), m.getAddress(), m.getPhone_number(), m.getPosition(), m.getUsername(), m.getPassword()};
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
        java.awt.GridBagConstraints gridBagConstraints;

        jFrame2 = new javax.swing.JFrame();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaNS = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTenNS = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cbVaiTro = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JFormattedTextField();
        cbDiaChi = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtTTK = new javax.swing.JTextField();
        txtPW = new javax.swing.JPasswordField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        lblanh = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jFrame3 = new javax.swing.JFrame();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtMaNS1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtTenNS1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cbVaiTro1 = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtSDT1 = new javax.swing.JFormattedTextField();
        cbDiaChi1 = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtTTK1 = new javax.swing.JTextField();
        txtPW1 = new javax.swing.JPasswordField();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        lblanh1 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        txtTimHoTen = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblnhanSu = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();

        jFrame2.setTitle("Cập nhật thông tin nhân viên");
        jFrame2.setLocation(new java.awt.Point(300, 200));
        jFrame2.setMinimumSize(new java.awt.Dimension(360, 380));

        jPanel16.setLayout(new java.awt.GridLayout(1, 2));

        jPanel17.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Mã Nhân Sự:");
        jPanel17.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        txtMaNS.setColumns(40);
        txtMaNS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaNSKeyReleased(evt);
            }
        });
        jPanel17.add(txtMaNS, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 220, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Tên Nhân Sự :");
        jPanel17.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        txtTenNS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTenNSKeyReleased(evt);
            }
        });
        jPanel17.add(txtTenNS, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 220, -1));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Địa Chỉ       :");
        jLabel9.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel17.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 100, -1));

        cbVaiTro.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        cbVaiTro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quản Lý", "Nhân Viên" }));
        cbVaiTro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbVaiTroActionPerformed(evt);
            }
        });
        jPanel17.add(cbVaiTro, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 220, -1));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Vai Trò :");
        jPanel17.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        jLabel20.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("Số Điện Thoại :");
        jPanel17.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

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
        jPanel17.add(txtSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 220, -1));

        cbDiaChi.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        cbDiaChi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "An Giang", "Bà Rịa – Vũng Tàu", "Bắc Giang", "Bắc Kạn", "Bạc Liêu", "Bắc Ninh", "Bến Tre", "Bình Định", "Bình Dương", "Bình Phước", "Bình Thuận", "Cà Mau", "Cần Thơ", "Cao Bằng", "Đà Nẵng", "Đắk Lắk", "Đắk Nông", "Điện Biên", "Đồng Nai", "Đồng Tháp", "Gia Lai", "Hà Giang", "Hà Nam", "Hà Nội", "Hà Tĩnh", "Hải Dương", "Hải Phòng", "Hậu Giang", "Hòa Bình", "Hưng Yên", "Khánh Hòa", "Kiên Giang", "Kon Tum", "Lai Châu", "Lâm Đồng", "Lạng Sơn", "Lào Cai", "Long An", "Nam Định", "Nghệ An", "Ninh Bình", "Ninh Thuận", "Phú Thọ", "Phú Yên", "Quảng Bình", "Quảng Nam", "Quảng Ngãi", "Quảng Ninh", "Quảng Trị", "Sóc Trăng", "Sơn La", "Tây Ninh", "Thái Bình", "Thái Nguyên", "Thanh Hóa", "Thừa Thiên Huế", "Tiền Giang", "Thành phố Hồ Chí Minh", "Trà Vinh", "Tuyên Quang", "Vĩnh Long", "Vĩnh Phúc", "Yên Bái" }));
        cbDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDiaChiActionPerformed(evt);
            }
        });
        jPanel17.add(cbDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 220, -1));

        jLabel21.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel21.setText("Tên Tài Khoản :");
        jPanel17.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        jLabel22.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel22.setText("Mật Khẩu :");
        jPanel17.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));
        jPanel17.add(txtTTK, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 220, -1));
        jPanel17.add(txtPW, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, 220, -1));

        jButton4.setBackground(new java.awt.Color(255, 204, 0));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Cập nhật");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel17.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, -1, -1));

        jButton5.setBackground(new java.awt.Color(255, 0, 0));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Huỷ");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel17.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, -1, -1));

        jPanel16.add(jPanel17);

        jPanel18.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel18.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(16, 13, 45, 14);
        jPanel18.add(lblanh, gridBagConstraints);

        jButton3.setBackground(new java.awt.Color(255, 204, 204));
        jButton3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton3.setText("Chọn ảnh");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel18.add(jButton3, new java.awt.GridBagConstraints());

        jPanel16.add(jPanel18);

        jFrame2.getContentPane().add(jPanel16, java.awt.BorderLayout.CENTER);

        jFrame3.setTitle("Thêm nhân viên");
        jFrame3.setLocation(new java.awt.Point(300, 200));
        jFrame3.setMinimumSize(new java.awt.Dimension(360, 380));

        jPanel19.setLayout(new java.awt.GridLayout(1, 2));

        jPanel20.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Mã Nhân Sự:");
        jPanel20.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        txtMaNS1.setColumns(40);
        txtMaNS1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaNS1KeyReleased(evt);
            }
        });
        jPanel20.add(txtMaNS1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 220, -1));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Tên Nhân Sự :");
        jPanel20.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        txtTenNS1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTenNS1KeyReleased(evt);
            }
        });
        jPanel20.add(txtTenNS1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 220, -1));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Địa Chỉ       :");
        jLabel12.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel20.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 100, -1));

        cbVaiTro1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        cbVaiTro1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quản Lý", "Nhân Viên" }));
        cbVaiTro1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbVaiTro1ActionPerformed(evt);
            }
        });
        jPanel20.add(cbVaiTro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 220, -1));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Vai Trò :");
        jPanel20.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        jLabel23.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("Số Điện Thoại :");
        jPanel20.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        try {
            txtSDT1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-###-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtSDT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDT1ActionPerformed(evt);
            }
        });
        jPanel20.add(txtSDT1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 220, -1));

        cbDiaChi1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        cbDiaChi1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "An Giang", "Bà Rịa – Vũng Tàu", "Bắc Giang", "Bắc Kạn", "Bạc Liêu", "Bắc Ninh", "Bến Tre", "Bình Định", "Bình Dương", "Bình Phước", "Bình Thuận", "Cà Mau", "Cần Thơ", "Cao Bằng", "Đà Nẵng", "Đắk Lắk", "Đắk Nông", "Điện Biên", "Đồng Nai", "Đồng Tháp", "Gia Lai", "Hà Giang", "Hà Nam", "Hà Nội", "Hà Tĩnh", "Hải Dương", "Hải Phòng", "Hậu Giang", "Hòa Bình", "Hưng Yên", "Khánh Hòa", "Kiên Giang", "Kon Tum", "Lai Châu", "Lâm Đồng", "Lạng Sơn", "Lào Cai", "Long An", "Nam Định", "Nghệ An", "Ninh Bình", "Ninh Thuận", "Phú Thọ", "Phú Yên", "Quảng Bình", "Quảng Nam", "Quảng Ngãi", "Quảng Ninh", "Quảng Trị", "Sóc Trăng", "Sơn La", "Tây Ninh", "Thái Bình", "Thái Nguyên", "Thanh Hóa", "Thừa Thiên Huế", "Tiền Giang", "Thành phố Hồ Chí Minh", "Trà Vinh", "Tuyên Quang", "Vĩnh Long", "Vĩnh Phúc", "Yên Bái" }));
        cbDiaChi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDiaChi1ActionPerformed(evt);
            }
        });
        jPanel20.add(cbDiaChi1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 220, -1));

        jLabel24.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel24.setText("Tên Tài Khoản :");
        jPanel20.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        jLabel25.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel25.setText("Mật Khẩu :");
        jPanel20.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));
        jPanel20.add(txtTTK1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 220, -1));
        jPanel20.add(txtPW1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, 220, -1));

        jButton8.setBackground(new java.awt.Color(255, 204, 0));
        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Thêm");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel20.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, -1, -1));

        jButton9.setBackground(new java.awt.Color(255, 0, 0));
        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Huỷ");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel20.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, -1, -1));

        jPanel19.add(jPanel20);

        jPanel21.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel21.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(16, 13, 45, 14);
        jPanel21.add(lblanh1, gridBagConstraints);

        jButton10.setBackground(new java.awt.Color(255, 204, 204));
        jButton10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton10.setText("Chọn ảnh");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel21.add(jButton10, new java.awt.GridBagConstraints());

        jPanel19.add(jPanel21);

        jFrame3.getContentPane().add(jPanel19, java.awt.BorderLayout.CENTER);

        setTitle("Quản lý nhà xe");
        setMinimumSize(new java.awt.Dimension(710, 422));

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.CardLayout(100, 15));

        jPanel7.setLayout(new java.awt.GridLayout(1, 0, 10, 10));

        jButton7.setBackground(new java.awt.Color(0, 0, 204));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Xuất excel");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton7);

        jButton2.setBackground(new java.awt.Color(0, 255, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Thêm");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton2);

        jButton6.setBackground(new java.awt.Color(255, 153, 0));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Tìm kiếm:");
        jPanel7.add(jButton6);
        jPanel7.add(txtTimHoTen);

        jPanel2.add(jPanel7, "card2");

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel9.setLayout(new java.awt.CardLayout(10, 5));

        tblnhanSu.setModel(new javax.swing.table.DefaultTableModel(
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
        tblnhanSu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblnhanSuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblnhanSu);

        jPanel9.add(jScrollPane1, "card2");

        jPanel8.add(jPanel9, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel10.setLayout(new java.awt.CardLayout(50, 10));

        jPanel11.setLayout(new java.awt.BorderLayout());

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 102, 0));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Quản lý nhân viên");
        jPanel11.add(jLabel19, java.awt.BorderLayout.CENTER);

        jPanel10.add(jPanel11, "card2");

        jPanel1.add(jPanel10, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jFrame3.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tblnhanSuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblnhanSuMouseClicked
        try {
            int row = tblnhanSu.getSelectedRow();
            if (row >= 0) {
                String tennhansu = String.valueOf(tblnhanSu.getValueAt(row, 0));
                UserDao dk = new UserDao();
                UserModel b = dk.FindManv(tennhansu);
                txtMaNS.setText(b.getUser_id());
                txtTenNS.setText(b.getFull_name());
                txtSDT.setText(b.getPhone_number());
                cbDiaChi.setSelectedItem(b.getAddress());
                txtTTK.setText(b.getUsername());
                cbVaiTro.setSelectedItem(b.getPosition());
                txtPW.setText(b.getPassword());
                if (b.getUser_image() != null) {
                    Image img = Image_nckh.createImageFromByteArray(b.getUser_image(), "jpg");
                    lblanh.setIcon(new ImageIcon(img));
                    resonalImage = b.getUser_image();
                } else {
                    resonalImage = b.getUser_image();
//                        ImageIcon icon = new ImageIcon(getClass()
//                            .getResource("/com/teamvietdev/qlhv/images/cute.jpg"));
//                        jLabel10.setIcon(icon);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }        // TODO add 
    }//GEN-LAST:event_tblnhanSuMouseClicked

    private void txtMaNSKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaNSKeyReleased

    }//GEN-LAST:event_txtMaNSKeyReleased

    private void txtTenNSKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenNSKeyReleased

    }//GEN-LAST:event_txtTenNSKeyReleased

    private void cbVaiTroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbVaiTroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbVaiTroActionPerformed

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed

    }//GEN-LAST:event_txtSDTActionPerformed

    private void cbDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDiaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbDiaChiActionPerformed
    private byte[] resonalImage;

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
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
            lblanh.setIcon(resizeIcon);
            resonalImage = imageheiper.toByteArray(img, "jpg");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(rootPane, "looix");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        StringBuilder sb = new StringBuilder();
        if (txtMaNS.getText().equals("")) {
            sb.append("Vui Lòng Nhập Mã San PhẨM \n");
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        try {
            UserModel bk = new UserModel();
            bk.setUser_id(txtMaNS.getText());
            bk.setFull_name(txtTenNS.getText());
            bk.setPhone_number(txtSDT.getText());
            bk.setAddress(cbDiaChi.getSelectedItem().toString());
            bk.setPosition(cbVaiTro.getSelectedItem().toString());
            bk.setUsername(txtTTK.getText());
            bk.setPassword(txtPW.getText());
            bk.setUser_image(resonalImage);
            UserDao dk = new UserDao();
            dk.update(bk);
            LoadnhaxeTable();
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công !");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sản Phẩm Này đã tồn tại trong cửa hàng.");
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        jFrame2.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
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
                for (int i = 0; i < tblnhanSu.getColumnCount(); i++) {
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(tblnhanSu.getColumnName(i));
                }

                // Lấy tất cả dữ liệu từ bảng
                for (int j = 0; j < tblnhanSu.getRowCount(); j++) {
                    TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblnhanSu.getModel());
                    tblnhanSu.setRowSorter(sorter);
                    int modelRow = tblnhanSu.convertRowIndexToModel(j);
                    Row row = sheet.createRow(j + 1);
                    for (int k = 0; k < tblnhanSu.getColumnCount(); k++) {
                        Object cellValue = tblnhanSu.getModel().getValueAt(modelRow, k);
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

    }//GEN-LAST:event_jButton7ActionPerformed

    private void txtMaNS1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaNS1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNS1KeyReleased

    private void txtTenNS1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenNS1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenNS1KeyReleased

    private void cbVaiTro1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbVaiTro1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbVaiTro1ActionPerformed

    private void txtSDT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDT1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDT1ActionPerformed

    private void cbDiaChi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDiaChi1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbDiaChi1ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        StringBuilder sb = new StringBuilder();
        if (txtMaNS.getText().equals("")) {
            sb.append("Vui Lòng Nhập Mã Nhân Viên \n");
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        try {
            UserModel bk = new UserModel();
            bk.setUser_id(txtMaNS1.getText());
            bk.setFull_name(txtTenNS1.getText());
            bk.setPhone_number(txtSDT1.getText());
            bk.setAddress(cbDiaChi1.getSelectedItem().toString());
            bk.setPosition(cbVaiTro1.getSelectedItem().toString());
            bk.setUsername(txtTTK1.getText());
            bk.setPassword(txtPW1.getText());
            bk.setUser_image(resonalImage);
            UserDao dk = new UserDao();
            dk.insertin(bk);
            LoadnhaxeTable();
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công !");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sản Phẩm Này đã tồn tại trong cửa hàng.");
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed
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
            java.util.logging.Logger.getLogger(user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                    new user().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbDiaChi;
    private javax.swing.JComboBox<String> cbDiaChi1;
    private javax.swing.JComboBox<String> cbVaiTro;
    private javax.swing.JComboBox<String> cbVaiTro1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JFrame jFrame3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblanh;
    private javax.swing.JLabel lblanh1;
    private javax.swing.JTable tblnhanSu;
    private javax.swing.JTextField txtMaNS;
    private javax.swing.JTextField txtMaNS1;
    private javax.swing.JPasswordField txtPW;
    private javax.swing.JPasswordField txtPW1;
    private javax.swing.JFormattedTextField txtSDT;
    private javax.swing.JFormattedTextField txtSDT1;
    private javax.swing.JTextField txtTTK;
    private javax.swing.JTextField txtTTK1;
    private javax.swing.JTextField txtTenNS;
    private javax.swing.JTextField txtTenNS1;
    private javax.swing.JTextField txtTimHoTen;
    // End of variables declaration//GEN-END:variables
}
