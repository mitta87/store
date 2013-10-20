/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package goodsmanagement;

import dao.Good;
import dao.GoodDao;
import dao.Maker;
import dao.MakerDao;
import dao.util.ConnectionFactory;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Митя
 */
public class MakerJdbcComponent {   
    private MakerDao dao = new MakerDao(); 
    
    public List<Maker> list() {
        Connection conn = null;
        List<Maker> list = null;
        try {
            conn = ConnectionFactory.getDefaultConnection();
            list = dao.list(conn);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally{
            try{
                conn.close();
            } catch(Exception ex){}
        }
        return list;
    }
}
