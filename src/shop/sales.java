/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shop;

import com.itextpdf.text.Document;

import com.itextpdf.text.Paragraph;

import com.itextpdf.text.pdf.PdfWriter;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import java.awt.print.PrinterException;

import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
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
    int itemcount = 0;
    
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
        bill.setText("");
        table.setModel(new DefaultTableModel(null,new String [] {
                "ItemId","Item Name", "Price", "Qty", "Total"
            }));
    }
    
    
    public sales() {
        
        this.setUndecorated(true);
        this.setAlwaysOnTop(false);
        this.setResizable(true);
        this.setVisible(true);
        initComponents();
        Toolkit tk=Toolkit.getDefaultToolkit();
        
        int xsize=(int) tk.getScreenSize().getWidth();
        int ysize=(int) tk.getScreenSize().getHeight();
        
        this.setSize(xsize, ysize);
        
        btnGen.setEnabled(false);
        btnPrint.setEnabled(false);
        discontvalue.setText("0");
        
        
        
        
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
        gtbremove = new javax.swing.JButton();
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
        btnGen = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        bill = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1024, 768));

        jLabel1.setFont(new java.awt.Font("Waree", 0, 18)); // NOI18N
        jLabel1.setText("Item ID");

        ItemID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemIDActionPerformed(evt);
            }
        });
        ItemID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ItemIDKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Waree", 0, 18)); // NOI18N
        jLabel2.setText("Qty");

        qty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qtyActionPerformed(evt);
            }
        });
        qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                qtyKeyTyped(evt);
            }
        });

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(62, 62, 62)));

        table.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(62, 62, 62)));
        table.setFont(new java.awt.Font("Waree", 0, 16)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Item Name", "Price", "Qty", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setRowHeight(22);
        table.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tableAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        table.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        gtbremove.setFont(new java.awt.Font("Waree", 0, 18)); // NOI18N
        gtbremove.setText("Remove Item");
        gtbremove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gtbremoveActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(62, 62, 62)));

        jLabel5.setFont(new java.awt.Font("Waree", 0, 18)); // NOI18N
        jLabel5.setText("Cash");

        cash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cashActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Waree", 0, 18)); // NOI18N
        jLabel3.setText("Amount");

        discontvalue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discontvalueActionPerformed(evt);
            }
        });
        discontvalue.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                discontvalueKeyTyped(evt);
            }
        });

        bff.setFont(new java.awt.Font("Waree", 0, 18)); // NOI18N
        bff.setText("Discount");

        jLabel4.setFont(new java.awt.Font("Waree", 0, 18)); // NOI18N
        jLabel4.setText("Total Price");

        jLabel6.setFont(new java.awt.Font("Waree", 0, 18)); // NOI18N
        jLabel6.setText("Change");

        jButton1.setFont(new java.awt.Font("Waree", 0, 18)); // NOI18N
        jButton1.setText("NEW BILL");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnGen.setFont(new java.awt.Font("Waree", 0, 18)); // NOI18N
        btnGen.setText("GENARATE");
        btnGen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenActionPerformed(evt);
            }
        });

        btnPrint.setFont(new java.awt.Font("Waree", 0, 18)); // NOI18N
        btnPrint.setText("PRINT");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bff)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cash)
                    .addComponent(price)
                    .addComponent(amont)
                    .addComponent(discontvalue)
                    .addComponent(change)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGen, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 121, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(amont, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bff)
                    .addComponent(discontvalue, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cash, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(change, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(btnGen)
                .addGap(18, 18, 18)
                .addComponent(btnPrint)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(35, 35, 35))
        );

        jPanel5.setBackground(new java.awt.Color(85, 85, 85));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(85, 85, 85));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images1/home_ico.jpg"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(bill);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(ItemID, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(gtbremove, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(ItemID, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)
                                .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(gtbremove)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)))
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ItemIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemIDActionPerformed
        
    }//GEN-LAST:event_ItemIDActionPerformed

    private void qtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qtyActionPerformed
        
        Item I = new Item();
        
        I.setItemID(Integer.parseInt(ItemID.getText().toString()));
        I.setQty(Integer.parseInt(qty.getText().toString()));
        //db.seachitem(I);
        //db.settotalcount(I);
        ResultSet rs = db.seachitem(I);
        try {
            if(rs.next()){
                int currentqty = Integer.parseInt(rs.getString("item_quantity"));
                if((currentqty - I.getQty())>= 0){
                I.setItemID(Integer.parseInt(rs.getString("item_id")));
                I.setName(rs.getString("item_name"));
                I.setPrice(Double.parseDouble(rs.getString("item_price")));
                DefaultTableModel model = (DefaultTableModel)table.getModel();
                Object[] row;
                this.itemtotal = (I.getPrice()* I.getQty());
                row = new Object[5];
                row[0] = I.getItemID();
                row[1] = I.getName();
                row[2]= I.getPrice();
                row[3] = I.getQty();
                row[4] = itemtotal;
                model.addRow(row);
                amount = amount + I.getPrice()*I.getQty();
                amont.setText(String.valueOf(amount));
                //Map<Integer, Integer> map = (Map<Integer, Integer>) new HashMap<Integer, Integer>();
                 map.put(I.getItemID(), I.getQty());
                itemcount += 1;
                }
                else{
                    JOptionPane.showMessageDialog(null, "Stock is empty", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Input Worng Item ID", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(sales.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
        /*DefaultTableModel model = (DefaultTableModel)table.getModel();
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
        itemcount += 1;*/
        ItemID.setText("");
        qty.setText("");
        
        
        
        
        
    }//GEN-LAST:event_qtyActionPerformed

    private void discontvalueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discontvalueActionPerformed
        discount = Double.parseDouble(discontvalue.getText());
        total = amount - amount*discount/100;
        price.setText(String.valueOf(total));
        gtbremove.setEnabled(false);
    }//GEN-LAST:event_discontvalueActionPerformed

    private void cashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cashActionPerformed
        Cash = Double.parseDouble(cash.getText());
        Double currentamount = Double.parseDouble(price.getText());
        if((Cash - currentamount )>= 0){
            changeprice = Cash - total;
            change.setText(String.valueOf(changeprice));
            btnGen.setEnabled(true);
            gtbremove.setEnabled(false);
        }
        else{
            JOptionPane.showMessageDialog(null, "Cannot do payment", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_cashActionPerformed

    private void btnGenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenActionPerformed
        Item I = new Item();
        I.setAmount(Double.parseDouble(amont.getText()));
        I.setTotal_price(Double.parseDouble(price.getText()));
        I.setDiscount(Double.parseDouble(discontvalue.getText()));
        I.setCash(Double.parseDouble(cash.getText()));
        I.setChange_amount(Double.parseDouble(change.getText()));
        ResultSet rs=db.payment(I);
        try {
             
            while(rs.next()){
                I.setBill_id(rs.getInt(1));
            }
                
        } catch (Exception ex) {
            ex.printStackTrace();
        }
//        Set set = map.entrySet();
//        Iterator iterator = set.iterator();
//        while(iterator.hasNext()) {
//            Map.Entry mentry = (Map.Entry)iterator.next();
//
//            I.setItemID((int) mentry.getKey());
//            I.setQty((int) mentry.getValue());
//
//
//            db.settotalcount(I);
//            db.upadatedailypayment(I);
//            db.setbillitem(I.getBill_id(),I.getItemID(),I.getQty());
//        }

            for (int count = 0; count < table.getRowCount(); count++) {
                String ItemID = (table.getValueAt(count, 0).toString());
                String curPrice = (table.getValueAt(count, 2).toString());;
                String Qty = (table.getValueAt(count, 3).toString());

                I.setItemID(Integer.parseInt(ItemID));
                I.setPrice(Double.parseDouble(curPrice));
                I.setQty(Integer.parseInt(Qty));

                db.settotalcount(I);
                db.upadatedailypayment(I);
                db.setbillitem(I.getBill_id(), I.getItemID(), I.getQty(), I.getPrice());

             }
        
        
        
        int billid=I.getBill_id();
        this.getRecept(billid);
        btnPrint.setEnabled(true);

        
        
        
        
    }//GEN-LAST:event_btnGenActionPerformed
    
    public void getRecept(int billid){
        Bill_system recept=new Bill_system();
        ResultSet rs=db.getBillItems(billid);
        ResultSet rs1=db.getBill(billid);
        String date=LocalDate.now().toString();
        String time=LocalTime.now().toString();
        recept.printTitle(billid,date+" Time: "+time);
        try {
            while(rs.next()){
                String name=rs.getString(5);
                int qty=rs.getInt(3);
                double price=rs.getDouble(4)*qty;
                recept.print(name, qty, price);
            }
            
            while(rs1.next()){
                double amount=rs1.getDouble(2);
                double dis=rs1.getDouble(3);
                double total=rs1.getDouble(4);
                double cash=rs1.getDouble(5);
                double blance=rs1.getDouble(6);

                recept.printTotal(amount, dis, total, cash, blance);
            
            }
            recept.printFinal();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        String output=recept.getOutput();
        
        bill.setContentType("text/html");
        bill.setText(output);
        
        //bill.setText(output);
        
        try {
            Document d=new Document();
            PdfWriter.getInstance(d, new FileOutputStream("files//report1.pdf"));
            d.open();
                Paragraph para1 = new Paragraph(output);
                d.add(para1);
            d.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
            
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
        discontvalue.setText("0");
        gtbremove.setEnabled(true);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void gtbremoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gtbremoveActionPerformed
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        try{
            int item_id;
            double item_price;
            int SelectedRowIndex = table.getSelectedRow();
            
            String item_name = (String) table.getValueAt(SelectedRowIndex, 1);
            int qty = (int) table.getValueAt(SelectedRowIndex, 3);
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
                itemcount -= 1;
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
    }//GEN-LAST:event_gtbremoveActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        try {
            boolean complete = bill.print();
            if (complete) {
                JOptionPane.showMessageDialog(null, "Done Printing!");
            }else{
                JOptionPane.showMessageDialog(null, "Printing....");
            }
        } catch (PrinterException ex) {
            Logger.getLogger(sales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    private void tableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableKeyPressed
      DefaultTableModel model = (DefaultTableModel)table.getModel();
        try{
            int item_id;
            int pastqty;
            double item_price;
            double items_prices;
            int SelectedRowIndex = table.getSelectedRow();
            
            String item_name = (String) table.getValueAt(SelectedRowIndex, 0);
            int qty = (int) table.getValueAt(SelectedRowIndex, 2);
            ResultSet rs = db.getitemid(item_name);
            try{
            while(rs.next()){
                item_id = Integer.parseInt(rs.getString(1));
                item_price = Double.parseDouble(rs.getString(2));
                //amount = amount - item_price*qty ;
                //amont.setText(String.valueOf(amount));
                //Set set = map.entrySet();
                //map.remove(table.getValueAt(SelectedRowIndex, item_id));
                for(Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<Integer, Integer> entry = it.next();
                if(entry.getKey().equals(item_id)) {
                pastqty = entry.getValue();
                items_prices = item_price*qty;
                amount = amount + item_price*qty - item_price*pastqty;
                amont.setText(String.valueOf(amount));
                model.setValueAt(items_prices,SelectedRowIndex, 3);
                map.put(entry.getKey(), qty);
                
      }
    }
            }
            
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }  
    }//GEN-LAST:event_tableKeyPressed

    private void tableAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tableAncestorAdded
       
    }//GEN-LAST:event_tableAncestorAdded

    private void ItemIDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ItemIDKeyTyped
        char vChar = evt.getKeyChar();
                    if (!(Character.isDigit(vChar)
                            || (vChar == KeyEvent.VK_BACK_SPACE)
                            || (vChar == KeyEvent.VK_DELETE))) {
                        evt.consume();
                    }
    }//GEN-LAST:event_ItemIDKeyTyped

    private void qtyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtyKeyTyped
        char vChar = evt.getKeyChar();
                    if (!(Character.isDigit(vChar)
                            || (vChar == KeyEvent.VK_BACK_SPACE)
                            || (vChar == KeyEvent.VK_DELETE))) {
                        evt.consume();
                    }
    }//GEN-LAST:event_qtyKeyTyped

    private void discontvalueKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_discontvalueKeyTyped
        char vChar = evt.getKeyChar();
                    if ((Character.isLetter(vChar)
                    ||(vChar == KeyEvent.VK_BACK_SPACE)
                     || (vChar == KeyEvent.VK_DELETE))) {
                        evt.consume();
                    }
    }//GEN-LAST:event_discontvalueKeyTyped

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
    private javax.swing.JTextPane bill;
    private javax.swing.JButton btnGen;
    private javax.swing.JButton btnPrint;
    private javax.swing.JTextField cash;
    private javax.swing.JTextField change;
    private javax.swing.JTextField discontvalue;
    private javax.swing.JButton gtbremove;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField price;
    private javax.swing.JTextField qty;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables

    
}
