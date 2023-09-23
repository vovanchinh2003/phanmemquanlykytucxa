/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pxu.com.views;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
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
import pxu.com.dao.CheckoutDao;
import pxu.com.dao.RoomrentalDao;
import pxu.com.dao.RoomtransferDao;
import pxu.com.dao.ViolationDao;
import pxu.com.model.CheckoutModel;
import pxu.com.model.RoomrentalModel;
import pxu.com.model.RoomtransferModel;

/**
 *
 * @author chinh
 */
public class roomrto extends javax.swing.JFrame {

    private DefaultTableModel tblModel, tblModel1, tblModel2;
    private ArrayList<RoomrentalModel> roomrentalModels;
    private ArrayList<CheckoutModel> checkoutModels;
    private ArrayList<RoomtransferModel> roomtransferModels;
    private TableRowSorter thue, chuyen, tra;

    /**
     * Creates new form Roomrto
     */
    public roomrto() throws SQLException, ClassNotFoundException {
        initComponents();
        intable();
        LoadthuephongTable();
        intablecheckout();
        LoadttraTable();
        intablechuyen();
        LoadchuyenTable();
        thuephong();
        chuyenphong();
        traphong();
        this.setSize(927, 600);
        this.setLocationRelativeTo(null);
    }

    private void intable() {
        tblModel = new DefaultTableModel();
        tblModel.setColumnIdentifiers(new String[]{"Mã sinh viên", "User_id", "Mã Phòng", "Ngày thuê", "Trạng thái"});
        tablehoadonthuephong.setModel(tblModel);
    }

    private void LoadthuephongTable() throws SQLException, ClassNotFoundException {
        roomrentalModels = RoomrentalDao.getAll();
        thue = new TableRowSorter(tblModel);
        tablehoadonthuephong.setRowSorter(thue);
        tblModel.setRowCount(0);
        for (RoomrentalModel m : roomrentalModels) {
            Object[] object = new Object[]{m.getStudent_id(), m.getUser_id(), m.getRoom_id(), m.getRental_date(), m.getStatus()};
            tblModel.addRow(object);
        }
        tblModel.fireTableDataChanged();
    }

    private void intablecheckout() {
        tblModel1 = new DefaultTableModel();
        tblModel1.setColumnIdentifiers(new String[]{"Mã sinh viên", "User_id", "Mã Phòng", "Ngày trả", "Lý do"});
        tabletraphong.setModel(tblModel1);
    }

    private void LoadttraTable() throws SQLException, ClassNotFoundException {
        checkoutModels = CheckoutDao.getAll();
        tra = new TableRowSorter(tblModel1);
        tabletraphong.setRowSorter(tra);
        tblModel1.setRowCount(0);
        for (CheckoutModel m : checkoutModels) {
            Object[] object = new Object[]{m.getStudent_id(), m.getUser_id(), m.getRoom_id(), m.getCheckout_date(), m.getReason()};
            tblModel1.addRow(object);
        }
        tblModel1.fireTableDataChanged();
    }

    private void intablechuyen() {
        tblModel2 = new DefaultTableModel();
        tblModel2.setColumnIdentifiers(new String[]{"Mã sinh viên", "User_id", "Mã Phòng cũ", "Mã Phòng mới", "Ngày trả", "Lý do"});
        tablechuyen.setModel(tblModel2);
    }

    private void LoadchuyenTable() throws SQLException, ClassNotFoundException {
        roomtransferModels = RoomtransferDao.getAll();
        chuyen = new TableRowSorter(tblModel2);
        tablechuyen.setRowSorter(chuyen);
        tblModel2.setRowCount(0);
        for (RoomtransferModel m : roomtransferModels) {
            Object[] object = new Object[]{m.getStudent_id(), m.getUser_id(), m.getPrevious_room_id(), m.getRoom_id(), m.getTransfer_date(), m.getReason()};
            tblModel2.addRow(object);
        }
        tblModel2.fireTableDataChanged();
    }

    private void thuephong() {
        txttimkiem3.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchthue(txttimkiem3.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchthue(txttimkiem3.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchthue(txttimkiem3.getText());
            }
        });
    }

    private void chuyenphong() {
        txttimchuyen1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchchuyen(txttimchuyen1.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchchuyen(txttimchuyen1.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchchuyen(txttimchuyen1.getText());
            }
        });
    }

    private void traphong() {
        txttimkiem2.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchtra(txttimkiem2.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchtra(txttimkiem2.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchtra(txttimkiem2.getText());
            }
        });
    }
    //Ham tim kiem theo chuoi str

    private void searchthue(String str) {
        if (str.length() == 0) {
            thue.setRowFilter(null);
        } else {
            thue.setRowFilter(RowFilter.regexFilter(str));
        }
    }

    private void searchchuyen(String str) {
        if (str.length() == 0) {
            chuyen.setRowFilter(null);
        } else {
            chuyen.setRowFilter(RowFilter.regexFilter(str));
        }
    }

    private void searchtra(String str) {
        if (str.length() == 0) {
            tra.setRowFilter(null);
        } else {
            tra.setRowFilter(RowFilter.regexFilter(str));
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

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablehoadonthuephong = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        txttimkiem3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabletraphong = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        txttimkiem2 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablechuyen = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        txttimchuyen1 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setTitle("Hoá đơn");

        jPanel1.setLayout(new java.awt.BorderLayout());

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jPanel4.setLayout(new java.awt.CardLayout(20, 10));

        jPanel5.setLayout(new java.awt.GridLayout(2, 0));

        jPanel6.setLayout(new java.awt.BorderLayout());

        tablehoadonthuephong.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablehoadonthuephong);

        jPanel6.add(jScrollPane1, java.awt.BorderLayout.PAGE_START);

        jPanel5.add(jPanel6);

        txttimkiem3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txttimkiem3MouseClicked(evt);
            }
        });
        txttimkiem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttimkiem3ActionPerformed(evt);
            }
        });
        txttimkiem3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txttimkiem3KeyPressed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 0, 153));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Xuất excel ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 153, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Tìm kiếm:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(207, 207, 207)
                .addComponent(jButton1)
                .addGap(32, 32, 32)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txttimkiem3, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(277, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttimkiem3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(429, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel7);

        jPanel4.add(jPanel5, "card2");

        jTabbedPane1.addTab("Thống kê thuê phòng", jPanel4);

        jPanel3.setLayout(new java.awt.CardLayout(20, 10));

        jPanel8.setLayout(new java.awt.GridLayout(2, 0));

        jPanel9.setLayout(new java.awt.BorderLayout());

        tabletraphong.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tabletraphong);

        jPanel9.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel8.add(jPanel9);

        jButton3.setBackground(new java.awt.Color(0, 0, 153));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Xuất excel");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 153, 0));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Tìm kiếm");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(203, 203, 203)
                .addComponent(jButton3)
                .addGap(37, 37, 37)
                .addComponent(jButton4)
                .addGap(11, 11, 11)
                .addComponent(txttimkiem2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(285, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttimkiem2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(429, Short.MAX_VALUE))
        );

        jPanel8.add(jPanel10);

        jPanel3.add(jPanel8, "card2");

        jTabbedPane1.addTab("Thống kê trả phòng", jPanel3);

        jPanel2.setLayout(new java.awt.CardLayout(20, 10));

        jPanel14.setLayout(new java.awt.GridLayout(2, 0));

        jPanel15.setLayout(new java.awt.BorderLayout());

        tablechuyen.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tablechuyen);

        jPanel15.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        jPanel14.add(jPanel15);

        jButton5.setBackground(new java.awt.Color(0, 0, 153));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Xuất excel");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(255, 153, 0));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Tìm kiếm");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(212, 212, 212)
                .addComponent(jButton5)
                .addGap(33, 33, 33)
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txttimchuyen1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(285, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttimchuyen1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addContainerGap(429, Short.MAX_VALUE))
        );

        jPanel14.add(jPanel16);

        jPanel2.add(jPanel14, "card2");

        jTabbedPane1.addTab("Thống kê chuyển phòng", jPanel2);

        jPanel1.add(jTabbedPane1, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txttimkiem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttimkiem3ActionPerformed
    }//GEN-LAST:event_txttimkiem3ActionPerformed

    private void txttimkiem3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txttimkiem3MouseClicked
    }//GEN-LAST:event_txttimkiem3MouseClicked

    private void txttimkiem3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimkiem3KeyPressed
    }//GEN-LAST:event_txttimkiem3KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
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
                for (int i = 0; i < tablehoadonthuephong.getColumnCount(); i++) {
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(tablehoadonthuephong.getColumnName(i));
                }

                // Lấy tất cả dữ liệu từ bảng
                for (int j = 0; j < tablehoadonthuephong.getRowCount(); j++) {
                    TableRowSorter<TableModel> sorter = new TableRowSorter<>(tablehoadonthuephong.getModel());
                    tablehoadonthuephong.setRowSorter(sorter);
                    int modelRow = tablehoadonthuephong.convertRowIndexToModel(j);
                    Row row = sheet.createRow(j + 1);
                    for (int k = 0; k < tablehoadonthuephong.getColumnCount(); k++) {
                        Object cellValue = tablehoadonthuephong.getModel().getValueAt(modelRow, k);
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

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
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
                for (int i = 0; i < tabletraphong.getColumnCount(); i++) {
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(tabletraphong.getColumnName(i));
                }

                // Lấy tất cả dữ liệu từ bảng
                for (int j = 0; j < tabletraphong.getRowCount(); j++) {
                    TableRowSorter<TableModel> sorter = new TableRowSorter<>(tabletraphong.getModel());
                    tabletraphong.setRowSorter(sorter);
                    int modelRow = tabletraphong.convertRowIndexToModel(j);
                    Row row = sheet.createRow(j + 1);
                    for (int k = 0; k < tabletraphong.getColumnCount(); k++) {
                        Object cellValue = tabletraphong.getModel().getValueAt(modelRow, k);
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
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
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
                for (int i = 0; i < tablechuyen.getColumnCount(); i++) {
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(tablechuyen.getColumnName(i));
                }

                // Lấy tất cả dữ liệu từ bảng
                for (int j = 0; j < tablechuyen.getRowCount(); j++) {
                    TableRowSorter<TableModel> sorter = new TableRowSorter<>(tablechuyen.getModel());
                    tablechuyen.setRowSorter(sorter);
                    int modelRow = tablechuyen.convertRowIndexToModel(j);
                    Row row = sheet.createRow(j + 1);
                    for (int k = 0; k < tablechuyen.getColumnCount(); k++) {
                        Object cellValue = tablechuyen.getModel().getValueAt(modelRow, k);
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
            java.util.logging.Logger.getLogger(roomrto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(roomrto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(roomrto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(roomrto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new roomrto().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(roomrto.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(roomrto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tablechuyen;
    private javax.swing.JTable tablehoadonthuephong;
    private javax.swing.JTable tabletraphong;
    private javax.swing.JTextField txttimchuyen1;
    private javax.swing.JTextField txttimkiem2;
    private javax.swing.JTextField txttimkiem3;
    // End of variables declaration//GEN-END:variables
}
