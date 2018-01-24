/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shop;

import java.sql.ResultSet;
import javax.swing.JTextField;



/**
 *
 * @author Acer
 */
public class Item {
    private String itemID;
    private String name;
    private double price;
    private int qty;
    private int stock;
    private int monthstock;
    private int daliystock;
    private double totalprice;
    private String date;
    private double amount;
    private double discount;
    private double total_price;
    private double cash;
    private double change_amount;
    private int bill_id;
    

    /**
     * @return the itemID
     */
    public String getItemID() {
        return itemID;
    }

    /**
     * @param itemID the itemID to set
     */
    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the qty
     */
    public int getQty() {
        return qty;
    }

    /**
     * @param qty the qty to set
     */
    public void setQty(int qty) {
        this.qty = qty;
    }

    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return the monthstock
     */
    public int getMonthstock() {
        return monthstock;
    }

    /**
     * @param monthstock the monthstock to set
     */
    public void setMonthstock(int monthstock) {
        this.monthstock = monthstock;
    }

    /**
     * @return the daliystock
     */
    public int getDaliystock() {
        return daliystock;
    }

    /**
     * @param daliystock the daliystock to set
     */
    public void setDaliystock(int daliystock) {
        this.daliystock = daliystock;
    }

    /**
     * @return the totalprice
     */
    public double getTotalprice() {
        return totalprice;
    }

    /**
     * @param totalprice the totalprice to set
     */
    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @return the discount
     */
    public double getDiscount() {
        return discount;
    }

    /**
     * @param discount the discount to set
     */
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    /**
     * @return the total_price
     */
    public double getTotal_price() {
        return total_price;
    }

    /**
     * @param total_price the total_price to set
     */
    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    /**
     * @return the cash
     */
    public double getCash() {
        return cash;
    }

    /**
     * @param cash the cash to set
     */
    public void setCash(double cash) {
        this.cash = cash;
    }

    /**
     * @return the change_amount
     */
    public double getChange_amount() {
        return change_amount;
    }

    /**
     * @param change_amount the change_amount to set
     */
    public void setChange_amount(double change_amount) {
        this.change_amount = change_amount;
    }

    void setTotalprice(JTextField price) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void setItemID(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the bill_id
     */
    public int getBill_id() {
        return bill_id;
    }

    /**
     * @param bill_id the bill_id to set
     */
    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }
    
}
