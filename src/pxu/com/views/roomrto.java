/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pxu.com.views;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
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
    private TableRowSorter sorter;

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
        hh();
        hh2();
        hh3();
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
        sorter = new TableRowSorter(tblModel);
        tablehoadonthuephong.setRowSorter(sorter);
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
        sorter = new TableRowSorter(tblModel1);
        tabletraphong.setRowSorter(sorter);
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
        sorter = new TableRowSorter(tblModel2);
        tablechuyen.setRowSorter(sorter);
        tblModel2.setRowCount(0);
        for (RoomtransferModel m : roomtransferModels) {
            Object[] object = new Object[]{m.getStudent_id(), m.getUser_id(),m.getPrevious_room_id(), m.getRoom_id(), m.getTransfer_date(), m.getReason()};
            tblModel2.addRow(object);
        }
        tblModel2.fireTableDataChanged();
    }

    private void hh() {
        txttimkiem.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(txttimkiem.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(txttimkiem.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(txttimkiem.getText());
            }
        });
    }

    private void hh3() {
        txttimchuyen.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(txttimchuyen.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(txttimchuyen.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(txttimchuyen.getText());
            }
        });
    }

    private void hh2() {
        txttimkiem1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(txttimkiem1.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(txttimkiem1.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(txttimkiem1.getText());
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

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablehoadonthuephong = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txttimkiem = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabletraphong = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txttimkiem1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablechuyen = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txttimchuyen = new javax.swing.JTextField();

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

        jPanel6.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel6);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Tìm kiếm:");

        txttimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttimkiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(328, 328, 328)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(328, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(419, Short.MAX_VALUE))
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

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Tìm kiếm:");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(328, 328, 328)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txttimkiem1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(328, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txttimkiem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(419, Short.MAX_VALUE))
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

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Tìm kiếm:");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(328, 328, 328)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txttimchuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(328, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txttimchuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(419, Short.MAX_VALUE))
        );

        jPanel14.add(jPanel16);

        jPanel2.add(jPanel14, "card2");

        jTabbedPane1.addTab("Thống kê chuyển phòng", jPanel2);

        jPanel1.add(jTabbedPane1, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txttimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttimkiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttimkiemActionPerformed

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JTextField txttimchuyen;
    private javax.swing.JTextField txttimkiem;
    private javax.swing.JTextField txttimkiem1;
    // End of variables declaration//GEN-END:variables
}
