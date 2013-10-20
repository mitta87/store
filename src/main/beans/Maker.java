/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.beans;

import java.util.Objects;


/**
 *
 * @author Митя
 */
public class Maker implements Comparable<Maker>{

    private String mkrId;
    private String mkrName;

    public Maker() {
    }    
    
    public Maker(String mkrId) {
        this.mkrId = mkrId;
    }

    public Maker(String mkrId, String mkrName) {
        this.mkrId = mkrId;
        this.mkrName = mkrName;
    }
   

    public String getMkrId() {
        return mkrId;
    }

    public String getMkrName() {
        return mkrName;
    }

    public void setMkrId(String mkrId) {
        this.mkrId = mkrId;
    }

    public void setMkrName(String mkrName) {
        this.mkrName = mkrName;
    }

    @Override
    public String toString() {
        return mkrName;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.mkrId);
        hash = 23 * hash + Objects.hashCode(this.mkrName);
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
        final Maker other = (Maker) obj;
        if (!Objects.equals(this.mkrId, other.mkrId)) {
            return false;
        }
        if (!Objects.equals(this.mkrName, other.mkrName)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Maker t) {
       return this.mkrName.compareTo(t.getMkrName());
    }            
}
