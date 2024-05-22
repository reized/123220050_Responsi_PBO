/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOData;

import java.sql.*;
import DAOImplement.dataBukuImplement;
import connection.connector;
import java.util.ArrayList;
import java.util.List;
import model.dataBuku;

/**
 *
 * @author LENOVO
 */
public class dataBukuDAO implements dataBukuImplement {

    Connection connection;

    final String select = "SELECT * FROM buku";
    final String insert = "INSERT INTO buku (judul, penulis, rating, harga, id) VALUES (?, ?, ?, ?, ?)";
    final String update = "UPDATE buku SET judul = ?, penulis = ?, rating = ?, harga = ? WHERE id = ?";
    final String delete = "DELETE FROM buku WHERE id = ?";

    public dataBukuDAO() {
        connection = connector.connection();
    }

    private float hitungHarga(float harga, float rating) {
        int perawatan = 500;

        float hasil = harga + perawatan + (rating * 100);
        return hasil;
    }

    @Override
    public void insert(dataBuku db) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, db.getJudul());
            statement.setString(2, db.getPenulis());
            statement.setFloat(3, db.getRating());

            float hasil = hitungHarga(db.getHarga(), db.getRating());
            statement.setFloat(4, hasil);

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                db.setId(rs.getInt(5));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void update(dataBuku db) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, db.getJudul());
            statement.setString(2, db.getPenulis());
            statement.setFloat(3, db.getRating());

            float hasil = hitungHarga(db.getHarga(), db.getRating());
            statement.setFloat(4, hasil);
            statement.setInt(5, db.getId());
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Integer id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(delete);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<dataBuku> getAll() {
        List<dataBuku> lb = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(select);
            while (rs.next()) {
                dataBuku buku = new dataBuku();
                buku.setJudul(rs.getString("judul"));
                buku.setPenulis(rs.getString("penulis"));
                buku.setRating(rs.getFloat("rating"));
                buku.setHarga(rs.getFloat("harga"));
                buku.setId(rs.getInt("id"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return lb;
    }
}
