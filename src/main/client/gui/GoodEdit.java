/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.client.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import main.beans.Category;
import main.beans.Good;
import main.beans.Maker;
import main.business.CategoryJdbcService;
import main.business.GoodJdbcService;
import main.business.MakerJdbcService;
import main.client.AppManager;
import main.client.goodsmanagement.GoodsListObserver;

/**
 *
 * @author Митя
 */
public class GoodEdit extends JDialog {

    private Map<String, JTextField> fields = new HashMap<String, JTextField>();
    private List<GoodsListObserver> observers = new ArrayList<GoodsListObserver>();
    private JPanel fieldsPanel;
    private JButton addButton;
    private JCheckBox checkBox;
    private Category category;
    private JComboBox<Maker> makersBox;
    private JComboBox<Category> catsBox;
    private Good good;
//    JComboBox catsBox;

    public GoodEdit(Category cat) throws FileNotFoundException, IOException {
        this.category = cat;
        setTitle("Adding good");
        init();
        setFieldsPanel();
        System.out.println("Выбрана ктаегория: " + category);
        addContent();
    }

    public GoodEdit(Good good) throws FileNotFoundException, IOException {
        this.good = good;
        setTitle("Editing good");
        init();
        setFieldsPanel();
        initCatComboBox();
        addContent();
        setFields();
    }

    private void setFields() throws FileNotFoundException, IOException {
        makersBox.setSelectedItem(good.getMaker());
        catsBox.setSelectedItem(new Category(good.getCatId()));
        fields.get("Модель").setText(good.getModel());
        fields.get("Цена").setText(good.getPrice().toString());
        if (good.isIsAvailable()) {
            checkBox.setSelected(true);
        }
    }

    private void setFieldsPanel() {
        setLayout(new BorderLayout());
        fieldsPanel = new JPanel();
        getContentPane().add(fieldsPanel, "Center");
        GridLayout gr = new GridLayout(0, 1, 10, 10);
        fieldsPanel.setLayout(gr);
    }

    private void addContent() throws FileNotFoundException, IOException {
        initMakerComboBox();
//        initCatComboBox();
        addField("Модель");
        addField("Цена");
        addCheckBox("Наличие");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        getContentPane().add(buttonPanel, "South");
        addButton = new JButton("Add");
        buttonPanel.add(addButton);
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (good == null) {
                    good = new Good();
                }
//                category = (Category) catsBox.getSelectedItem();
                System.out.println(category);
//                good.setCatId(((Category)catsBox.getSelectedItem()).getCatID());
                if (catsBox != null) {
                    category = (Category) catsBox.getSelectedItem();
                }
                good.setCatId(category.getCatID());
                good.setMaker((Maker) makersBox.getSelectedItem());
                good.setModel(fields.get("Модель").getText());
                String priceInput = fields.get("Цена").getText().trim();
                System.out.println(priceInput);
                String mantissa = "";
                for (int i = 0; i < priceInput.length(); i++) {
                    if (priceInput.charAt(i) != '.') {
                        mantissa += priceInput.charAt(i);
                    }
                }
                System.out.println(mantissa);
                BigDecimal price = new BigDecimal(BigInteger.valueOf(Long.parseLong(mantissa)), 2);
                good.setPrice(price);
                good.setIsAvailable(checkBox.isSelected());
                AppManager.doJob(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            new GoodJdbcService().addGood(good);
                            for (GoodsListObserver observer : observers) {
                                observer.listChanged(good);
                            }
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(GoodEdit.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(GoodEdit.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });                
                setVisible(false);
                dispose();
            }
        });
    }

    private void init() {
        this.setVisible(true);
        this.setModal(true);
        this.setSize(150, 350);
        setLocation(200, 100);
        setLocationRelativeTo(AppManager.getMainFrame());
    }

    private void initMakerComboBox() {
        List<Maker> list = new MakerJdbcService().list();
        makersBox = new JComboBox(list.toArray());
        fieldsPanel.add(new JLabel("Производитель"));
        fieldsPanel.add(makersBox);
    }

    private void initCatComboBox() throws FileNotFoundException, IOException {
        List<Category> list = new CategoryJdbcService().listOfLeafs();
        catsBox = new JComboBox(list.toArray());
        fieldsPanel.add(new JLabel("Категория"));
        fieldsPanel.add(catsBox);
    }

    public void addObserver(GoodsListObserver observer) {
        observers.add(observer);
    }

    private void addField(String fieldName) {
        JLabel label = new JLabel(fieldName);
        fieldsPanel.add(label);
        JTextField field = new JTextField();
        fieldsPanel.add(field);
        fields.put(fieldName, field);
    }

    private void addCheckBox(String fieldName) {
        checkBox = new JCheckBox(fieldName);
        fieldsPanel.add(checkBox);
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
