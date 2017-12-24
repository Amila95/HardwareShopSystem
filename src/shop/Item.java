/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shop;

/**
 *
 * @author Acer
 */
public class Item {
    private int itemID;
    private String name;
    private double price;
    private int qty;
    private int stock;
    private int monthstock;
    private int daliystock;
    private double totalprice;

    /**
     * @return the itemID
     */
    public int getItemID() {
        return itemID;
    }

    /**
     * @param itemID the itemID to set
     */
    public void setItemID(int itemID) {
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
    
}
