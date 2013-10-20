/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.business;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import main.beans.Category;
import main.dao.CategoryDao;
import main.dao.ConnectionFactory;

/**
 *
 * @author Митя
 */
public class CategoryJdbcService {
  private CategoryDao dao = new CategoryDao(); 
    
    public List<Category> listOfLeafs() throws FileNotFoundException, IOException {
        Connection conn = null;
        List<Category> list = null;
        try {
            conn = ConnectionFactory.getDefaultConnection();
            list = dao.listOfLeafs(conn);
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
