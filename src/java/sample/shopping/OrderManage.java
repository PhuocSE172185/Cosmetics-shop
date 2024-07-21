/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.shopping;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class OrderManage {
    public String order;
    public String userID;
    public String fullName;
    public Date date;
    public double total;

    public OrderManage() {
    }

    public OrderManage(String order, String userID, String fullName, Date date, double total) {
        this.order = order;
        this.userID = userID;
        this.fullName = fullName;
        this.date = date;
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
}
