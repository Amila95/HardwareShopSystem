/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shop;

import com.sun.org.glassfish.external.statistics.annotations.Reset;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.standard.Media;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;

/**
 *
 * @author pasindu
 */
public class Test {
    PrintService printService1 = PrintServiceLookup.lookupDefaultPrintService();
    Media med[] = (Media[])printService1.getSupportedAttributeValues(Media.class, null, null);
    
//    Media med[] = (Media[])printService1.getSupportedAttributeValues(Media.class, null, null);
//                for (Media media : med) {
//                if (media instanceof MediaSizeName) {
//                    MediaSizeName msn = (MediaSizeName) media;
//                    MediaSize ms = MediaSize.getMediaSizeForName(msn);
//                    float width = ms.getX(MediaSize.INCH);
//                    float height = ms.getY(MediaSize.INCH);
//                    System.out.println(media + ": width = " + width + "; height = " + height);
//                }
      

}
