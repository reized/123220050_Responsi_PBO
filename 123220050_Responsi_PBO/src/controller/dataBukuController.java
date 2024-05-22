/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAOData.dataBukuDAO;
import DAOImplement.dataBukuImplement;
import java.util.List;
import javax.swing.JOptionPane;
import model.dataBuku;
import model.modelTabelDataBuku;
import view.mainView;

/**
 *
 * @author LENOVO
 */
public class dataBukuController {

    mainView frame;
    dataBukuImplement implDataBuku;
    List<dataBuku> db;

    private Integer deleteValue;
    private String tmpJudul;
    private String tmpPenulis;
    private Float tmpRating;
    private Float tmpHarga;
    private boolean insertError;
    private boolean updateError;
    private boolean deleteError;

    private boolean isFormValid() {
        if (frame.getTxtJudul().getText().isEmpty() || frame.getTxtPenulis().getText().isEmpty() || frame.getTxtRating().getText().isEmpty() || frame.getTxtHarga().getText().isEmpty()) {
            return false;
        }

        try {
            float rating = Float.parseFloat(frame.getTxtRating().getText());
            float harga = Float.parseFloat(frame.getTxtHarga().getText());

            return rating >= 0 && rating <= 5 && harga >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public dataBukuController(mainView frame) {
        this.frame = frame;
        implDataBuku = new dataBukuDAO();
        db = implDataBuku.getAll();
    }

    public void isiTabel() {
        db = implDataBuku.getAll();
        modelTabelDataBuku mb = new modelTabelDataBuku(db);
        frame.getTabelBuku().setModel(mb);
    }

    public void insert() {
        if (isFormValid()) {
            try {
                dataBuku buku = new dataBuku();
                buku.setJudul(frame.getTxtJudul().getText());
                buku.setPenulis(frame.getTxtPenulis().getText());
                buku.setRating(Float.valueOf(frame.getTxtRating().getText()));
                buku.setHarga(Float.valueOf(frame.getTxtHarga().getText()));

                implDataBuku.insert(buku);
                frame.clearForm();
                insertError = false;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Mohon isi data", "Warning", JOptionPane.WARNING_MESSAGE);
                insertError = true;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Mohon isi data", "Warning", JOptionPane.WARNING_MESSAGE);
            insertError = true;
        }
    }

    public void update() {
        if (isFormValid()) {
            try {
                dataBuku buku = new dataBuku();
                buku.setJudul(frame.getTxtJudul().getText());
                buku.setPenulis(frame.getTxtPenulis().getText());
                buku.setRating(Float.valueOf(frame.getTxtRating().getText()));
                buku.setHarga(Float.valueOf(frame.getTxtHarga().getText()));

                implDataBuku.update(buku);
                frame.clearForm();
                updateError = false;
                JOptionPane.showMessageDialog(null, "Data berhasil diubah", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Mohon isi data", "Warning", JOptionPane.WARNING_MESSAGE);
                updateError = true;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Mohon isi data", "Warning", JOptionPane.WARNING_MESSAGE);
            updateError = true;
        }
    }

    public void delete() {
        if (isFormValid()) {
            try {
                dataBuku buku = new dataBuku();
                deleteValue = buku.getId();

                implDataBuku.delete(deleteValue);
                frame.clearForm();
                deleteError = false;
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Mohon isi data", "Warning", JOptionPane.WARNING_MESSAGE);
                deleteError = true;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Mohon isi data", "Warning", JOptionPane.WARNING_MESSAGE);
            deleteError = true;
        }
    }

    public boolean isInsertError() {
        return insertError;
    }

    public boolean isUpdateError() {
        return updateError;
    }

    public boolean isDeleteError() {
        return deleteError;
    }
}
