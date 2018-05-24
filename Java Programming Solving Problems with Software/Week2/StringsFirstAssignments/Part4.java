import edu.duke.*;

/**
 * Write a description of class Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {

    public void findingWebLinks(){
    String str = "";
    URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
     for (String s : ur.lines()) {
         String s2 = s.toLowerCase();
         // print or process fields in record
         int Index = s2.indexOf("youtube.com");
         if (Index != -1){
             int startIndex = s2.indexOf("href=\"");
             int stopIndex = s2.indexOf("\"",startIndex+6);
             str = s.substring(startIndex+6,stopIndex);
             System.out.println(str);
            }
     }
    }
    
}
