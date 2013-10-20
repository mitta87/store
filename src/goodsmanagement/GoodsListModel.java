/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package goodsmanagement;

import dao.Category;
import dao.Good;
import dao.GoodDao;
import dao.util.ConnectionFactory;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author Митя
 */
public class GoodsListModel extends DefaultListModel<Good> {

    public GoodsListModel(List<Good> list) {
        for (Good good : list) {
            this.addElement(good);
        }
    }

    public GoodsListModel() {
         Connection conn = null;
       try {
            conn = ConnectionFactory.getDefaultConnection();
            List<Good> goods = new GoodDao().list(conn);
            for (Good good : goods) {
                this.addElement(good);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GoodsListModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GoodsListModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GoodsListModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
       finally{
             try {
                 conn.close();
             } catch (SQLException ex) {
                 Logger.getLogger(GoodsListModel.class.getName()).log(Level.SEVERE, null, ex);
             }
       }
    }
    
    

    public GoodsListModel(Category cat) {
        try {
            Connection conn = ConnectionFactory.getDefaultConnection();
            List<Good> goods = new GoodDao().list(conn, cat);
            for (Good good : goods) {
                this.addElement(good);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GoodsListModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
