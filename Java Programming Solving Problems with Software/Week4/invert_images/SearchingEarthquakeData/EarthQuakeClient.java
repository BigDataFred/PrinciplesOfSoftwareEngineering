
/**
 * Write a description of class EarthQuakeClient here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class EarthQuakeClient {
    
    public void CSVparser(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        // String source = "http://earthquake.usgs.gov/earthquake
        ArrayList <QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read :"+list.size());
    }
    
}

