/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.util.ConnectionFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Митя
 */
public class CategoryDao extends Dao{   

    public List<Category> listOfLeafs(Connection conn) throws SQLException, FileNotFoundException, IOException{
        List<Category> list = new ArrayList<Category>();
        PreparedStatement ps = conn.prepareStatement(getQueryFromProperties("listOfLeafs"));
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Category category = new Category();
            category.setCatID(rs.getInt(1));
            category.setCatName(rs.getString(2));
            list.add(category);
        }
        return list;
    }   

}
