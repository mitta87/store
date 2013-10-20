/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.business;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import main.beans.Maker;
import main.dao.ConnectionFactory;
import main.dao.MakerDao;


/**
 *
 * @author Митя
 */
public class MakerJdbcService {   
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
