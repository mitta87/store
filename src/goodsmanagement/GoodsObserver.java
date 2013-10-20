/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package goodsmanagement;

import dao.Good;

/**
 *
 * @author Митя
 */
public interface GoodsObserver {
    public void goodAdded(Good good);    
}
