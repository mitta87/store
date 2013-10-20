/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.client.gui;

import java.util.Map;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 *
 * @author Митя
 */
public class AppInternalFrameListener extends InternalFrameAdapter{
    
     private Map<String, JInternalFrame> frameMap;
     private String frameName;

    public AppInternalFrameListener(Map<String, JInternalFrame> frameMap, String frameName) {
        this.frameMap = frameMap;
        this.frameName = frameName;
    }
     
     @Override
     public void internalFrameClosing(InternalFrameEvent e){
         frameMap.remove(frameName);         
     }
}
