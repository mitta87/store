/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.client.tree;

import com.sybase.jdbc4.jdbc.SybDataSource;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.swing.DefaultListModel;
import javax.swing.tree.DefaultMutableTreeNode;
import main.beans.Category;
import main.beans.Good;

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
//            connDB = DriverManager.getConnection("jdbc:derby://localhost:1527/Project");
             SybDataSource ds = new SybDataSource();        
        ds.setServerName("127.0.0.1");
        ds.setPortNumber(5000);
        ds.setDatabaseName("store");
        ds.setUser("sa");
        ds.setPassword("");
        connDB = ds.getConnection();
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

    public Queue<Category> readTreeCategory() {
        Statement stmt = null;
        ResultSet rs = null;
        Queue<Category> catQ = new LinkedList<Category>();
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
            Statement stmnt = connDB.createStatement();
            System.out.println(queryIns);
            stmnt.executeUpdate(queryIns);
            stmnt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editCategoryInDB(String queryIns) {
        try {
            Statement stmnt = connDB.createStatement();
            System.out.println(queryIns);
            stmnt.executeUpdate(queryIns);
            stmnt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeCategoryFromDB(String queryIns) {
        try {
            Statement stmnt = connDB.createStatement();
            System.out.println(queryIns);
            stmnt.executeUpdate(queryIns);
            stmnt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    

    public void addGoodToDB(String queryAdd) {
        try {
            Statement stmnt = connDB.createStatement();
            System.out.println(queryAdd);
            stmnt.executeUpdate(queryAdd);
            stmnt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
