/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shop;

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
    
}
    
    

