/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author Peter
 */
public class Category {
//    поля таблицы
//int CAT_ID, varchar CAT_NAME, int PARENT_CAT_ID
//поля класса бина
//catID, catName, parentCatID
    private int catID;
    private String catName;
    private int parentCatID;
    
    public Category() {}

    public Category(int catID, String catName, int parentCatID) {
        this.catID = catID;
        this.catName = catName;
        this.parentCatID = parentCatID;
    }

    /**
     * @return the catID
     */
    public int getCatID() {
        return catID;
    }

    /**
     * @param catID the catID to set
     */
    public void setCatID(int catID) {
        this.catID = catID;
    }

    /**
     * @return the catName
     */
    public String getCatName() {
        return catName;
    }

    /**
     * @param catName the catName to set
     */
    public void setCatName(String catName) {
        this.catName = catName;
    }

    /**
     * @return the parentCatID
     */
    public int getParentCatID() {
        return parentCatID;
    }

    /**
     * @param parentCatID the parentCatID to set
     */
    public void setParentCatID(int parentCatID) {
        this.parentCatID = parentCatID;
    }
    
    public String toString() {
        return getCatName();
    }
}
