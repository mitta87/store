/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.client;

import javax.swing.JFrame;
import main.client.gui.LoadingWindow;


public class AppManager {
    
    private static JFrame mainFrame = null;
    private static JdbcServiceFactory serviceFactory = null;
    private static LoadingWindow loadingWindow = null;

    public static void init(JFrame frame, JdbcServiceFactory factory) {
        mainFrame = frame;
       serviceFactory = factory;
        loadingWindow = new LoadingWindow(mainFrame);
    }

    public static JFrame getMainFrame() {
        return mainFrame;
    }

    public static JdbcServiceFactory getServiceFactory() {
        return serviceFactory;
    }    
    
    public static void doJob(Runnable job){
        loadingWindow.doJob(job);
    }
    
}
