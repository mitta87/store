/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import goodsmanagement.GoodsListPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;

/**
 *
 * @author Peter
 */
public class AdminGUI {

    private JPanel mainPanel;
    private JTree jT;
    private DataModel data;

    public AdminGUI(DataModel data) {
        //Connection operation
        //init some date
        this.data = data;
        //Panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        
        JPanel treePanel = new JPanel(new BorderLayout());
        jT = new JTree(data.getRoot());
        JScrollPane scp = new JScrollPane(getjT());
        treePanel.add(scp, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new GridLayout(0, 3));
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");
        JButton editButton = new JButton("Edit");
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(editButton);
        treePanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(treePanel, BorderLayout.CENTER);
//        JTextField tf = new JTextField("FUTURE");        
//        mainPanel.add(tf, BorderLayout.EAST);
        GoodsListPanel goodsListPanel = new GoodsListPanel();
        mainPanel.add(goodsListPanel, "East");
        // Frame
        JFrame frame = new JFrame("FileTreeDemo");
        frame.getContentPane().add(mainPanel, "Center");
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Listeners
        frame.addWindowListener(new AdminEngineWindow(this));
        jT.addTreeSelectionListener(new AdminEngine(this));
        addButton.addActionListener(new AdminEngine(this));
    }

    public void addNode(Object obj, String name){
        data.addCategory(obj, name);
        getjT().updateUI();
    }

    public static void main(String[] args) {
        new AdminGUI(new DataModel(new DBConnection()));
    }

    /**
     * @return the data
     */
    public DataModel getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(DataModel data) {
        this.data = data;
    }

    /**
     * @return the jT
     */
    public JTree getjT() {
        return jT;
    }
}
