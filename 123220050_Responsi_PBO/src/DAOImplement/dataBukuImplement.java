/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAOImplement;

import java.util.List;
import model.dataBuku;

/**
 *
 * @author LENOVO
 */
public interface dataBukuImplement {

    public void insert(dataBuku db);

    public void update(dataBuku db);

    public void delete(Integer id);

    public List<dataBuku> getAll();
}
