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
       ResultSet checkStock(){
           String sql="SELECT `item_id` AS 'ItemID' ,`item_name` AS 'Name', `item_price` AS '1x Price', `item_quantity` AS 'Available Qty', item_price*item_quantity AS 'Price x Qty' FROM `item`";
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
       
       ResultSet getSumStock(){
           String sql="SELECT SUM(item_price*item_quantity) AS 'Total' FROM `item`";
           try {
               Statement s = Database.getStatement();
               ResultSet rs = s.executeQuery(sql);
               return rs;
           } catch (Exception e) {
               e.printStackTrace();
           }
         return null;
       }
       
       ResultSet getReport(String from,String to){
           String sql="SELECT bill_item.item_id AS 'ItemID',item.item_name AS 'Name',SUM(bill_item.quantity) AS 'Sold Qty',bill_item.cur_1x_price AS '1X Price',SUM(bill_item.cur_1x_price*bill_item.quantity) AS 'Total' FROM `bill_item` JOIN bill ON bill_item.bill_id=bill.bill_id JOIN item on bill_item.item_id=item.item_id WHERE bill.datetime BETWEEN '"+from+"' AND '"+to+"' GROUP BY bill_item.item_id,bill_item.cur_1x_price";
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
       
      
       ResultSet getTodayReport(String date){
           String sql="SELECT bill_item.item_id AS 'ItemID',item.item_name AS 'Name',SUM(bill_item.quantity) AS 'Sold Qty',bill_item.cur_1x_price AS '1X Price',SUM(bill_item.cur_1x_price*bill_item.quantity) AS 'Total' FROM `bill_item` JOIN bill ON bill_item.bill_id=bill.bill_id JOIN item on bill_item.item_id=item.item_id WHERE bill.datetime LIKE '"+date+"%' GROUP BY bill_item.item_id,bill_item.cur_1x_price";
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
       
       
       ResultSet getOneReportTotal(String date){
           String sql="SELECT SUM(bill_item.cur_1x_price*bill_item.quantity) AS 'Total' FROM `bill_item` JOIN bill ON bill_item.bill_id=bill.bill_id JOIN item on bill_item.item_id=item.item_id WHERE bill.datetime LIKE '"+date+"%' ";
           try {
               Statement s = Database.getStatement();
               ResultSet rs = s.executeQuery(sql);
               return rs;
           } catch (Exception e) {
               e.printStackTrace();
           }
           
           return null;
           
       }
       
       
       ResultSet getOneReportTotalBillPrice(String date){
           String sql="SELECT SUM(`total_price`) FROM `bill` WHERE `datetime` LIKE '"+date+"%'";
           
           try {
               Statement s = Database.getStatement();
               ResultSet rs = s.executeQuery(sql);
               return rs;
           } catch (Exception e) {
               e.printStackTrace();
           }
         return null;
       }
       
       ResultSet getPeriodReportTotal(String from,String to){
           String sql="SELECT SUM(bill_item.cur_1x_price*bill_item.quantity) AS 'Total' FROM `bill_item` JOIN bill ON bill_item.bill_id=bill.bill_id JOIN item on bill_item.item_id=item.item_id WHERE bill.datetime BETWEEN '"+from+"' AND '"+to+"'";
           try {
               Statement s = Database.getStatement();
               ResultSet rs = s.executeQuery(sql);
               return rs;
           } catch (Exception e) {
               e.printStackTrace();
           }
         return null;
       }
       
       ResultSet getPeriodReportTotalBillPrice(String from,String to){
           String sql="SELECT SUM(`total_price`) FROM `bill` WHERE `datetime` BETWEEN '"+from+"' AND '"+to+"'";
             try {
               Statement s = Database.getStatement();
               ResultSet rs = s.executeQuery(sql);
               return rs;
           } catch (Exception e) {
               e.printStackTrace();
           }
         return null;
       }
       
       ResultSet reportSearch(int seflag,String word,String from,String to,String date){
           
           if (seflag==1) {
               String sql="SELECT bill_item.item_id AS 'ItemID',item.item_name AS 'Name',SUM(bill_item.quantity) AS 'Sold Qty',bill_item.cur_1x_price AS '1X Price',SUM(bill_item.cur_1x_price*bill_item.quantity) AS 'Total' FROM `bill_item` JOIN bill ON bill_item.bill_id=bill.bill_id JOIN item on bill_item.item_id=item.item_id WHERE bill.datetime BETWEEN '"+from+"' AND '"+to+"' AND bill_item.bill_id LIKE '%"+word+"%' OR item.item_name LIKE '%"+word+"%' GROUP BY bill_item.item_id,bill_item.cur_1x_price";
               try {
                    
                    Statement s = Database.getStatement();
                    ResultSet rs = s.executeQuery(sql);
                    return rs;
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }else if (seflag==2 || seflag==3) {
               String sql="SELECT bill_item.item_id AS 'ItemID',item.item_name AS 'Name',SUM(bill_item.quantity) AS 'Sold Qty',bill_item.cur_1x_price AS '1X Price',SUM(bill_item.cur_1x_price*bill_item.quantity) AS 'Total' FROM `bill_item` JOIN bill ON bill_item.bill_id=bill.bill_id JOIN item on bill_item.item_id=item.item_id WHERE bill.datetime LIKE '"+date+"%' AND bill_item.bill_id LIKE '%"+word+"%' OR item.item_name LIKE '%"+word+"%' GROUP BY bill_item.item_id,bill_item.cur_1x_price";
               try {
                    
                    Statement s = Database.getStatement();
                    ResultSet rs = s.executeQuery(sql);
                    return rs;
                   
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }else if(seflag==4){
               String sql="SELECT `item_id` AS 'ItemID' ,`item_name` AS 'Name', `item_price` AS '1x Price', `item_quantity` AS 'Available Qty', item_price*item_quantity AS 'Price x Qty' FROM `item` WHERE item.item_name LIKE '%"+word+"%' OR item.item_id LIKE '%"+word+"%'";
               try {
                    
                    Statement s = Database.getStatement();
                    ResultSet rs = s.executeQuery(sql);
                    return rs;
                   
               } catch (Exception e) {
                   e.printStackTrace();
               }
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
       
       //login part
       
       int checklog(String uname,String password){
           String sql="SELECT * FROM `user` WHERE `uname`='"+uname+"' AND `password`='"+password+"'";
           
           try {
               Statement s = Database.getStatement();
               ResultSet rs = s.executeQuery(sql);
               
               if (!rs.next()) {
                  return 0;
               }else{
                   String type= rs.getString(3);
                   if(type.equals("Admin")){
                       return 1;
                   }else if(type.equals("Cashier")){
                       return 2;
                   }
                   
                   
               }
   
           } catch (Exception e) {
               e.printStackTrace();
           }
         return 0;
           
       }
       
       
     
     
    
}
