/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import dao.Category;
import goodsmanagement.GoodsListPanel;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import javax.swing.*;

/**
 *
 * @author Митя
 */
public class NewFrame extends JFrame{
   private  JLabel label;

    public NewFrame() throws HeadlessException {
        super("DB Client");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
         GoodsListPanel goodsListPanel = new GoodsListPanel(new Category(4));         
          getContentPane().add(goodsListPanel, "Center");          
          
    }
    
    private void init(){
        
    }
    
    public static void main(String[] args) {
        new NewFrame().setVisible(true);
    }
    
}
