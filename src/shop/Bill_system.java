/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shop;

import java.util.Formatter;


public class Bill_system {
    
    
    private String output="";
    private Formatter f = new Formatter(System.out);
    
    public void printTitle(int billno,String date) {
       output+= "<html>\n" +
"  <head>\n" +
"\n" +
"    <title></title>\n" +
"    <style>\n" +
"html, body {\n" +
"                width: 287.244094488px;\n" +
"                height: 100%;\n" +
"                margin: 0;\n" +
"                padding: 0;\n" +
"\n" +
"            }\n" +
"\n" +
"table {\n" +
"    font-family: \"Times New Roman\", Times, serif;\n" +
"    font-size:  10px;\n" +
"    border-collapse: collapse;\n" +
"    width: 100%;\n" +
"\n" +
"}\n" +
"\n" +
"td, th {\n" +
"    /*border: 1px solid #dddddd;*/\n" +
"    text-align: left;\n" +
"\n" +
"}\n" +
"\n" +
"tr td {\n" +
"\n" +
"    padding-top: 0px;\n" +
"    padding-bottom: 0px;\n" +
"\n" +
"}\n" +
"hr {\n" +
"  padding-top: 0px;\n" +
"  padding-bottom: 0px;\n" +
"}\n" +
"\n" +
"\n" +
"</style>\n" +
"  </head>\n" +
"  <body>";
       output+=
                    "    <table>\n" ;
       
       output+="<tr>\n" +
"        <td colspan=\"3\" style=\"text-align: center;\"> GLOBAL CARE Detergent Products</td>\n" +
"      </tr>\n" +
"      <tr>\n" +
"        <td colspan=\"3\" style=\"text-align: center;\"> No:302 , Nagoda, Kandana</td>\n" +
"      </tr>\n" +
"      <tr>\n" +
"        <td colspan=\"3\" style=\"text-align: center;\"> Tel : 011 2051366 / rndistributors1@gmail.com</td>\n" +
"      </tr>";
       
       output+="<tr>\n" +
"        <td colspan=\"3\" style=\"text-align: center;\"> <hr style=\"border-top: dotted 2px;\"></td>\n" +
"\n" +
"\n" +
"      </tr>";
       
       output+="  <tr>\n" +
"        <td colspan=\"3\" style=\"text-align: left;\"> Bill Number :"+billno+" </td>\n" +
"      </tr>\n" +
"      <tr>\n" +
"        <td colspan=\"3\" style=\"text-align: left;\"> Date :"+date+" </td>\n" +
"      </tr>";
       
       output+="<tr>\n" +
"        <td colspan=\"3\" style=\"text-align: center;\"> <hr style=\"border-top: dotted 2px;\"></td>\n" +
"\n" +
"\n" +
"      </tr>";
       output+=
                    "      <tr>\n" +
                    "        <th>Item</th>\n" +
                    "        <th style=\"text-align: right;\">Qty</th>\n" +
                    "        <th style=\"text-align: right;\">Price</th>\n" +
                    "      </tr>\n" +
                    "      <tr>\n" +
                    "        <td colspan=\"3\" style=\"text-align: center;\"> <hr style=\"border-top: dotted 2px;\"></td>\n" +
                    "\n" +
                    "\n" +
                    "      </tr>";
       

    }
    public void print(String name, int qty, double price) {
       output+="<tr>\n" +
"        <td>"+name+"</td>\n" +
"        <td style=\"text-align: right;\">"+qty+"</td>\n" +
"        <td style=\"text-align: right;\">"+price+"</td>\n" +
"\n" +
"      </tr>";
      
    }
    public void printTotal(double amount,double dis,double total,double cash,double blance) {
      
      output+="<tr>\n" +
"        <td colspan=\"3\" style=\"text-align: center;\"> <hr style=\"border-top: dotted 2px;\"></td>\n" +
"\n" +
"\n" +
"      </tr>";
      
      output+="<tr>\n" +
"        <td>Amount</td>\n" +
"        <td></td>\n" +
"        <td style=\"text-align: right;\">"+amount+"</td>\n" +
"\n" +
"      </tr>";
      
      
      output+="<tr>\n" +
"        <td>Discount</td>\n" +
"        <td></td>\n" +
"        <td style=\"text-align: right;\">"+dis+"</td>\n" +
"\n" +
"      </tr>";
      
      output+="<tr>\n" +
"        <td colspan=\"3\" style=\"text-align: center;\"> <hr style=\"border-top: dotted 2px;\"></td>\n" +
"\n" +
"\n" +
"      </tr>";
      
      output+="<tr>\n" +
"        <td>Total</td>\n" +
"        <td></td>\n" +
"        <td style=\"text-align: right;\">"+total+"</td>\n" +
"\n" +
"      </tr>";
      
      output+="<tr>\n" +
"        <td colspan=\"3\" style=\"text-align: center;\"> <hr style=\"border-top: dotted 2px;\"></td>\n" +
"\n" +
"\n" +
"      </tr>";
      
      output+="<tr>\n" +
"        <td>Cash</td>\n" +
"        <td></td>\n" +
"        <td style=\"text-align: right;\">"+cash+"</td>\n" +
"\n" +
"      </tr>";
      output+="<tr>\n" +
"        <td>Blance</td>\n" +
"        <td></td>\n" +
"        <td style=\"text-align: right;\">"+blance+"</td>\n" +
"\n" +
"      </tr>";
      output+="<tr>\n" +
"        <td colspan=\"3\" style=\"text-align: center;\"> <hr style=\"border-top: dotted 2px;\"></td>\n" +
"\n" +
"\n" +
"      </tr>";
      output+="<tr>\n" +
"        <td colspan=\"3\" style=\"text-align: center;\"> <hr style=\"border-top: dotted 2px;\"></td>\n" +
"\n" +
"\n" +
"      </tr>";

      
    }
    
    public void printFinal(){
        output+=" <td colspan=\"3\" style=\"text-align: center;\"> Thank You Visit Again!</td>";
        
        output+="</table>\n" +
"  </body>\n" +
"</html>";
    }
    
    public String getOutput(){
        return this.output;
    }
    
    public static void main(String[] args) {
        Bill_system bill=new Bill_system();
        bill.printTitle(3,"pasindu");
        bill.print("Pasind",12,100);
        bill.print("Pasind",12,100);
        bill.printTotal(100, 1, 99, 100, 1);
        bill.printFinal();
        System.out.println(bill.getOutput());

    }
    
}
