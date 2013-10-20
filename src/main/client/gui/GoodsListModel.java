/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.client.gui;

import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultListModel;
import main.beans.Category;
import main.beans.Good;
import main.business.GoodJdbcService;

/**
 *
 * @author Митя
 */
public class GoodsListModel extends DefaultListModel<Good> {
    private List<Good> allGoods;

    public GoodsListModel(List<Good> list) {
        for (Good good : list) {
            this.addElement(good);
            allGoods.add(good);
        }
    }

    public GoodsListModel() throws SQLException {         
            List<Good> goods = new GoodJdbcService().list();
            for (Good good : goods) {
                this.addElement(good);
            } 
       }
    
    

    public GoodsListModel(Category cat) throws SQLException {
            List<Good> goods = new GoodJdbcService().list(cat);
            for (Good good : goods) {
                this.addElement(good);
            }
        } 
    }

