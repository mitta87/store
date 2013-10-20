/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package goodsmanagement;

import dao.Good;
import dao.GoodDao;
import dao.Maker;
import dao.util.ConnectionFactory;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Митя
 */
public class GoodJdbcComponent implements GoodsObserver{
    
    private GoodDao dao = new GoodDao();
    
     public List<Good> list() {
        Connection conn = null;
        List<Good> list = null;
        try {
            conn = ConnectionFactory.getDefaultConnection();
            list = dao.list(conn);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GoodJdbcComponent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GoodJdbcComponent.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try{
                conn.close();
            } catch(Exception ex){}
        }
        return list;
    }
     
     public void addGood(Good good) throws FileNotFoundException, IOException{
     Connection conn = null;
        try {
            conn = ConnectionFactory.getDefaultConnection();
            PreparedStatement ps = conn.prepareStatement(dao.getQueryFromProperties("insertGood"));
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
            Logger.getLogger(GoodJdbcComponent.class.getName()).log(Level.SEVERE, null, ex);
        }
     
     }

    @Override
    public void goodAdded(Good good) {
        try {
            addGood(good);
            System.out.println("Good added to DB: " + good);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GoodJdbcComponent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GoodJdbcComponent.class.getName()).log(Level.SEVERE, null, ex);
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
