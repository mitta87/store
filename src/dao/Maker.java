/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author Митя
 */
public class Maker {

    private String mkrId;
    private String mkrName;

    public Maker(String mkrId) {
        this.mkrId = mkrId;
    }

    public Maker(String mkrId, String mkrName) {
        this.mkrId = mkrId;
        this.mkrName = mkrName;
    }

    Maker() {
        
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
}
