/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.beans;

import java.util.Objects;
import old.dao.*;

/**
 *
 * @author Peter
 */
public class Category {

    public Category() {
    }

    public Category(String catName) {
        this.catName = catName;
    }

    public Category(int catID) {
        this.catID = catID;
    }
//    поля таблицы
//int CAT_ID, varchar CAT_NAME, int PARENT_CAT_ID
//поля класса бина
//catID, catName, parentCatID
    private int catID;
    private String catName;
    private int parentCatID;

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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.catID;
        hash = 89 * hash + Objects.hashCode(this.catName);
        hash = 89 * hash + this.parentCatID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Category other = (Category) obj;
        if (this.catID != other.catID) {
            return false;
        }        
        return true;
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
    
//    public String toString() {
//        return getCatName();
//    }

    @Override
    public String toString() {
        return catName;
    }
}
