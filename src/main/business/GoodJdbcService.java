/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.business;

import old.goodsmanagement.*;

import main.dao.GoodDao;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.beans.Category;
import main.beans.Good;
import main.dao.ConnectionFactory;
import main.dao.DaoUtil;

/**
 *
 * @author Митя
 */
public class GoodJdbcService{
    
    private GoodDao dao = new GoodDao();
    
     public List<Good> list() throws SQLException {
        Connection conn = null;
        List<Good> list = null;
        
            conn = ConnectionFactory.getDefaultConnection();
            list = dao.list(conn);      
                conn.close();        
       
        return list;
    }
     
     public List<Good> list(Category cat) throws SQLException{              
            Connection conn = ConnectionFactory.getDefaultConnection();
           List<Good> list = dao.list(conn, cat);
            conn.close();
            return list;
     }
     
     public void addGood(Good good) throws FileNotFoundException, IOException{
     Connection conn = null;
        try {
            conn = ConnectionFactory.getDefaultConnection();
            PreparedStatement ps = conn.prepareStatement(DaoUtil.getQueryFromProperties("insertGood"));
            ps.setString(1, good.getMaker().getMkrId());
            ps.setInt(2, good.getCatId());
            ps.setString(3, good.getModel());
            ps.setBigDecimal(4, good.getPrice());
            String available;
            if (good.isIsAvailable()){
                available = "y";
            }
            else {
                available = "n";
            }
            ps.setString(5, available);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(GoodJdbcService.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
         try {
             conn.close();
         } catch (SQLException ex) {
             Logger.getLogger(GoodJdbcService.class.getName()).log(Level.SEVERE, null, ex);
         }
        }     
     }
     
     public void delete(int goodId) throws FileNotFoundException, IOException{
         Connection conn = null;
        try { 
            conn = ConnectionFactory.getDefaultConnection();
            dao.deleteGood(conn, goodId);
        } catch (SQLException ex) {
            Logger.getLogger(GoodJdbcService.class.getName()).log(Level.SEVERE, null, ex);
        }
         
     }

    
    public void goodAdded(Good good) {
        try {
            addGood(good);
            System.out.println("Good added to DB: " + good);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GoodJdbcService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GoodJdbcService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    public static void main(String[] args) throws FileNotFoundException, IOException {
//        Good good = new Good();
//        good.setCatId(4);
//        good.setMaker(new Maker("indesit"));
//        good.setIsAvailable(true);
//        good.setModel("fdfds");
//        good.setPrice(BigDecimal.TEN);
//        new GoodJdbcComponent().addGood(good);
//    }
}
