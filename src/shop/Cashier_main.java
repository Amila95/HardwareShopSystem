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
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.awt.Button;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author pasindu
 */
public class Cashier_main extends javax.swing.JFrame {
    DBOP1 db1=new DBOP1();
    String email="pasindurohana@gmail.com";
    String date="",filename="";
    /**
     * Creates new form Cashier_main
     */
    public Cashier_main() {
        this.setUndecorated(true);
        this.setAlwaysOnTop(false);
        this.setResizable(true);
        this.setVisible(true);
        initComponents();
        Toolkit tk=Toolkit.getDefaultToolkit();
        
        int xsize=(int) tk.getScreenSize().getWidth();
        int ysize=(int) tk.getScreenSize().getHeight();
        
        this.setSize(xsize, ysize);
        showDate();
        showTime();
        
        
        btnView.setEnabled(false);
        btnSend.setEnabled(false);
        txtEmail.setText(email);
        btnSave.setEnabled(false);
        
    }
    
    private void showDate() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(df.format(date));
    }

    private void showTime() {
        new Timer(0, new ActionListener() {
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
    
    public void sendMail(){
        final String username = "nuvan200@gmail.com"; //ur email
        final String password = "vikum200";

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
        message.setFrom(new InternetAddress("nuvan200@gmail.com"));//ur email
        message.setRecipients(Message.RecipientType.TO,
        InternetAddress.parse(email));//u will send to
        String file="",fileName="";
        
            message.setSubject("Report: Date: "+date);
            file = "files//report.pdf";
            fileName = date+".pdf";
       
            
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
        
        
   

            
    }catch (MessagingException e) {
        e.printStackTrace();
    }
    }
    
    private void setBtnColour(JLabel label) {
        label.setBackground(new Color(150, 150, 150));
    }

    private void resetBtnColour(JLabel label) {
        label.setBackground(new Color(190, 190, 190));
    }

    private void setButtonColour(Button button) {
        button.setBackground(new Color(0, 153, 0));
        button.setForeground(new Color(255, 255, 255));

    }

    private void resetButtonColour(Button button) {
        button.setBackground(new Color(240, 240, 240));
        button.setForeground(new Color(0, 0, 0));

    }

    private void setLableColour(JLabel lbl) {
        lbl.setBackground(new Color(106, 116, 145));

    }

    private void resetLableColour(JLabel lbl) {
        lbl.setBackground(new Color(9, 18, 72));
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
        jLabel3 = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        btnNewBill = new javax.swing.JLabel();
        btnExit = new javax.swing.JLabel();
        btnLogout = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        date_pick_one = new com.toedter.calendar.JDateChooser();
        btnGen = new javax.swing.JButton();
        btnView = new javax.swing.JButton();
        txtEmail = new javax.swing.JTextField();
        btnSend = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(85, 85, 85));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 170));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(254, 254, 254));
        jLabel3.setText("Cashier");

        lblTime.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTime.setForeground(new java.awt.Color(254, 254, 254));
        lblTime.setText("Time");

        lblDate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDate.setForeground(new java.awt.Color(254, 254, 254));
        lblDate.setText("Date");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images1/home2.png"))); // NOI18N

        jSeparator2.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator2.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator1.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator1.setForeground(new java.awt.Color(204, 204, 204));

        btnNewBill.setBackground(new java.awt.Color(190, 190, 190));
        btnNewBill.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnNewBill.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnNewBill.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images1/home_Add_Item.png"))); // NOI18N
        btnNewBill.setText("              New bill             ");
        btnNewBill.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(170, 170, 170)));
        btnNewBill.setOpaque(true);
        btnNewBill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNewBillMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNewBillMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNewBillMouseExited(evt);
            }
        });

        btnExit.setBackground(new java.awt.Color(190, 190, 190));
        btnExit.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnExit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images1/home_Add_Item.png"))); // NOI18N
        btnExit.setText("                  Exit                  ");
        btnExit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(170, 170, 170)));
        btnExit.setOpaque(true);
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExitMouseExited(evt);
            }
        });

        btnLogout.setBackground(new java.awt.Color(190, 190, 190));
        btnLogout.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnLogout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images1/logout.png"))); // NOI18N
        btnLogout.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(170, 170, 170)));
        btnLogout.setOpaque(true);
        btnLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLogoutMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLogoutMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLogoutMouseEntered(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(129, 129, 129));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(62, 62, 62)));

        btnGen.setFont(new java.awt.Font("URW Palladio L", 0, 12)); // NOI18N
        btnGen.setText("GENARATE");
        btnGen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenActionPerformed(evt);
            }
        });

        btnView.setFont(new java.awt.Font("URW Palladio L", 0, 12)); // NOI18N
        btnView.setText("VIEW");
        btnView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewActionPerformed(evt);
            }
        });

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        btnSend.setFont(new java.awt.Font("URW Palladio L", 0, 12)); // NOI18N
        btnSend.setText("SEND");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        btnSave.setFont(new java.awt.Font("URW Palladio L", 0, 12)); // NOI18N
        btnSave.setText("SAVE");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(date_pick_one, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnGen, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)
                                .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSend, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(date_pick_one, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnView)
                    .addComponent(btnGen)
                    .addComponent(btnSave))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSend))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Waree", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(248, 241, 241));
        jLabel1.setText("Genarate report");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDate)
                    .addComponent(lblTime)
                    .addComponent(jLabel3))
                .addGap(45, 45, 45)
                .addComponent(jLabel6)
                .addGap(34, 34, 34)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnNewBill, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(151, 151, 151)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblTime)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblDate))
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(btnNewBill, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE)))))
                    .addComponent(btnLogout))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(49, 58, 115));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images1/Main Board copy.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(189, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(241, 241, 241))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1630, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewBillMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNewBillMouseExited
        resetBtnColour(btnNewBill);
    }//GEN-LAST:event_btnNewBillMouseExited

    private void btnNewBillMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNewBillMouseEntered
        setBtnColour(btnNewBill);
    }//GEN-LAST:event_btnNewBillMouseEntered

    private void btnExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_btnExitMouseClicked

    private void btnExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseExited
        resetBtnColour(btnExit);
    }//GEN-LAST:event_btnExitMouseExited

    private void btnExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseEntered
        setBtnColour(btnExit);
    }//GEN-LAST:event_btnExitMouseEntered

    private void btnLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseClicked
        this.setVisible(false);
        login frm=new login();
        frm.setVisible(true);
    }//GEN-LAST:event_btnLogoutMouseClicked

    private void btnLogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseExited
        resetBtnColour(btnLogout);
    }//GEN-LAST:event_btnLogoutMouseExited

    private void btnLogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseEntered
        setBtnColour(btnLogout);
    }//GEN-LAST:event_btnLogoutMouseEntered

    private void btnNewBillMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNewBillMouseClicked
        sales frm=new sales();
        frm.setVisible(true);
    }//GEN-LAST:event_btnNewBillMouseClicked

    private void btnGenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenActionPerformed
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        Date c=date_pick_one.getDate();
        date=df.format(c);
        filename="report_"+date+".pdf";
        try {
            Document d=new Document();
            PdfWriter.getInstance(d, new FileOutputStream("files//report.pdf"));
            ResultSet rs=db1.getTodayReport(date);
            int colno = rs.getMetaData().getColumnCount();
            
             PdfPTable pt=new PdfPTable(colno);
            d.open();
                Paragraph para1 = new Paragraph("Report");
                para1.setAlignment(Paragraph.ALIGN_CENTER);
                d.add(para1);
                
                d.add(new Paragraph("Date: " + date));
                d.add(new Paragraph(" "));
                pt.addCell("ItemID");
                pt.addCell("Name");
                pt.addCell("Sold quantity");
                pt.addCell("1x Price");
                pt.addCell("Total Price");
                while (rs.next()) {
                    pt.addCell(rs.getString(1));
                    pt.addCell(rs.getString(2));
                    pt.addCell(rs.getString(3));
                    pt.addCell(rs.getString(4));
                    pt.addCell(rs.getString(5));

                }
                d.add(pt);
                ResultSet rs1 = db1.getOneReportTotal(date);
                d.add(new Paragraph(" "));
                double price = 0, price1 = 0;
                while (rs1.next()) {

                    price = rs1.getDouble(1);
                }
                ResultSet rs2 = db1.getOneReportTotalBillPrice(date);
                while (rs2.next()) {
                    price1 = rs2.getDouble(1);
                }
                double discounts = price - price1;
                d.add(new Paragraph("Total Price : " + price));
                d.add(new Paragraph("Discounts : " + discounts));
                d.add(new Paragraph("Total Income : " + price1));

            d.close();
            
            btnView.setEnabled(true);
            btnSend.setEnabled(true);
            btnSave.setEnabled(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        

        

    }//GEN-LAST:event_btnGenActionPerformed

    private void btnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewActionPerformed
        File myFile = new File("files//report.pdf");
        try {
            Desktop.getDesktop().open(myFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }        
    }//GEN-LAST:event_btnViewActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        this.sendMail();
    }//GEN-LAST:event_btnSendActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        this.saveFile(new File("files//report.pdf"),filename);
    }//GEN-LAST:event_btnSaveActionPerformed

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
            java.util.logging.Logger.getLogger(Cashier_main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cashier_main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cashier_main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cashier_main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cashier_main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnExit;
    private javax.swing.JButton btnGen;
    private javax.swing.JLabel btnLogout;
    private javax.swing.JLabel btnNewBill;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSend;
    private javax.swing.JButton btnView;
    private com.toedter.calendar.JDateChooser date_pick_one;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblTime;
    private javax.swing.JTextField txtEmail;
    // End of variables declaration//GEN-END:variables
}
