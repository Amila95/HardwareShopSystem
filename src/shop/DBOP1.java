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
     
     
    
}
