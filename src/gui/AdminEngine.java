/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Peter
 */
public class AdminEngine implements TreeSelectionListener, ActionListener {

    private AdminGUI guiApp;

    public AdminEngine(AdminGUI guiApp) {
        this.guiApp = guiApp;
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        System.out.println(((JTree)e.getSource()).getLastSelectedPathComponent());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Add ON!");
        Object node = guiApp.getjT().getLastSelectedPathComponent();
        String name = (String)JOptionPane.showInputDialog("Enter category name", "hmm...");
        guiApp.addNode(node, name);
        System.out.println(node);
        // debug print
        System.out.println(
                ((Category)((DefaultMutableTreeNode)guiApp.getjT().getLastSelectedPathComponent()).getUserObject()).getCatID());
    }
}
