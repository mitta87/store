/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package goodsmanagement;

import dao.Category;
import dao.Good;
import dao.Maker;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Митя
 */
public class GoodsDialog extends JDialog {

    Map<String, JTextField> fields = new HashMap<String, JTextField>();
    List<GoodsObserver> observers = new ArrayList<GoodsObserver>();
    JPanel fieldsPanel;
    JButton addButton;
    JCheckBox checkBox;
    Category category;
    JComboBox makersBox;
    JComboBox catsBox;

    public GoodsDialog() throws FileNotFoundException, IOException {
        setTitle("Adding good");
        this.setVisible(true);
        this.setModal(true);
        this.setSize(150, 250);
        setLocation(200, 100);
        setLayout(new BorderLayout());
        fieldsPanel = new JPanel();
        getContentPane().add(fieldsPanel, "Center");
        BoxLayout box = new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS);
        fieldsPanel.setLayout(box);
        initMakerComboBox();
        initCatComboBox();
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
                Good good = new Good();
                category = (Category) catsBox.getSelectedItem();
                System.out.println(category);
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
                for (GoodsObserver observer : observers) {
                    observer.goodAdded(good);
                }
                setVisible(false);
                dispose();
            }
        });

    }

    private void initMakerComboBox() {
        List<Maker> list = new MakerJdbcComponent().list();
        makersBox = new JComboBox(list.toArray());
        fieldsPanel.add(new JLabel("Производитель"));
        fieldsPanel.add(makersBox);
    }

    public void initCatComboBox() throws FileNotFoundException, IOException {
        List<Category> list = new CategoryJdbcComponent().listOfLeafs();
        catsBox = new JComboBox(list.toArray());
        fieldsPanel.add(new JLabel("Категория"));
        fieldsPanel.add(catsBox);
    }

    public void addObserver(GoodsObserver observer) {
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
