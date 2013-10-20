/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.client.goodsmanagement;

import main.beans.Good;



/**
 *
 * @author Митя
 */
public interface GoodsListObserver {
    public void listChanged(Good good);    
}
