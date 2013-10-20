package main.beans;

import java.math.BigDecimal;

public class Good {

    private int id;
    private Maker maker;
    private int catId;
    private String model;
    private BigDecimal price;
    private String image;
    private String info;
    private boolean isAvailable;

    public int getId() {
        return id;
    }

    public Maker getMaker() {
        return maker;
    }

    public int getCatId() {
        return catId;
    }

    public String getModel() {
        return model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getInfo() {
        return info;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMaker(Maker maker) {
        this.maker = maker;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
       return String.format("%s %s, цена:  %s грн., на складе: %s", maker.getMkrName(), model, price, isAvailable); 
    }
    
    
}
