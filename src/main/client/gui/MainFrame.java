/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.client.gui;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;



/**
 *
 * @author Митя
 */
public class MainFrame extends JFrame {

    private JDesktopPane desktop = new JDesktopPane();
    private JMenuBar menuBar = new JMenuBar();
    private Map<String, JInternalFrame> frameMap = new HashMap<>();

    public MainFrame() {
        super("Store client");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JMenu mainMenu = new JMenu("Меню");
        JMenuItem goodsMenu = new JMenuItem("Товары");
        goodsMenu.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                String frameName = "GoodsFrame";                
               JInternalFrame goodsFrame = frameMap.get(frameName);
               if (goodsFrame == null){                  
                  System.out.println(frameName);
                   goodsFrame = new GoodsFrame();
                   desktop.add(goodsFrame);
                   frameMap.put(frameName, goodsFrame);
                   goodsFrame.addInternalFrameListener(new AppInternalFrameListener(frameMap, frameName));
                    try {
                        goodsFrame.setMaximum(true);
                    } catch (PropertyVetoException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    SwingUtilities.invokeLater(new Runnable() {

                      @Override
                      public void run() {
                       MainFrame.this.frameMap.get("GoodsFrame").setVisible(true);    
                      }
                  });
                                     
                   
               }
            }
        });
        getContentPane().add(desktop);
        mainMenu.add(goodsMenu);
        menuBar.add(mainMenu);
        setJMenuBar(menuBar);
        setVisible(true);
    }   
  }
