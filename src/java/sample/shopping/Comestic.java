/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.shopping;

/**
 *
 * @author HoangNQ
 */
public class Comestic {
    private String id;
    private String name;
    private double price;
    private int quantity;
    private String img;
    private boolean Status;

    public Comestic() {
    }

    public Comestic(String id, String name, double price, int quantity, String img, boolean Status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.img = img;
        this.Status = Status;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    
}
