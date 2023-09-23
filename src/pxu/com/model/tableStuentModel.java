/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pxu.com.model;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NGUYEN DUNG
 */
public class tableStuentModel extends DefaultTableModel {

    public tableStuentModel() {
        super();
        this.addColumn("Ma SV");
        this.addColumn("Ten SV");
        this.addColumn("Khoa ");
        this.addColumn("Nganh");
        this.addColumn("Ngay Sinh");
        this.addColumn("So CCCD");
        this.addColumn("SĐT");
        this.addColumn("Gioi Tinh");
        this.addColumn("Que Quan");
        this.addColumn("Phong so");
        this.addColumn("Vi Pham");
        this.addColumn("Ngay Vao");
        this.addColumn("Trang Thai");

    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public Class getColumnClass(int column) {
        switch (column) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return String.class;
            case 5:
                return String.class;
            case 6:
                return String.class;
            case 7:
                return String.class;
            case 8:
                return String.class;
            case 9:
                return String.class;
            case 10:
                return String.class;
            case 11:
                return String.class;
            case 12:
                return String.class;
            case 13:
                return String.class;
            default:
                return String.class;
        }
    }
}
