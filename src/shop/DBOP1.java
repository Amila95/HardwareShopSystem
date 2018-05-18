/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shop;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.awt.HeadlessException;
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
            
            String sql1= "INSERT INTO `addqty` (`item_id`, `date`, `qty`) VALUES ('"+I.getItemID()+"', '2000-05-21 10:15:09', '0')";
            String sql2="INSERT INTO `bill` (`amount`, `discount`, `total_price`, `cash`, `change_amount`, `datetime`) VALUES ('0', '0', '0', '0', '0', '2000-05-21 10:15:30');";
            String pqr = ("INSERT INTO `item` (`item_id`, `item_name`, `item_price`,`item_cost`) VALUES ('"+I.getItemID()+"', '"+I.getName()+"', '"+I.getPrice()+"','"+I.getUnitCost()+"') ");
            s.executeUpdate(pqr);
            s.executeUpdate(sql1);
            s.executeUpdate(sql2,Statement.RETURN_GENERATED_KEYS);
            ResultSet rs= s.getGeneratedKeys();
            int bill_id=0;
            try {
             
            while(rs.next()){
                bill_id = rs.getInt(1);
            }
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            String sql3 ="INSERT INTO `bill_item` (`bill_id`, `item_id`, `quantity`, `cur_1x_price`, `cur_1x_cost`) VALUES ('"+bill_id+"', '"+I.getItemID()+"', '0', '0', '0')";
            s.executeUpdate(sql3);
            JOptionPane.showMessageDialog(null, "Item Added!");
           
        }
        catch(MySQLIntegrityConstraintViolationException ex){
            JOptionPane.showMessageDialog(null, "please enter valid inputs.Check your itemID","Error", JOptionPane.ERROR_MESSAGE);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "please enter valid inputs.Check your itemID","Error", JOptionPane.ERROR_MESSAGE);
        }
    }
     
    
     
     void updateItem(String oid,String nid,String name,double price,double cost){
         try {
             String sql= "UPDATE `item` SET `item_name` = '" + name + "',item_price = '" + price + "',item_id= '" + nid + "',item_cost= '" + cost + "' WHERE `item`.`item_id` = '" + oid + "'; ";
             Statement s = Database.getStatement();
             s.executeUpdate(sql);
             JOptionPane.showMessageDialog(null, "Successfully updated!");
             
         
         } 
         
         catch (Exception e) {
             
             JOptionPane.showMessageDialog(null, "Sorry you cannot update this item. please enter valid inputs!","Error", JOptionPane.ERROR_MESSAGE);
            }

     }
     
     ResultSet showItems(String click){
         String sql= "SELECT * FROM item WHERE item_id = '" + click + "' ";
         try {
             Statement s = Database.getStatement();
             ResultSet rs = s.executeQuery(sql);
             return rs;
         } catch (Exception e) {
             
             JOptionPane.showMessageDialog(null, "please enter valid inputs");
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
            
             JOptionPane.showMessageDialog(null, "Sorry you cannot delete this item.(Item may be use for past bills,reports,etc) !","Error", JOptionPane.ERROR_MESSAGE);
         }
     }
     
     ResultSet getItemData(){
        String sql="SELECT item_id AS 'Item Id', item_name AS 'Item Name',item_price AS 'Unit Price',item_cost AS 'Unit Cost' FROM item";        
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
         String sql="SELECT item_id AS 'Item Id', item_name AS 'Item Name',item_price AS 'Unit Price',item_cost AS 'Unit Cost' FROM `item` WHERE item_name LIKE '%"+name+"%' OR `item_id` LIKE '%"+name+"%' ";
         
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
        String sql="SELECT item_id AS 'Item Id', item_name AS 'Item Name',item_price AS 'Unit Price',item_cost AS 'Unit Cost', item_quantity AS 'Current Stock' FROM item";        
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
         String sql="SELECT item_id AS 'Item Id', item_name AS 'Item Name',item_price AS 'Unit Price',item_cost AS 'Unit Cost', item_quantity AS 'Current Stock' FROM `item` WHERE item_name LIKE '%"+name+"%' OR `item_id` LIKE '%"+name+"%' ";
         
         try {
             
             Statement s = Database.getStatement();
             ResultSet rs = s.executeQuery(sql);
             return rs;
             
         } catch (Exception e) {
            e.printStackTrace();
         }
         return null;
         
     }
       
       void addStock(String id,int value,int avalue){
           String sql="UPDATE `item` SET `item_quantity` = '"+value+"' WHERE `item`.`item_id` = '"+id+"' ";
           String sql1="INSERT INTO `addqty`( `item_id`, `qty`) VALUES ( '"+id+"', '"+avalue+"')";
           try {
               Statement s = Database.getStatement();
               s.executeUpdate(sql);
               s.executeUpdate(sql1);
               JOptionPane.showMessageDialog(null, "Stock Added!");
           } catch (Exception e) {
               e.printStackTrace();
           }
           
       }
       
       //report genaring
       ResultSet checkStock(){
           String sql="SELECT `item_id` AS 'ItemID' ,`item_name` AS 'Name', `item_price` AS '1x Unit Price', `item_quantity` AS 'Available Qty', item_price*item_quantity AS 'Price x Qty' ,`item_cost` AS '1x Unit Cost',`item_cost`*item_quantity AS 'Cost x Qty' FROM `item`";
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
       ResultSet getSumCostStock(){
           String sql="SELECT SUM(item_cost*item_quantity) AS 'Total' FROM `item`";
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
           //String sql="SELECT bill_item.item_id AS 'ItemID',item.item_name AS 'Name',SUM(bill_item.quantity) AS 'Sold Qty',bill_item.cur_1x_price AS '1X Price',SUM(bill_item.cur_1x_price*bill_item.quantity) AS 'Total' FROM `bill_item` JOIN bill ON bill_item.bill_id=bill.bill_id JOIN item on bill_item.item_id=item.item_id WHERE bill.datetime BETWEEN '"+from+"' AND '"+to+"' GROUP BY bill_item.item_id,bill_item.cur_1x_price";
           String sql = "SELECT bill_item.item_id AS 'ItemID',item.item_name AS 'Name',SUM(bill_item.quantity) AS 'Sold Qty',bill_item.cur_1x_price AS '1X Price',SUM(bill_item.cur_1x_price*bill_item.quantity) AS 'Total Price',bill_item.cur_1x_cost AS '1X Cost',SUM(bill_item.cur_1x_cost*bill_item.quantity) AS 'Total Cost' FROM `bill_item` JOIN bill ON bill_item.bill_id=bill.bill_id JOIN item on bill_item.item_id=item.item_id WHERE bill.datetime BETWEEN '"+from+"' AND '"+to+"' GROUP BY bill_item.item_id,bill_item.cur_1x_price,bill_item.cur_1x_cost";
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
           //String sql1="SELECT bill_item.item_id AS 'ItemID',item.item_name AS 'Name',SUM(bill_item.quantity) AS 'Sold Qty',bill_item.cur_1x_price AS '1X Price',SUM(bill_item.cur_1x_price*bill_item.quantity) AS 'Total' FROM `bill_item` JOIN bill ON bill_item.bill_id=bill.bill_id JOIN item on bill_item.item_id=item.item_id WHERE bill.datetime LIKE '"+date+"%' GROUP BY bill_item.item_id,bill_item.cur_1x_price";
           String sql="SELECT bill_item.item_id AS 'ItemID',item.item_name AS 'Name',SUM(bill_item.quantity) AS 'Sold Qty',bill_item.cur_1x_price AS '1X Price',SUM(bill_item.cur_1x_price*bill_item.quantity) AS 'Total Price',bill_item.cur_1x_cost AS '1X Cost',SUM(bill_item.cur_1x_cost*bill_item.quantity) AS 'Total Cost' FROM `bill_item` JOIN bill ON bill_item.bill_id=bill.bill_id JOIN item on bill_item.item_id=item.item_id WHERE bill.datetime LIKE '"+date+"%' GROUP BY bill_item.item_id,bill_item.cur_1x_price,bill_item.cur_1x_cost";
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
       ResultSet getOneReportTotalCost(String date){
           String sql="SELECT SUM(bill_item.cur_1x_cost*bill_item.quantity) AS 'Total Cost' FROM `bill_item` JOIN bill ON bill_item.bill_id=bill.bill_id JOIN item on bill_item.item_id=item.item_id WHERE bill.datetime LIKE '"+date+"%' ";
           
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
       ResultSet getPeriodReportTotalCost(String from,String to){
           String sql="SELECT SUM(bill_item.cur_1x_cost*bill_item.quantity) AS 'Total Cost' FROM `bill_item` JOIN bill ON bill_item.bill_id=bill.bill_id JOIN item on bill_item.item_id=item.item_id WHERE bill.datetime BETWEEN '"+from+"' AND '"+to+"'";
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
               //String sql1="SELECT bill_item.item_id AS 'ItemID',item.item_name AS 'Name',SUM(bill_item.quantity) AS 'Sold Qty',bill_item.cur_1x_price AS '1X Price',SUM(bill_item.cur_1x_price*bill_item.quantity) AS 'Total' FROM `bill_item` JOIN bill ON bill_item.bill_id=bill.bill_id JOIN item on bill_item.item_id=item.item_id WHERE bill.datetime BETWEEN '"+from+"' AND '"+to+"' AND bill_item.bill_id LIKE '%"+word+"%' OR item.item_name LIKE '%"+word+"%' GROUP BY bill_item.item_id,bill_item.cur_1x_price";
               String sql = "SELECT bill_item.item_id AS 'ItemID',item.item_name AS 'Name',SUM(bill_item.quantity) AS 'Sold Qty',bill_item.cur_1x_price AS '1X Price',SUM(bill_item.cur_1x_price*bill_item.quantity) AS 'Total Price',bill_item.cur_1x_cost AS '1X Cost',SUM(bill_item.cur_1x_cost*bill_item.quantity) AS 'Total Cost' FROM `bill_item` JOIN bill ON bill_item.bill_id=bill.bill_id JOIN item on bill_item.item_id=item.item_id WHERE bill.datetime BETWEEN '"+from+"' AND '"+to+"' AND bill_item.bill_id LIKE '%"+word+"%' OR item.item_name LIKE '%"+word+"%' GROUP BY bill_item.item_id,bill_item.cur_1x_price,bill_item.cur_1x_cost";
               try {
                    
                    Statement s = Database.getStatement();
                    ResultSet rs = s.executeQuery(sql);
                    return rs;
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }else if (seflag==2 || seflag==3) {
               //String sql1="SELECT bill_item.item_id AS 'ItemID',item.item_name AS 'Name',SUM(bill_item.quantity) AS 'Sold Qty',bill_item.cur_1x_price AS '1X Price',SUM(bill_item.cur_1x_price*bill_item.quantity) AS 'Total' FROM `bill_item` JOIN bill ON bill_item.bill_id=bill.bill_id JOIN item on bill_item.item_id=item.item_id WHERE bill.datetime LIKE '"+date+"%' AND bill_item.bill_id LIKE '%"+word+"%' OR item.item_name LIKE '%"+word+"%' GROUP BY bill_item.item_id,bill_item.cur_1x_price";
               String sql= "SELECT bill_item.item_id AS 'ItemID',item.item_name AS 'Name',SUM(bill_item.quantity) AS 'Sold Qty',bill_item.cur_1x_price AS '1X Price',SUM(bill_item.cur_1x_price*bill_item.quantity) AS 'Total Price',bill_item.cur_1x_cost AS '1X Cost',SUM(bill_item.cur_1x_cost*bill_item.quantity) AS 'Total Cost' FROM `bill_item` JOIN bill ON bill_item.bill_id=bill.bill_id JOIN item on bill_item.item_id=item.item_id WHERE bill.datetime LIKE '"+date+"%' AND bill_item.bill_id LIKE '%"+word+"%' OR item.item_name LIKE '%"+word+"%' GROUP BY bill_item.item_id,bill_item.cur_1x_price,bill_item.cur_1x_cost";
               try {
                    
                    Statement s = Database.getStatement();
                    ResultSet rs = s.executeQuery(sql);
                    return rs;
                   
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }else if(seflag==4){
               //String sql1="SELECT `item_id` AS 'ItemID' ,`item_name` AS 'Name', `item_price` AS '1x Price', `item_quantity` AS 'Available Qty', item_price*item_quantity AS 'Price x Qty' FROM `item` WHERE item.item_name LIKE '%"+word+"%' OR item.item_id LIKE '%"+word+"%'";
               String sql="SELECT `item_id` AS 'ItemID' ,`item_name` AS 'Name', `item_price` AS '1x Unit Price', `item_quantity` AS 'Available Qty', item_price*item_quantity AS 'Price x Qty' ,`item_cost` AS '1x Unit Cost',`item_cost`*item_quantity AS 'Cost x Qty' FROM `item` WHERE item.item_name LIKE '%"+word+"%' OR item.item_id LIKE '%"+word+"%'";
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
       ResultSet getCurCost(String id){
           String sql="SELECT `item_cost` FROM `item` WHERE `item_id`='"+id+"'";
           //System.out.println(sql);
             try {
               Statement s = Database.getStatement();
               ResultSet rs = s.executeQuery(sql);
               return rs;
           } catch (Exception e) {
               e.printStackTrace();
           }
         return null;
       }
       String nowsql="";
        ResultSet getStockReport(String from,String to){
            nowsql="SELECT item.item_id,item.item_name ,C.CurQtyFrom ,D.Addqty-C.Addqty AS 'add',D.Soldqty-C.Soldqty AS 'Sales',D.CurQtyTo, item.item_price, item.item_price*(D.CurQtyTo) AS 'Price*Stock',item.item_cost,item.item_cost*(D.CurQtyTo) AS 'Cost*Stock'\n" +
                        "FROM ( SELECT A.item_id,A.Addqty,B.Soldqty , A.Addqty-B.Soldqty AS 'CurQtyFrom'\n" +
                        "FROM ( SELECT addqty.`item_id` ,SUM(`qty`) AS 'Addqty' FROM `addqty` WHERE `date` < '"+from+"' GROUP BY `item_id`) \n" +
                        "as A\n" +
                        "\n" +
                        "join \n" +
                        "\n" +
                        "( SELECT bill_item.item_id , SUM(`quantity`) AS 'Soldqty' FROM `bill_item` WHERE bill_id IN (SELECT bill.bill_id FROM bill WHERE datetime < '"+from+"') GROUP BY item_id) \n" +
                        "as B\n" +
                        "\n" +
                        "on A.item_id=B.item_id) \n" +
                        "as C\n" +
                        "\n" +
                        "join \n" +
                        "\n" +
                        "( SELECT A.item_id,A.Addqty,B.Soldqty , A.Addqty-B.Soldqty AS 'CurQtyTo'\n" +
                        "FROM ( SELECT addqty.`item_id` ,SUM(`qty`) AS 'Addqty' FROM `addqty` WHERE `date` < '"+to+"' GROUP BY `item_id`) \n" +
                        "as A\n" +
                        "\n" +
                        "join \n" +
                        "\n" +
                        "( SELECT bill_item.item_id , SUM(`quantity`) AS 'Soldqty' FROM `bill_item` WHERE bill_id IN (SELECT bill.bill_id FROM bill WHERE datetime < '"+to+"') GROUP BY item_id) \n" +
                        "as B\n" +
                        "\n" +
                        "on A.item_id=B.item_id) \n" +
                        "as D\n" +
                        "\n" +
                        "on C.item_id=D.item_id JOIN item ON C.item_id=item.item_id";
             try {
               Statement s = Database.getStatement();
                 //System.out.println(sql);
               ResultSet rs = s.executeQuery(nowsql);
               return rs;
           } catch (Exception e) {
               e.printStackTrace();
           }
         return null;
        }
        
        ResultSet getSumStocks(String from,String to){
           String sql="SELECT  SUM(item.item_price*(D.CurQtyTo)) AS 'Price*Stock',SUM(item.item_cost*(D.CurQtyTo)) AS 'Cost*Stock'\n" +
                        "FROM ( SELECT A.item_id,A.Addqty,B.Soldqty , A.Addqty-B.Soldqty AS 'CurQtyFrom'\n" +
                        "FROM ( SELECT addqty.`item_id` ,SUM(`qty`) AS 'Addqty' FROM `addqty` WHERE `date` < '"+from+"' GROUP BY `item_id`) \n" +
                        "as A\n" +
                        "\n" +
                        "join \n" +
                        "\n" +
                        "( SELECT bill_item.item_id , SUM(`quantity`) AS 'Soldqty' FROM `bill_item` WHERE bill_id IN (SELECT bill.bill_id FROM bill WHERE datetime < '"+from+"') GROUP BY item_id) \n" +
                        "as B\n" +
                        "\n" +
                        "on A.item_id=B.item_id) \n" +
                        "as C\n" +
                        "\n" +
                        "join \n" +
                        "\n" +
                        "( SELECT A.item_id,A.Addqty,B.Soldqty , A.Addqty-B.Soldqty AS 'CurQtyTo'\n" +
                        "FROM ( SELECT addqty.`item_id` ,SUM(`qty`) AS 'Addqty' FROM `addqty` WHERE `date` < '"+to+"' GROUP BY `item_id`) \n" +
                        "as A\n" +
                        "\n" +
                        "join \n" +
                        "\n" +
                        "( SELECT bill_item.item_id , SUM(`quantity`) AS 'Soldqty' FROM `bill_item` WHERE bill_id IN (SELECT bill.bill_id FROM bill WHERE datetime < '"+to+"') GROUP BY item_id) \n" +
                        "as B\n" +
                        "\n" +
                        "on A.item_id=B.item_id) \n" +
                        "as D\n" +
                        "\n" +
                        "on C.item_id=D.item_id JOIN item ON C.item_id=item.item_id";
             try {
               Statement s = Database.getStatement();
               ResultSet rs = s.executeQuery(sql);
               return rs;
           } catch (Exception e) {
               e.printStackTrace();
           }
         return null;
       }
        
        ResultSet getStockSearch(String from,String to,String word){
           String sql="SELECT item.item_id,item.item_name ,C.CurQtyFrom ,D.Addqty-C.Addqty AS 'add',D.Soldqty-C.Soldqty AS 'Sales',D.CurQtyTo, item.item_price, item.item_price*(D.CurQtyTo) AS 'Price*Stock',item.item_cost,item.item_cost*(D.CurQtyTo) AS 'Cost*Stock'\n" +
                        "FROM ( SELECT A.item_id,A.Addqty,B.Soldqty , A.Addqty-B.Soldqty AS 'CurQtyFrom'\n" +
                        "FROM ( SELECT addqty.`item_id` ,SUM(`qty`) AS 'Addqty' FROM `addqty` WHERE `date` < '"+from+"' GROUP BY `item_id`) \n" +
                        "as A\n" +
                        "\n" +
                        "join \n" +
                        "\n" +
                        "( SELECT bill_item.item_id , SUM(`quantity`) AS 'Soldqty' FROM `bill_item` WHERE bill_id IN (SELECT bill.bill_id FROM bill WHERE datetime < '"+from+"') GROUP BY item_id) \n" +
                        "as B\n" +
                        "\n" +
                        "on A.item_id=B.item_id) \n" +
                        "as C\n" +
                        "\n" +
                        "join \n" +
                        "\n" +
                        "( SELECT A.item_id,A.Addqty,B.Soldqty , A.Addqty-B.Soldqty AS 'CurQtyTo'\n" +
                        "FROM ( SELECT addqty.`item_id` ,SUM(`qty`) AS 'Addqty' FROM `addqty` WHERE `date` < '"+to+"' GROUP BY `item_id`) \n" +
                        "as A\n" +
                        "\n" +
                        "join \n" +
                        "\n" +
                        "( SELECT bill_item.item_id , SUM(`quantity`) AS 'Soldqty' FROM `bill_item` WHERE bill_id IN (SELECT bill.bill_id FROM bill WHERE datetime < '"+to+"') GROUP BY item_id) \n" +
                        "as B\n" +
                        "\n" +
                        "on A.item_id=B.item_id) \n" +
                        "as D\n" +
                        "\n" +
                        "on C.item_id=D.item_id JOIN item ON C.item_id=item.item_id WHERE item.item_id LIKE '%"+word+"%' OR item.item_name LIKE '%"+word+"%'";
             try {
               Statement s = Database.getStatement();
               ResultSet rs = s.executeQuery(sql);
               return rs;
           } catch (Exception e) {
               e.printStackTrace();
           }
         return null;
       }
        
        ResultSet getAllStock(){
           
             try {
               Statement s = Database.getStatement();
               ResultSet rs = s.executeQuery(nowsql);
               return rs;
           } catch (Exception e) {
               e.printStackTrace();
           }
         return null;
       }
        
        
        
        
       
     
     
    
}
