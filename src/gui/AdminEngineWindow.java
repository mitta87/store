/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Peter
 */
public class AdminEngineWindow extends WindowAdapter {

    private AdminGUI guiApp;

    public AdminEngineWindow(AdminGUI guiApp) {
        this.guiApp = guiApp;
    }

    public void windowClosing(WindowEvent e) {
        guiApp.getData().getConnDB().closeDBCon();
    }
}
