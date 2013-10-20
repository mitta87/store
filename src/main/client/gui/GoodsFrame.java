/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.client.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import main.beans.Category;
import main.beans.Good;
import main.business.GoodJdbcService;
import main.client.AppManager;
import main.client.goodsmanagement.GoodsListObserver;
import main.client.tree.DBConnection;
import main.client.tree.DataModel;

/**
 *
 * @author Митя
 */
public class GoodsFrame extends JInternalFrame implements GoodsListObserver{

    private JScrollPane pane;
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton refreshButton;
    private JPanel buttonPanel;
    private GoodsListModel model;
    private JList goodsList;
    private GoodEdit dialog;
    private JPanel goodsPanel;
    private JPanel treePanel;
    GoodJdbcService jdbcService = new GoodJdbcService();
    private Category cat;
    //tree components
    private JTree jT;
    private DataModel data = new DataModel(new DBConnection());
    private JPanel catButtonPanel;
    private JButton addCatButton;
    private JButton removeCatButton;
    private JButton editCatButton;
    

    public GoodsFrame() {
        super("Goods List", true, true, true, true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initGoodsPanel();
        initTreePanel();
        add(goodsPanel, "Center");
        add(treePanel, "West");
        initListeners();
    }

    private void initTreePanel() {
        treePanel = new JPanel(new BorderLayout());
        jT = new JTree(data.getRoot());
        JScrollPane scp = new JScrollPane(jT);
        treePanel.add(scp, BorderLayout.CENTER);
        catButtonPanel = new JPanel(new  FlowLayout());
        addCatButton = new JButton("Add");
        removeCatButton = new JButton("Remove");
        editCatButton = new JButton("Edit");
       catButtonPanel.add(addCatButton);
       catButtonPanel.add(removeCatButton);
       catButtonPanel.add(editCatButton);
       treePanel.add(catButtonPanel, "South");       
    }

    private void initGoodsPanel() {
        goodsPanel = new JPanel();
        goodsPanel.setLayout(new BorderLayout());
        goodsList = new JList();
        goodsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        pane = new JScrollPane(goodsList);
        goodsPanel.add(pane, "Center");
        buttonPanel = new JPanel(new FlowLayout());
        goodsPanel.add(buttonPanel, "South");
        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");
        updateButton = new JButton("Edit");
        refreshButton = new JButton("Refresh");
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);
        buildGoodsListModel();


    }

    public void buildGoodsListModel() {
        AppManager.doJob(new Runnable() {
            @Override
            public void run() {
                try {
                    if (cat == null) {
                        model = new GoodsListModel();
                        goodsList.setModel(model);
                    } else {
                        model = new GoodsListModel(cat);
                        goodsList.setModel(model);
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(GoodsFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void initListeners() {
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                AppManager.doJob(new Runnable() {
                    @Override
                    public void run() {
                        try {                            
                            dialog = new GoodEdit(cat);
                            dialog.addObserver(GoodsFrame.this);
                            dialog.setVisible(true);
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(GoodsFrame.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(GoodsFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    System.out.println("delete");
                    Good removed = (Good) goodsList.getSelectedValue();
                    System.out.println(removed);
                    jdbcService.delete(removed.getId());
                    model.removeElement(removed);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(GoodsFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(GoodsFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                buildGoodsListModel();
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                final Good selected = (Good) goodsList.getSelectedValue();
                if (selected == null) {
                    JOptionPane.showMessageDialog(AppManager.getMainFrame(),
                            "Please, select the good to edit!");
                } else {
                    AppManager.doJob(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                GoodEdit editWindow = new GoodEdit(selected);
                                editWindow.setVisible(true);
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(GoodsFrame.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(GoodsFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                    });
                }
            }
        });
        jT.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent tse) {
                Object node = jT.getLastSelectedPathComponent();
                if (((DefaultMutableTreeNode) node).isLeaf()) {
                    System.out.println("Leaf is selected");
                    DefaultMutableTreeNode catNode = (DefaultMutableTreeNode) jT.getLastSelectedPathComponent();
                    cat = (Category) catNode.getUserObject();
                    buildGoodsListModel();
                } else {
                    System.out.println("Node selected");
                }
                System.out.println(jT.getLastSelectedPathComponent());
            }
        });
    }

    @Override
    public void listChanged(Good good) {
        buildGoodsListModel();
    }
}
