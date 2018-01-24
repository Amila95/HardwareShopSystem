package shop;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.print.PrintService;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.Size2DSyntax;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.PageRanges;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
 
 
public class DirectPrint implements Printable {
 
    private PrintService[] printService;
    private JTextPane text;
 
    public DirectPrint() {
        this.printService = PrinterJob.lookupPrintServices();
    }
 
    public static void main(String[] args) {
        DirectPrint lt = new DirectPrint();
        //lt.printString("If this text gets printed, it will have worked! ;D");
    }
 
    public void printString(JTextPane input) {
 
        this.text = input;
         
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        //aset.add(new PageRanges(1, 2));
        aset.add(new Copies(1));
        
        //aset.add(new MediaPrintableArea(0.5f,0.5f,0.5f,0.5f,Size2DSyntax.MM));
        
    
        PrinterJob printJob = PrinterJob.getPrinterJob();
        
        PageFormat pf = printJob.defaultPage();
        Paper paper = new Paper();
        paper.setSize(60, 40);
        double margin = 9;
        paper.setImageableArea(margin, margin, paper.getWidth() - margin * 2, paper.getHeight()
            - margin * 2);
        pf.setPaper(paper);
        
        printJob.setPrintable(this,pf);
 
        try {
            printJob.setPrintService(printService[0]);
            ////index of installed printers on you system
            //not sure if default-printer is always '0'
            printJob.printDialog(aset);
        } catch (PrinterException err) {
            System.err.println(err);
        }
    }
 
    public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(pf.getImageableX(), pf.getImageableY());
        g.drawString(String.valueOf(this.text), 14, 14);
        return PAGE_EXISTS;
    }
}