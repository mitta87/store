/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Peter
 */
public class ProjectPrivat {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        Queue<Category> catQ = new LinkedList<Category>();
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Project");
            String sqlQuery = "SELECT * from Category";
            stmt = conn.createStatement();
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
// clean up the system resources
            try {
                rs.close();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // creating tree
        // creating root node
        DefaultMutableTreeNode root = null;
        while (root == null) {
            Category curCat = catQ.poll();
            if (curCat.getParentCatID() == 0) {
                root = new DefaultMutableTreeNode(curCat);
            } else {
                catQ.add(curCat);
            }
        }
        // check root
        System.out.println();
        System.out.println("Root is " + root);
        // filling tree
        a:
        while (!catQ.isEmpty()) {
            Category curCat = catQ.poll();
            Category seaCat = null;
            DefaultMutableTreeNode seaNode = null;
            int parentCID = curCat.getParentCatID();
            Enumeration path = root.breadthFirstEnumeration();
            while (path.hasMoreElements()) {
                seaNode = (DefaultMutableTreeNode) path.nextElement();
                seaCat = (Category) seaNode.getUserObject();
                if (seaCat.getCatID() == parentCID) {
                    DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(curCat);
                    //newNode.setParent(seaNode);
                    seaNode.add(newNode);
                    continue a;
                }
            }
            catQ.add(curCat);
        }
        // check tree prining it depth order
        {
            System.out.println();
            Enumeration path = root.depthFirstEnumeration();
            while (path.hasMoreElements()) {
                System.out.println((DefaultMutableTreeNode) path.nextElement());
            }
        }
        // Display
        JTree jT = new JTree(root);
        JScrollPane scp = new JScrollPane(jT);
        JFrame frame = new JFrame("FileTreeDemo");
        frame.getContentPane().add(scp, "Center");
        frame.setSize(400, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
