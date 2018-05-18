/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shop;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.proteanit.sql.DbUtils;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
/**
 *
 * @author pasindu
 */
public class reports extends javax.swing.JFrame {
    DBOP1 db1 = new DBOP1();
    public int seflag=0;
    public String from="",to="",date="",filename="";
    public String email="rndistributors1@gmail.com";
    
    /**
     * Creates new form reports
     */
    public reports() {
        this.setUndecorated(true);
        this.setAlwaysOnTop(false);
        this.setResizable(true);
        this.setVisible(true);
        initComponents();
        Toolkit tk=Toolkit.getDefaultToolkit();
        
        int xsize=(int) tk.getScreenSize().getWidth();
        int ysize=(int) tk.getScreenSize().getHeight();
        
        this.setSize(xsize, ysize);
        
        btnSearch.setEnabled(false);
        btnAll.setEnabled(false);
        
        btnGen.setEnabled(false);
        btnView.setEnabled(false);
        btnSend.setEnabled(false);
        
        txtEmail.setText(email);
        lblTitle.setText("");


        showDate();
        showTime(); 
        

        
    }
    
        public void printReport(){
        try {
            Document d=new Document();
            PdfWriter.getInstance(d, new FileOutputStream("files//report.pdf"));
            ResultSet rs=db1.getAll();
            int colno = rs.getMetaData().getColumnCount();
            
            PdfPTable pt=new PdfPTable(colno);
            d.open();
                Paragraph para1 = new Paragraph("Report");
                para1.setAlignment(Paragraph.ALIGN_CENTER);
                d.add(para1);
                           
                if(seflag==1){
                        d.add(new Paragraph("From: "+from+" To: "+to));
                        d.add(new Paragraph(" "));
                        pt.addCell("ItemID");
                        pt.addCell("Name");
                        pt.addCell("Sold quantity");
                        pt.addCell("1x Unit Price");
                        pt.addCell("Total Price");
                        pt.addCell("1x Unit Cost");
                        pt.addCell("Total Cost");
                    while (rs.next()) {
                        pt.addCell(rs.getString(1));
                        pt.addCell(rs.getString(2));
                        pt.addCell(rs.getString(3));
                        pt.addCell(rs.getString(4));
                        pt.addCell(rs.getString(5));
                        pt.addCell(rs.getString(6));
                        pt.addCell(rs.getString(7));

                    }
                    d.add(pt);
                    
                    ResultSet rs1=db1.getPeriodReportTotal(from,to);
                    d.add(new Paragraph(" "));
                    double price=0,price1=0,totalcost=0;
                    while(rs1.next()){
                        
                        price=rs1.getDouble(1);
                    }
                    ResultSet rs2=db1.getPeriodReportTotalBillPrice(from,to);
                    while(rs2.next()){
                        price1=rs2.getDouble(1);
                    }
                    double discounts=price-price1;
                    ResultSet rs3 = db1.getPeriodReportTotalCost(from,to);
                    while (rs3.next()) {
                        totalcost = rs3.getDouble(1);

                    }
            
                    double profit = price1-totalcost;
                    d.add(new Paragraph("Total Price : "+ price ));
                    d.add(new Paragraph("Discounts : " + discounts));
                    d.add(new Paragraph("Total Income : "+ price1 ));
                    d.add(new Paragraph("Total Cost : "+ totalcost ));
                    d.add(new Paragraph("Profit : "+ profit ));
                }
                
               
                else if(seflag==3 || seflag==2){
                        
                        d.add(new Paragraph("Date: "+date));
                        d.add(new Paragraph(" "));
                        pt.addCell("ItemID");
                        pt.addCell("Name");
                        pt.addCell("Sold quantity");
                        pt.addCell("1x Unit Price");
                        pt.addCell("Total Price");
                        pt.addCell("1x Unit Cost");
                        pt.addCell("Total Cost");
                    while(rs.next()) {
                        pt.addCell(rs.getString(1));
                        pt.addCell(rs.getString(2));
                        pt.addCell(rs.getString(3));
                        pt.addCell(rs.getString(4));
                        pt.addCell(rs.getString(5));
                        pt.addCell(rs.getString(6));
                        pt.addCell(rs.getString(7));

                    }
                d.add(pt);
                    ResultSet rs1=db1.getOneReportTotal(date);
                    d.add(new Paragraph(" "));
                    double price=0,price1=0,totalcost=0;
                    while(rs1.next()){
                        
                        price=rs1.getDouble(1);
                    }
                    ResultSet rs2=db1.getOneReportTotalBillPrice(date);
                    while(rs2.next()){
                        price1=rs2.getDouble(1);
                    }
                    ResultSet rs3 = db1.getOneReportTotalCost(date);
                    while (rs3.next()) {
                        totalcost = rs3.getDouble(1);

                    }
                    double profit = price1-totalcost;
                    double discounts=price-price1;
                    d.add(new Paragraph("Total Price : "+ price ));
                    d.add(new Paragraph("Discounts : " + discounts));
                    d.add(new Paragraph("Total Income : "+ price1 ));
                    d.add(new Paragraph("Total Cost : "+ totalcost ));
                    d.add(new Paragraph("Profit : "+ profit ));
                    
                }else if(seflag==4){
                    String date1=LocalDate.now().toString();
                    String time1=LocalTime.now().toString();
                    d.add(new Paragraph("Date: "+date1+" Time: "+time1));
                        d.add(new Paragraph(" "));
                        pt.addCell("ItemID");
                        pt.addCell("Name");
                        pt.addCell("1x Unit Price");
                        pt.addCell("Available Qty");
                        pt.addCell("Price x Qty");
                        pt.addCell("1x Unit Cost");
                        pt.addCell("Cost x Qty");
                    while(rs.next()) {
                        pt.addCell(rs.getString(1));
                        pt.addCell(rs.getString(2));
                        pt.addCell(rs.getString(3));
                        pt.addCell(rs.getString(4));
                        pt.addCell(rs.getString(5));
                        pt.addCell(rs.getString(6));
                        pt.addCell(rs.getString(7));

                    }
                d.add(pt);
                 ResultSet rs1=db1.getSumStock();
                 ResultSet rs2=db1.getSumCostStock();
                 d.add(new Paragraph(" "));
                    try {
                        while(rs1.next()){

                            double total=rs1.getDouble(1);
                            d.add(new Paragraph("Total Price : "+ total ));
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        while(rs2.next()){

                            double total=rs2.getDouble(1);
                            d.add(new Paragraph("Total Cost : "+ total ));
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                
                }
            d.close();
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
    public void sendMail(){
    final String username = "rndistributors2@gmail.com"; //ur email
    final String password = "rndis123!";

    Properties props = new Properties();
    props.put("mail.smtp.auth", true);
    props.put("mail.smtp.starttls.enable", true);
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }                            
    });

    try {
        email=txtEmail.getText();
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("rndistributors2@gmail.com"));//ur email
        message.setRecipients(Message.RecipientType.TO,
        InternetAddress.parse(email));//u will send to
        String file="",fileName="";
        if(seflag==2 || seflag==3){
            message.setSubject("Report: Date: "+date);
            file = "files//report.pdf";
            fileName = date+".pdf";
        }else if(seflag==1){
            message.setSubject("Report: From: "+from+" To: "+to);
            file = "files//report.pdf";
            fileName = from+"_"+to+".pdf";
        }else if(seflag==4){
            String date1=LocalDate.now().toString();
            String time1=LocalTime.now().toString();
            message.setSubject("Report: Stock Check: "+date1+" "+time1);
            file = "files//report.pdf";
            fileName = "stock_check_"+date1+"_"+time1+".pdf";
        }
            
        message.setText("Email with an attachment");
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        Multipart multipart = new MimeMultipart();


     
     
    //attached 1 --------------------------------------------
        
        messageBodyPart = new MimeBodyPart();   
        DataSource source = new FileDataSource(file);      
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(fileName);
        multipart.addBodyPart(messageBodyPart);
      
  
    
        message.setContent(multipart);

       
        //show load dialog
        Transport.send(message);
        JOptionPane.showMessageDialog(null, "Report has been sent");
        btnSend.setEnabled(false);
        
   

            
    }catch (MessagingException e) {
        JOptionPane.showMessageDialog(null, "Check your network connection!","Error", JOptionPane.ERROR_MESSAGE);
    }
    }
    
    public void addColoredText(JTextPane pane, String text, Color color) {
        StyledDocument doc = pane.getStyledDocument();

        Style style = pane.addStyle("Color Style", null);
        StyleConstants.setForeground(style, color);
        try {
            doc.insertString(doc.getLength(), text, style);
        } 
        catch (BadLocationException e) {
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        date_pick_fr = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        date_pick_to = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        report_table = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        date_pick_one = new com.toedter.calendar.JDateChooser();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        search_id = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnAll = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnGen = new javax.swing.JButton();
        btnView = new javax.swing.JButton();
        txtEmail = new javax.swing.JTextField();
        btnSend = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtReport = new javax.swing.JTextPane();
        lblTitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1152, 864));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(62, 62, 62)));

        jLabel1.setFont(new java.awt.Font("Waree", 0, 18)); // NOI18N
        jLabel1.setText("From");

        jButton1.setFont(new java.awt.Font("Waree", 0, 18)); // NOI18N
        jButton1.setText("SUBMIT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Waree", 0, 18)); // NOI18N
        jLabel2.setText("To");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(date_pick_fr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(date_pick_to, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(date_pick_fr, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(date_pick_to, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(62, 62, 62)));

        report_table.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(62, 62, 62)));
        report_table.setFont(new java.awt.Font("Waree", 0, 16)); // NOI18N
        report_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        report_table.setRowHeight(22);
        jScrollPane1.setViewportView(report_table);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(62, 62, 62)));

        jButton2.setFont(new java.awt.Font("Waree", 0, 18)); // NOI18N
        jButton2.setText("SUBMIT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(85, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(date_pick_one, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(date_pick_one, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(62, 62, 62)));

        search_id.setFont(new java.awt.Font("Waree", 0, 16)); // NOI18N
        search_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_idActionPerformed(evt);
            }
        });

        btnSearch.setFont(new java.awt.Font("Waree", 0, 18)); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnAll.setFont(new java.awt.Font("Waree", 0, 18)); // NOI18N
        btnAll.setText("All");
        btnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(search_id, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAll, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSearch)
                        .addComponent(btnAll))
                    .addComponent(search_id))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(62, 62, 62)));

        btnGen.setFont(new java.awt.Font("Waree", 0, 18)); // NOI18N
        btnGen.setText("SAVE");
        btnGen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenActionPerformed(evt);
            }
        });

        btnView.setFont(new java.awt.Font("Waree", 0, 18)); // NOI18N
        btnView.setText("VIEW");
        btnView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewActionPerformed(evt);
            }
        });

        txtEmail.setFont(new java.awt.Font("Waree", 0, 16)); // NOI18N

        btnSend.setFont(new java.awt.Font("Waree", 0, 18)); // NOI18N
        btnSend.setText("SEND");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnGen, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSend, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnGen)
                        .addComponent(btnView)
                        .addComponent(btnSend))
                    .addComponent(txtEmail))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(194, 254, 149));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images1/home_ico.jpg"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel5.setText("Admin");

        lblTime.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTime.setText("Time");

        lblDate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDate.setText("Date");

        jSeparator1.setBackground(new java.awt.Color(51, 51, 51));
        jSeparator1.setForeground(new java.awt.Color(204, 204, 204));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images1/home2.png"))); // NOI18N

        jSeparator2.setBackground(new java.awt.Color(51, 51, 51));
        jSeparator2.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel7.setText("Reports");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(lblTime)
                            .addComponent(lblDate))
                        .addGap(60, 60, 60)
                        .addComponent(jLabel6)
                        .addGap(40, 40, 40)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel5)
                                .addGap(22, 22, 22)
                                .addComponent(lblTime)
                                .addGap(18, 18, 18)
                                .addComponent(lblDate))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel6))
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Waree", 0, 18)); // NOI18N
        jLabel3.setText("Period reports");

        jLabel4.setFont(new java.awt.Font("Waree", 0, 18)); // NOI18N
        jLabel4.setText("One day report");

        jButton4.setFont(new java.awt.Font("Waree", 0, 14)); // NOI18N
        jButton4.setText("CHECK STOCK");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Waree", 0, 14)); // NOI18N
        jButton5.setText("GET TODAY REPORT");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        txtReport.setFont(new java.awt.Font("Waree", 0, 18)); // NOI18N
        jScrollPane2.setViewportView(txtReport);

        lblTitle.setFont(new java.awt.Font("Waree", 0, 18)); // NOI18N
        lblTitle.setText("Title");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(53, 53, 53))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitle)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblTitle))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        Date c=date_pick_fr.getDate();
        Date d=date_pick_to.getDate();
        from=df.format(c);
        to=df.format(d);
        
        
        ResultSet rs=db1.getReport(from, to);
        report_table.setModel(DbUtils.resultSetToTableModel(rs));
        seflag=1;
        txtReport.setText("");
        date_pick_one.setDate(null);
        
        btnAll.setEnabled(true);
        btnSearch.setEnabled(true);
        
        btnGen.setEnabled(true);
        btnView.setEnabled(true);
        btnSend.setEnabled(true);
        
        //genatate other info
        lblTitle.setText("Report: From: "+from+" 12.00AM To: "+to+" 12.00AM");
        filename= "report_"+from+"_"+to+".pdf";
        try {
            double price=0,price1=0,totalcost=0;
            ResultSet rs1 = db1.getPeriodReportTotal(from,to);
            while (rs1.next()) {

                price = rs1.getDouble(1);
                
            }
            ResultSet rs2 = db1.getPeriodReportTotalBillPrice(from,to);
            while (rs2.next()) {
                price1 = rs2.getDouble(1);
                
            }
            double discount=price-price1;
            
            ResultSet rs3 = db1.getPeriodReportTotalCost(from,to);
            while (rs3.next()) {
                totalcost = rs3.getDouble(1);
                
            }
            
            double profit = price1-totalcost;
            
            addColoredText(txtReport, "\nTotal Price : " + price+"\n", Color.BLACK);
            addColoredText(txtReport, "Discounts : " + discount+"\n", Color.BLACK);
            addColoredText(txtReport, "Total Income : " + price1+"\n", Color.BLACK);
            addColoredText(txtReport, "Total Cost : " + totalcost+"\n", Color.BLACK);
            
            addColoredText(txtReport, "Profit : " + profit+"\n", Color.BLACK);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "please select a date","Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        
        this.printReport(); 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "please select a date","Error", JOptionPane.ERROR_MESSAGE);
        }
         
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
           DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        Date c=date_pick_one.getDate();
        date=df.format(c);
        
        ResultSet rs=db1.getTodayReport(date);  
        report_table.setModel(DbUtils.resultSetToTableModel(rs));
        seflag=2;
        txtReport.setText("");
        date_pick_fr.setDate(null);
        date_pick_to.setDate(null);
        btnSearch.setEnabled(true);
        btnAll.setEnabled(true);
        
        btnGen.setEnabled(true);
        btnView.setEnabled(true);
        btnSend.setEnabled(true);
        
        lblTitle.setText("Report: Date "+date +" 12.00AM - 12.00PM");
        filename= "report_"+date+".pdf";

        try {
            double price=0,price1=0,totalcost=0;
            ResultSet rs1 = db1.getOneReportTotal(date);
            while (rs1.next()) {

                price = rs1.getDouble(1);
                
            }
            ResultSet rs2 = db1.getOneReportTotalBillPrice(date);
            while (rs2.next()) {
                price1 = rs2.getDouble(1);
                
            }
            double discount=price-price1;
            
            ResultSet rs3 = db1.getOneReportTotalCost(date);
            while (rs3.next()) {
                totalcost = rs3.getDouble(1);
                
            }
            double profit = price1-totalcost;
            
            addColoredText(txtReport, "\nTotal Price : " + price+"\n", Color.BLACK);
            addColoredText(txtReport, "Discounts : " + discount+"\n", Color.BLACK);
            addColoredText(txtReport, "Total Income : " + price1+"\n", Color.BLACK);
            addColoredText(txtReport, "Total Cost : " + totalcost+"\n", Color.BLACK);
            addColoredText(txtReport, "Profit : " + profit+"\n", Color.BLACK);
            
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "please select a date","Error", JOptionPane.ERROR_MESSAGE);
        }
        this.printReport();   
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "please select a date","Error", JOptionPane.ERROR_MESSAGE);
        }
        
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void search_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_idActionPerformed
        String word= search_id.getText();
        ResultSet rs=db1.reportSearch(seflag,word,from,to,date);
        report_table.setModel(DbUtils.resultSetToTableModel(rs));
    }//GEN-LAST:event_search_idActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String word= search_id.getText();
        ResultSet rs=db1.reportSearch(seflag,word,from,to,date);
        report_table.setModel(DbUtils.resultSetToTableModel(rs));
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllActionPerformed
        ResultSet rs=db1.getAll();
        report_table.setModel(DbUtils.resultSetToTableModel(rs));        //all
    }//GEN-LAST:event_btnAllActionPerformed

    private void btnGenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenActionPerformed
        try{
            this.saveFile(new File("files//report.pdf"),filename);
   
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Can't save!","Error", JOptionPane.ERROR_MESSAGE);
        }
        
        
        
    }//GEN-LAST:event_btnGenActionPerformed

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        
        this.sendMail();        
    }//GEN-LAST:event_btnSendActionPerformed

    private void btnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewActionPerformed
        File myFile = new File("files//report.pdf");
        try {
            Desktop.getDesktop().open(myFile);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Can't open!","Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnViewActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        txtReport.setText("");
        Date date1 = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
        date= df.format(date1);
        Date date2 = new Date();
        SimpleDateFormat df1 = new SimpleDateFormat("hh:mm:ss a"); // use HH for het time in 24 hour format
        String time= df1.format(date2);
        
        
        ResultSet rs=db1.checkStock();
        
        report_table.setModel(DbUtils.resultSetToTableModel(rs));
        ResultSet rs1=db1.getSumStock();
        ResultSet rs2=db1.getSumCostStock();
        try {
            while(rs1.next()){
                
                double total=rs1.getDouble(1);
                addColoredText(txtReport, "\nTotal Price : " +total+"\n", Color.BLACK);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            while(rs2.next()){
                
                double total=rs2.getDouble(1);
                addColoredText(txtReport, "\nTotal Cost : " +total+"\n", Color.BLACK);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        seflag=4;
        btnSearch.setEnabled(true);
        btnAll.setEnabled(true);
        btnGen.setEnabled(true);
        
        
        date_pick_fr.setDate(null);
        date_pick_to.setDate(null);
        date_pick_one.setDate(null);
        
        btnView.setEnabled(true);
        btnSend.setEnabled(true);
        filename= "stock_report_"+date+".pdf";
        lblTitle.setText("Stock Report: "+date+"_"+time);
        
        this.printReport();  
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Date date1 = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
        date= df.format(date1);
        
        
        ResultSet rs=db1.getTodayReport(date);
        report_table.setModel(DbUtils.resultSetToTableModel(rs));
        seflag=3;
        
        txtReport.setText("");
        date_pick_fr.setDate(null);
        date_pick_to.setDate(null);
        btnSearch.setEnabled(true);
        btnAll.setEnabled(true);
        
        btnGen.setEnabled(true);
        btnView.setEnabled(true);
        btnSend.setEnabled(true);
        
        date_pick_fr.setDate(null);
        date_pick_to.setDate(null);
        date_pick_one.setDate(null);
        
        
        //genatate other info
        lblTitle.setText("Report: Date "+date+" 12.00AM-12.00PM");
        filename= "report_"+date+".pdf";
        try {
            double price=0,price1=0,totalcost=0;
            ResultSet rs1 = db1.getOneReportTotal(date);
            while (rs1.next()) {

                price = rs1.getDouble(1);
                
            }
            ResultSet rs2 = db1.getOneReportTotalBillPrice(date);
            while (rs2.next()) {
                price1 = rs2.getDouble(1);
                
            }
            Double discount=price-price1;
            ResultSet rs3 = db1.getOneReportTotalCost(date);
            while (rs3.next()) {
                totalcost = rs3.getDouble(1);
                
            }
            double profit = price1-totalcost;
            
            addColoredText(txtReport, "\nTotal Price : " + price+"\n", Color.BLACK);
            addColoredText(txtReport, "Discounts : " + discount+"\n", Color.BLACK);
            addColoredText(txtReport, "Total Income : " + price1+"\n", Color.BLACK);
            addColoredText(txtReport, "Total Cost : " + totalcost+"\n", Color.BLACK);
            addColoredText(txtReport, "Total Profit : " + profit+"\n", Color.BLACK);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.printReport();  
    }//GEN-LAST:event_jButton5ActionPerformed
    public void saveFile(File file,String name) {
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setSelectedFile(new File(name));
            
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "PDF file", "pdf");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showSaveDialog(null);
            String path="";
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                path= chooser.getSelectedFile().getAbsolutePath();
                File dest = new File(path); //any location
                Files.copy(file.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
            
            
            
        }
         catch (IOException ex) {
            ex.printStackTrace();
        }
    }
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
            java.util.logging.Logger.getLogger(reports.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(reports.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(reports.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(reports.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new reports().setVisible(true);
            }
        });
    }
    
    private void showDate() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(df.format(date));
    }

    private void showTime() {
        new javax.swing.Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = new Date();
                SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss a"); // use HH for het time in 24 hour format
                lblTime.setText(df.format(date));
            }

            //@Override
            //public void actionPerformed(ActionEvent e) {
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           // }
        }).start();
      
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAll;
    private javax.swing.JButton btnGen;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSend;
    private javax.swing.JButton btnView;
    private com.toedter.calendar.JDateChooser date_pick_fr;
    private com.toedter.calendar.JDateChooser date_pick_one;
    private com.toedter.calendar.JDateChooser date_pick_to;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTable report_table;
    private javax.swing.JTextField search_id;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextPane txtReport;
    // End of variables declaration//GEN-END:variables
}
