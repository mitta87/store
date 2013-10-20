/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.client;

import javax.swing.SwingUtilities;
import main.client.gui.MainFrame;

/**
 *
 * @author Митя
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       AppManager.init(new MainFrame(), new JdbcServiceFactory());
        SwingUtilities.invokeLater(new Runnable() {

           @Override
           public void run() {
           AppManager.getMainFrame().setVisible(true);    
           }
       });         
    }
}
