/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

/**
 *
 * @author Peter
 */
public class DBConnection {
    
    private Connection connDB;
    
    public DBConnection() {
        //Connection operation
        try {
//            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connDB = ConnectionFactory.getDefaultConnection();
        } catch (SQLException se) {
            System.out.println("SQLError: " + se.getMessage()
                    + " code: " + se.getErrorCode());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @return the connDB
     */
    public Connection getConnDB() {
        return connDB;
    }

    /**
     * @param connDB the connDB to set
     */
    public void setConnDB(Connection connDB) {
        this.connDB = connDB;
    }
    
    public LinkedList<Category> readTreeCategory() {
        Statement stmt = null;
        ResultSet rs = null;
        LinkedList<Category> catQ = new LinkedList<Category>();
        try {
            String sqlQuery = "SELECT * from Category";
            stmt = getConnDB().createStatement();
            rs = stmt.executeQuery(sqlQuery);
            // read all in queue
            while (rs.next()) {
                Category currentCat = new Category();
                currentCat.setCatID(rs.getInt("CAT_ID"));
                currentCat.setCatName(rs.getString("CAT_NAME"));
                currentCat.setParentCatID(rs.getInt("PARENT_CAT_ID"));
                System.out.println(currentCat);
                catQ.add(currentCat);
            }
        } catch (SQLException se) {
            System.out.println("SQLError: " + se.getMessage()
                    + " code: " + se.getErrorCode());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    return catQ;
    }
    
    public void closeDBCon() {
        try {
            connDB.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Finally!");
    }
    
    public void addCategoryToDB(String queryIns) {
        try {
            Statement ccc = connDB.createStatement();
            System.out.println(queryIns);
            ccc.executeUpdate(queryIns);
            ccc.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
