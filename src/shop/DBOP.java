/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shop;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Acer
 */
public class DBOP {
    
    void seachitem(Item I){
    try{
        Statement s = Database.getStatement();
        String pqr = ("SELECT * FROM item WHERE item_id ='"+I.getItemID()+"'");
        ResultSet rs = s.executeQuery(pqr);
        while(rs.next()){
            I.setName(rs.getString("item_name"));
            I.setPrice(Integer.parseInt(rs.getString("item_price")));
        }
    }
    catch(Exception ex){
            ex.printStackTrace();
            }
    }
    void settotalcount(Item I){
    try{
        Statement s = Database.getStatement();
        String pq = ("SELECT item_quantity FROM item WHERE item_id ='"+I.getItemID()+"'");
        ResultSet rs = s.executeQuery(pq);
        int t=0;
            while(rs.next()){
            t =rs.getInt(1);
            }
            int count1 = t - I.getQty();
            I.setStock(count1);
            //I.setStock(Integer.parseInt(rs.getString("Count")));
            s.executeUpdate("UPDATE item set item_quantity ='"+I.getStock()+"' WHERE item_id='"+I.getItemID()+"'");
            //s.executeUpdate("UPDATE patientdetails set Total='"+pe.getTotal()+"' WHERE p_id='"+p.getPatientId()+"' ");
        
    }catch(Exception ex){
        ex.printStackTrace();
    }
    }
    void cansalepayment(Item I){
        try{
        Statement s = Database.getStatement();
        String pq = ("SELECT item_quantity FROM item WHERE item_id ='"+I.getItemID()+"'");
        ResultSet rs = s.executeQuery(pq);
        int t=0;
            while(rs.next()){
            t =rs.getInt(1);
            }
            int count1 = t + I.getQty();
            I.setStock(count1);
            //I.setStock(Integer.parseInt(rs.getString("Count")));
            s.executeUpdate("UPDATE item set item_quantity ='"+I.getStock()+"' WHERE item_id='"+I.getItemID()+"'");
            //s.executeUpdate("UPDATE patientdetails set Total='"+pe.getTotal()+"' WHERE p_id='"+p.getPatientId()+"' ");
        
    }catch(Exception ex){
        ex.printStackTrace();
    }
        
    }
    void upadatedailypayment(Item I){
        try{
            
            Statement s = Database.getStatement();
            String pqr = ("SELECT * FROM daily WHERE item_id ='"+I.getItemID()+"' AND date='"+LocalDate.now()+"'");
            ResultSet rs = s.executeQuery(pqr);
            if(rs.next()){
                int qty = Integer.parseInt(rs.getString("d_quantity"))+I.getQty();
                s.executeUpdate("UPDATE daily set d_quantity ='"+qty+"' WHERE item_id ='"+I.getItemID()+"' AND date='"+LocalDate.now()+"'");
            }
            else{
                s.executeUpdate("INSERT INTO daily (item_id,date,d_quantity) VALUES ('"+I.getItemID()+"','"+LocalDate.now()+"','"+I.getQty()+"')");
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    void  payment(Item I){
    try{
        Statement s = Database.getStatement();
        s.executeUpdate("INSERT INTO bill(amount,discount,total_price,cash,change_amount) VALUES ('"+I.getAmount()+"','"+I.getDiscount()+"','"+I.getTotal_price()+"','"+I.getCash()+"','"+I.getChange_amount()+"')");
    }catch(Exception e){
        e.printStackTrace();
    }
    }
    
    ResultSet getlastid() {
        try{
        Statement s = Database.getStatement();
        String p = "SELECT bill_id FROM bill ORDER BY bill_id DESC LIMIT 1";
        ResultSet rs = s.executeQuery(p);
        return rs;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
        
        } 
    ResultSet getbillitem(int bill_id){
        try{
        Statement s = Database.getStatement();
        String p ="SELECT bill_item.bill_id,bill_item.item_id,bill_item.quantity ,item.item_name,item.item_price FROM bill_item INNER JOIN item ON bill_item.item_id=item.item_id WHERE bill_item.bill_id='"+ bill_id+"'";
        ResultSet rs = s.executeQuery(p);
        return rs;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    
    }
    void setbillitem(int bill_id, int item_id, int qty){
        try{
            Statement s = Database.getStatement();
            s.executeUpdate("INSERT INTO bill_item (bill_id,Item_id,quantity) VALUES ('"+bill_id+"','"+item_id+"','"+qty+"')");
        }catch(Exception e){
            e.printStackTrace();
        }
    
    }
    /*void bill(Item I){
        try{
        Statement s = Database.getStatement();
        s.executeUpdate("INSERT INTO bill (id,qty) VALUES ('"+I.getItemID()+"','"+I.getQty()+"')");
        }catch(Exception e){
        e.printStackTrace();
        }
    }
    
    void deletebill(Item I){
    try{
        String sql=" truncate bill ";
             Statement s = Database.getStatement();
             s.executeUpdate(sql);
    }catch(Exception e){
        e.printStackTrace();
    }
    }
    ResultSet getprint(){
    try{
        Item I = new Item();
        Statement s = Database.getStatement();
        String pqr = ("SELECT item.item_name, item.item_price, bill.qty FROM item INNER JOIN bill ON bill.id=item.item_id  ");
        //String sql="SELECT daily.item_id AS 'ItemID',item.item_name AS 'Name',`d_quantity` AS 'Sold Quantity' FROM `daily` INNER JOIN item ON daily.item_id=item.item_id WHERE `date`= '"+date+"'";
        
        ResultSet rs = s.executeQuery(pqr);
        return rs;
        
        
    }catch(Exception e){
        e.printStackTrace();
    }
        return null;
    }*/
    
    //SELECT bill_item.bill_id,bill_item.item_id,bill_item.quantity ,item.item_name,item.item_price FROM bill_item INNER JOIN item ON bill_item.item_id=item.item_id WHERE bill_item.bill_id=1;
    
    
    
}
    
    

