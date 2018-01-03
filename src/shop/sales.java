/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shop;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.List;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import com.itextpdf.text.Element;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Acer
 */
public class sales extends javax.swing.JFrame {
    
    DBOP db = new DBOP();
    static double amount = 0;
    double discount = 0;
    double itemtotal = 0;
    static double total = 0;
    double Cash= 0;
    double changeprice = 0;
    double itemprice;
    int qt;
    double priceitem;
    
    Map<Integer, Integer> map = (Map<Integer, Integer>) new HashMap<Integer, Integer>();
    
    /**
     * Creates new form sales
     */
    void cleardata(){
        ItemID.setText("");
        qty.setText("");
        amont.setText("");
        discontvalue.setText("");
        price.setText("");
        cash.setText("");
        change.setText("");
        
        table.setModel(new DefaultTableModel(null,new String [] {
                "Item Name", "Price", "Qty", "Total"
            }));
    }
    
    
    public sales() {
        
        initComponents();
        
    }
    
    void printReport(Item I){
        try {
            //Item I = new Item();
            Document d=new Document();
            PdfWriter.getInstance(d, new FileOutputStream("report.pdf"));
            
            
            d.open();
                Paragraph para0 = new Paragraph("--------------------------------------");
                //para0.setAlignment(Paragraph.ALIGN_CENTER);
                d.add(para0);
                Paragraph para2 = new Paragraph("--------------------------------------");
                //para2.setAlignment(Paragraph.ALIGN_CENTER);
                d.add(para2);
                Paragraph para1 = new Paragraph("Goble Care");
                //para1.setAlignment(Paragraph.ALIGN_CENTER);
                d.add(para1);
                Paragraph para3 = new Paragraph("--------------------------------------");
                //para3.setAlignment(Paragraph.ALIGN_CENTER);
                d.add(para3);
                Paragraph para4 = new Paragraph("NO:37,Kadana Road,Kadana");
               // para4.setAlignment(Paragraph.ALIGN_CENTER);
                d.add(para4);
                Paragraph para5 = new Paragraph("Tel: 075 0504648");
                //para5.setAlignment(Paragraph.ALIGN_CENTER);
                d.add(para5);
                Paragraph para6 = new Paragraph("--------------------------------------");
                //para6.setAlignment(Paragraph.ALIGN_CENTER);
                d.add(para6);
                Paragraph para7 = new Paragraph("--------------------------------------");
                //para7.setAlignment(Paragraph.ALIGN_CENTER);
                d.add(para7);
                
                
                com.itextpdf.text.List list = new com.itextpdf.text.List();
                list.add("Item name" +"     "+"Quntity"+"       "+"Price");
                ResultSet rs = db.getbillitem(I.getBill_id());
                while(rs.next()) {
                    Double aitemprice = Double.parseDouble(rs.getString(5)); 
                    int qty = Integer.parseInt(rs.getString(3));
                    itemprice = aitemprice * qty ;
                    list.add(rs.getString(4) +"     "+ rs.getString(3)+"        "+ itemprice );
                    //list.setAlignment(com.itextpdf.text.List.ALIGN_CENTER);
                
                }
                //list.setAlignment(List.ALIGN_CENTER);
                d.add(list);
                Paragraph para8 = new Paragraph("--------------------------------------");
                //para8.setAlignment(Paragraph.ALIGN_CENTER);
                d.add(para8);
                Paragraph para9 = new Paragraph("TOTAL:"+"                  "+amont.getText());
                //para9.setAlignment(Paragraph.ALIGN_CENTER);
                d.add(para9);
                Paragraph para10 = new Paragraph("Discount:"+"                  "+discontvalue.getText());
                //para10.setAlignment(Paragraph.ALIGN_CENTER);
                d.add(para10);
                Paragraph para11 = new Paragraph("--------------------------------------");
                //para11.setAlignment(Paragraph.ALIGN_CENTER);
                d.add(para11);
                Paragraph para12 = new Paragraph("NET TOTAL:"+"                  "+price.getText());
                //para12.setAlignment(Paragraph.ALIGN_CENTER);
                d.add(para12);
                Paragraph para14 = new Paragraph("--------------------------------------");
                //para14.setAlignment(Paragraph.ALIGN_CENTER);
                d.add(para14);
                Paragraph para13 = new Paragraph("Cash:"+"                  "+cash.getText());
                //para13.setAlignment(Paragraph.ALIGN_CENTER);
                d.add(para13);
                Paragraph para15 = new Paragraph("--------------------------------------");
                //para15.setAlignment(Paragraph.ALIGN_CENTER);
                d.add(para15);
                Paragraph para16 = new Paragraph("***************************************");
               // para16.setAlignment(Paragraph.ALIGN_CENTER);
                d.add(para16);
                Paragraph para17 = new Paragraph("BALANCE:"+"                  "+change.getText());
                //para17.setAlignment(Paragraph.ALIGN_CENTER);
                d.add(para17);
                
                
                
                
                
                

            d.close();
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        }
        
        
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        ItemID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        qty = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        cash = new javax.swing.JTextField();
        amont = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        discontvalue = new javax.swing.JTextField();
        bff = new javax.swing.JLabel();
        price = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        change = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        print = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1024, 768));

        jLabel1.setText("Item ID");

        ItemID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemIDActionPerformed(evt);
            }
        });

        jLabel2.setText("Qty");

        qty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qtyActionPerformed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item Name", "Price", "Qty", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);

        jButton2.setText("Remove Item");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Payment Details"));

        jLabel5.setText("Cash");

        cash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cashActionPerformed(evt);
            }
        });

        jLabel3.setText("Amount");

        discontvalue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discontvalueActionPerformed(evt);
            }
        });

        bff.setText("Discount");

        jLabel4.setText("Total Price");

        jLabel6.setText("Change");

        jButton1.setText("Cansale");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        print.setText("Print");
        print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bff)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(cash, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(98, 98, 98))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(amont, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(discontvalue, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(change, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(print)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(117, 117, 117))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cash, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(amont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bff)
                    .addComponent(discontvalue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(change, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(78, 78, 78))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(print)
                            .addComponent(jButton1))
                        .addGap(23, 23, 23))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(483, 483, 483)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(ItemID, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)))
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(101, 101, 101))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(89, 89, 89))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ItemID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(106, 106, 106))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ItemIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemIDActionPerformed
        
    }//GEN-LAST:event_ItemIDActionPerformed

    private void qtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qtyActionPerformed
        
        Item I = new Item();
        
        I.setItemID(Integer.parseInt(ItemID.getText().toString()));
        I.setQty(Integer.parseInt(qty.getText().toString()));
        db.seachitem(I);
        //db.settotalcount(I);
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        Object[] row;
        this.itemtotal = (I.getPrice()* I.getQty());
        row = new Object[4];
        row[0] = I.getName();
        row[1]= I.getPrice();
        row[2] = I.getQty();
        row[3] = itemtotal;
        model.addRow(row);
        amount = amount + I.getPrice()*I.getQty();
        amont.setText(String.valueOf(amount));
        //Map<Integer, Integer> map = (Map<Integer, Integer>) new HashMap<Integer, Integer>();
        map.put(I.getItemID(), I.getQty());
        
        
        
        
        
    }//GEN-LAST:event_qtyActionPerformed

    private void discontvalueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discontvalueActionPerformed
        discount = Double.parseDouble(discontvalue.getText());
        total = amount - amount*discount/100;
        price.setText(String.valueOf(total));
    }//GEN-LAST:event_discontvalueActionPerformed

    private void cashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cashActionPerformed
        Cash = Double.parseDouble(cash.getText());
        changeprice = Cash - total;
        change.setText(String.valueOf(changeprice));
        
    }//GEN-LAST:event_cashActionPerformed

    private void printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printActionPerformed
        Item I = new Item();
        I.setAmount(Double.parseDouble(amont.getText()));
        I.setTotal_price(Double.parseDouble(price.getText()));
        I.setDiscount(Double.parseDouble(discontvalue.getText()));
        I.setCash(Double.parseDouble(cash.getText()));
        I.setChange_amount(Double.parseDouble(change.getText()));
        db.payment(I);
        Set set = map.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
         Map.Entry mentry = (Map.Entry)iterator.next();
         /*System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
         System.out.println(mentry.getValue());*/
        I.setItemID((int) mentry.getKey());
        I.setQty((int) mentry.getValue());
        try {
                ResultSet rs = db.getlastid();
                while(rs.next()) {
                    //System.out.println(rs.getString(1));
                    I.setBill_id(Integer.parseInt(rs.getString(1)));
                }
                
            } catch (Exception ex) {
                
            }
        
       db.settotalcount(I);
       db.upadatedailypayment(I);
       db.setbillitem(I.getBill_id(),I.getItemID(),I.getQty());
        }
        
            
            
        
        
        
       
        printReport(I);
        //db.deletebill(I);
       
        
        
    }//GEN-LAST:event_printActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        /*Item I = new Item();
        Set set = map.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
         Map.Entry mentry = (Map.Entry)iterator.next();
         /*System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
         System.out.println(mentry.getValue());
        I.setItemID((int) mentry.getKey());
        I.setQty((int) mentry.getValue());
        db.cansalepayment(I);
        }*/
        this.cleardata();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        try{
            int item_id;
            double item_price;
            int SelectedRowIndex = table.getSelectedRow();
            
            String item_name = (String) table.getValueAt(SelectedRowIndex, 0);
            int qty = (int) table.getValueAt(SelectedRowIndex, 2);
            ResultSet rs = db.getitemid(item_name);
            try{
            while(rs.next()){
                item_id = Integer.parseInt(rs.getString(1));
                item_price = Double.parseDouble(rs.getString(2));
                amount = amount - item_price*qty ;
                amont.setText(String.valueOf(amount));
                //Set set = map.entrySet();
                //map.remove(table.getValueAt(SelectedRowIndex, item_id));
                for(Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<Integer, Integer> entry = it.next();
                if(entry.getKey().equals(item_id)) {
                it.remove();
      }
    }
            }
            model.removeRow(SelectedRowIndex);
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new sales().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ItemID;
    private javax.swing.JTextField amont;
    private javax.swing.JLabel bff;
    private javax.swing.JTextField cash;
    private javax.swing.JTextField change;
    private javax.swing.JTextField discontvalue;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField price;
    private javax.swing.JButton print;
    private javax.swing.JTextField qty;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables

    
}
