/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author LENOVO
 */
public class modelTabelDataBuku extends AbstractTableModel {

    List<dataBuku> data;

    public modelTabelDataBuku(List<dataBuku> data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Judul";
            case 1:
                return "Penulis";
            case 2:
                return "Rating";
            case 3:
                return "Harga";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch (column) {
            case 0:
                return data.get(row).getJudul();
            case 1:
                return data.get(row).getPenulis();
            case 2:
                return data.get(row).getRating();
            case 3:
                return data.get(row).getHarga();
            default:
                return null;
        }
    }

}
