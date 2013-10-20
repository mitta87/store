/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.Enumeration;
import java.util.Queue;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Peter
 */
public class DataModel {

    private DefaultMutableTreeNode root;
    private int maxID;
    private DBConnection connDB;

    public DataModel(DBConnection connDB) {
        this.connDB = connDB;
        InitTree();
    }

    /**
     * @return the root
     */
    public DefaultMutableTreeNode getRoot() {
        return root;
    }

    /**
     * @param root the root to set
     */
    public void setRoot(DefaultMutableTreeNode root) {
        this.root = root;
    }

    /**
     * @return the maxID
     */
    public int getMaxID() {
        return maxID;
    }

    /**
     * @param maxID the maxID to set
     */
    public void setMaxID(int maxID) {
        this.maxID = maxID;
    }

    /**
     * @return the connDB
     */
    public DBConnection getConnDB() {
        return connDB;
    }

    /**
     * @param connDB the connDB to set
     */
    public void setConnDB(DBConnection connDB) {
        this.connDB = connDB;
    }

    final public void InitTree() {
        // read all category table preconstuct tree
        Queue<Category> catQ = connDB.readTreeCategory();
        // finding maximum category ID
        for (Category currentCat : catQ) {
            if (getMaxID() < currentCat.getCatID()) {
                setMaxID(currentCat.getCatID());
            }
        }
        //construct tree
        // find root
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
    }

    public void addCategory(Object obj, String name) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) obj;
        maxID += 1;
        Category catNew = new Category(
                maxID, name, ((Category) node.getUserObject()).getCatID());
        node.add(new DefaultMutableTreeNode(catNew));
        // Create query;
        String query = String.format("insert  category select %s, %s, %s",
                catNew.getCatID(), catNew.getParentCatID(), catNew.getCatName());
        connDB.addCategoryToDB(query);
    }
}
