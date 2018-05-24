
/**
 * Write a description of class firstCSVexample here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class firstCSVexample {

    public void readFood(){
        FileResource fr = new FileResource();// FileResource object
        CSVParser parser = fr.getCSVParser();// new Class from Apache library
        for (CSVRecord record : parser){ // CSVRecord class
            System.out.print(record.get("Name") + " ");// use method get
            System.out.print(record.get("Favorite Color") + " ");
            System.out.println(record.get("Favorite Food"));
        }
    }
}
