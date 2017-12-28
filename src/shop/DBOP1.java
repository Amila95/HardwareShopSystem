/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shop;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
/**
 *
 * @author pasindu
 */
public class DBOP1 {

    /**
     *
     */
    
     String cursql=""; //use for report genarating
     void addItem(Item I){
        try{
            Statement s = Database.getStatement();
            String pqr = ("INSERT INTO `item` (`item_id`, `item_name`, `item_price`) VALUES ('"+I.getItemID()+"', '"+I.getName()+"', '"+I.getPrice()+"') ");
            s.executeUpdate(pqr);
            JOptionPane.showMessageDialog(null, "Item Added!");
           
        }
        catch(Exception ex){
                ex.printStackTrace();
        }
    }
     
    
     
     void updateItem(String oid,String nid,String name,double price){
         try {
             String sql= "UPDATE `item` SET `item_name` = '" + name + "',item_price = '" + price + "',item_id= '" + nid + "' WHERE `item`.`item_id` = '" + oid + "'; ";
             Statement s = Database.getStatement();
             s.executeUpdate(sql);
             JOptionPane.showMessageDialog(null, "Successfully updated!");
         } catch (Exception e) {
             e.printStackTrace();
         }

     }
     
     ResultSet showItems(String click){
         String sql= "SELECT * FROM item WHERE item_id = '" + click + "' ";
         try {
             Statement s = Database.getStatement();
             ResultSet rs = s.executeQuery(sql);
             return rs;
         } catch (Exception e) {
             e.printStackTrace();
         }
         return null;
     }
     
     void deleteItem(String id){
         try {
             String sql=" DELETE FROM `item` WHERE `item`.`item_id` = '"+id+"' ";
             Statement s = Database.getStatement();
             s.executeUpdate(sql);
             JOptionPane.showMessageDialog(null, "Successfully Deleted!");
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
     
     ResultSet getItemData(){
        String sql="SELECT item_id AS 'Item Id', item_name AS 'Item Name',item_price AS 'Item Price' FROM item";        
         try {
             Statement s = Database.getStatement();
             ResultSet rs = s.executeQuery(sql);
             return rs;
         } catch (Exception e) {
             e.printStackTrace();
         }
         return null;
     }
     
     ResultSet searchItem(String name){
         String sql="SELECT item_id AS 'Item Id', item_name AS 'Item Name',item_price AS 'Item Price' FROM `item` WHERE item_name LIKE '%"+name+"%' OR `item_id` LIKE '%"+name+"%' ";
         
         try {
             
             Statement s = Database.getStatement();
             ResultSet rs = s.executeQuery(sql);
             return rs;
             
         } catch (Exception e) {
            e.printStackTrace();
         }
         return null;
         
     }
     //addStock db operations
     
       ResultSet getStockData(){
        String sql="SELECT item_id AS 'Item Id', item_name AS 'Item Name',item_price AS 'Item Price', item_quantity AS 'Current Stock' FROM item";        
         try {
             Statement s = Database.getStatement();
             ResultSet rs = s.executeQuery(sql);
             return rs;
         } catch (Exception e) {
             e.printStackTrace();
         }
         return null;
     }
       
       ResultSet searchStock(String name){
         String sql="SELECT item_id AS 'Item Id', item_name AS 'Item Name',item_price AS 'Item Price', item_quantity AS 'Current Stock' FROM `item` WHERE item_name LIKE '%"+name+"%' OR `item_id` LIKE '%"+name+"%' ";
         
         try {
             
             Statement s = Database.getStatement();
             ResultSet rs = s.executeQuery(sql);
             return rs;
             
         } catch (Exception e) {
            e.printStackTrace();
         }
         return null;
         
     }
       
       void addStock(String id,int value){
           String sql="UPDATE `item` SET `item_quantity` = '"+value+"' WHERE `item`.`item_id` = '"+id+"' ";
           try {
               Statement s = Database.getStatement();
               s.executeUpdate(sql);
               JOptionPane.showMessageDialog(null, "Stock Added!");
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
       
       //report genaring
       
       ResultSet getReport(String from,String to){
           String sql="SELECT daily.item_id  ,item.item_name AS 'Name', SUM(`d_quantity`) AS 'Sold Quantity' FROM `daily` INNER JOIN item on daily.item_id=item.item_id WHERE daily.date BETWEEN '"+from+"' AND '"+to+"' GROUP by `item_id`";
           try {
               Statement s = Database.getStatement();
               ResultSet rs = s.executeQuery(sql);
               cursql=sql;
               return rs;
           } catch (Exception e) {
               e.printStackTrace();
           }
         return null;
       }
       
       ResultSet getOneReport(String date){
           String sql="SELECT daily.item_id AS 'ItemID',item.item_name AS 'Name',`d_quantity` AS 'Sold Quantity' FROM `daily` INNER JOIN item ON daily.item_id=item.item_id WHERE `date`= '"+date+"'";
           try {
               Statement s = Database.getStatement();
               ResultSet rs = s.executeQuery(sql);
               cursql=sql;
               return rs;
           } catch (Exception e) {
               e.printStackTrace();
           }
         return null;
       }
       
       ResultSet reportSearch(int seflag,String word,String from,String to,String date){
           
           if (seflag==1) {
               String sql="SELECT daily.item_id,item.item_name AS 'Name', SUM(`d_quantity`) AS 'Sold Quantity' FROM `daily` INNER JOIN item on daily.item_id=item.item_id WHERE daily.date BETWEEN '"+from+"' AND '"+to+"' AND item.item_name LIKE '%"+word+"%' OR daily.item_id LIKE '%"+word+"%' GROUP by `item_id`";
               try {
                    
                    Statement s = Database.getStatement();
                    ResultSet rs = s.executeQuery(sql);
                    return rs;
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }else if (seflag==2) {
               String sql="SELECT daily.item_id AS 'ItemID',item.item_name AS 'Name',`d_quantity` AS 'Sold Quantity' FROM `daily` INNER JOIN item ON daily.item_id=item.item_id WHERE `date`= '"+date+"' AND item.item_name LIKE '%"+word+"%' OR daily.item_id LIKE '%"+word+"%'";
               try {
                    
                    Statement s = Database.getStatement();
                    ResultSet rs = s.executeQuery(sql);
                    return rs;
                   
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }else{
               return null;
           }
         return null;
       }
       
       ResultSet getAll(){
           try {
               Statement s = Database.getStatement();
               ResultSet rs = s.executeQuery(cursql);
               return rs;
           } catch (Exception e) {
               e.printStackTrace();
           }
         return null;
       }
     
     
    
}
