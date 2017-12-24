/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shop;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Acer
 */
public class DBOP {
    
    void seachitem(Item I){
    try{
        Statement s = Database.getStatement();
        String pqr = ("SELECT * FROM pricelist WHERE ID ='"+I.getItemID()+"'");
        ResultSet rs = s.executeQuery(pqr);
        while(rs.next()){
            I.setName(rs.getString("Name"));
            I.setPrice(Integer.parseInt(rs.getString("Price")));
        }
    }
    catch(Exception ex){
            ex.printStackTrace();
            }
    }
    void settotalcount(Item I){
    try{
        Statement s = Database.getStatement();
        String pq = ("SELECT Count FROM pricelist WHERE ID ='"+I.getItemID()+"'");
        ResultSet rs = s.executeQuery(pq);
        int t=0;
            while(rs.next()){
            t =rs.getInt(1);
            }
            int count1 = 0;
            I.setStock(count1);
            //I.setStock(Integer.parseInt(rs.getString("Count")));
            s.executeUpdate("UPDATE stock set Count ='"+I.getStock()+"' WHERE ID='"+I.getItemID()+"'");
            //s.executeUpdate("UPDATE patientdetails set Total='"+pe.getTotal()+"' WHERE p_id='"+p.getPatientId()+"' ");
        
    }catch(Exception ex){
        ex.printStackTrace();
    }
    }
    
}
    
    

