/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package goodsmanagement;

import dao.Category;
import dao.Good;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Митя
 */
public class GoodsListPanel extends JPanel implements GoodsObserver{

    private JScrollPane pane;
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JPanel buttonPanel;
    private GoodsListModel model;
    private JList goodsList;
    private GoodsDialog dialog;
    GoodJdbcComponent jdbcComp;

    public GoodsListPanel() {
        super();
//        setSize(400, 600);
        setLayout(new BorderLayout());
        goodsList = new JList();
        goodsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        pane = new JScrollPane(goodsList);
        add(pane, "Center");
        buttonPanel = new JPanel(new FlowLayout());
        add(buttonPanel, "South");
        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");
        updateButton = new JButton("Edit");
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        model = new GoodsListModel();
        goodsList.setModel(model);
        initListeners();

    }

    public GoodsListPanel(Category cat) {
        this();
        model = new GoodsListModel(cat);
        goodsList.setModel(model);


    }

    private void initListeners() {
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {                    
                    dialog = new GoodsDialog();
                    dialog.addObserver(GoodsListPanel.this);
                    jdbcComp = new GoodJdbcComponent();
                    dialog.addObserver(jdbcComp);
                    dialog.setVisible(true);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(GoodsListPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(GoodsListPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @Override
    public void goodAdded(Good good) {
       model.addElement(good);
        System.out.println("added " + good);
    }
}
