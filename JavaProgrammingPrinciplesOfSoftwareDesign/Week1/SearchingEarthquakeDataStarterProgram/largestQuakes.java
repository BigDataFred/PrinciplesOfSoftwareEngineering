
/**
 * Write a description of class largestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class largestQuakes {
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData,int howMany){
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        
        if (quakeData.size()<howMany){
            howMany = quakeData.size();
        }
        
        for (int k=0;k<howMany;k++){
            int maxIndex = indexOfLargest(copy);
            ret.add(copy.get(maxIndex));
            copy.remove(maxIndex);
        }
        
        return ret;
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> quakeData){
       
        int maxIndex = 0;
        for (int k=0;k<quakeData.size();k++){
            QuakeEntry curQuake = quakeData.get(k);
            if ( curQuake.getMagnitude() > quakeData.get(maxIndex).getMagnitude() ){
                maxIndex = k;
            }
        }
        System.out.println(maxIndex);
        System.out.println(quakeData.get(maxIndex).getMagnitude());
        return maxIndex;
    }
    
    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());
        
        /*for (QuakeEntry curQuake : list){
            System.out.println(curQuake);
        }*/
        
        ArrayList<QuakeEntry> largestList = getLargest(list,20);
        for (QuakeEntry curQuake : largestList){
            System.out.println(curQuake.getInfo());
        }
    }
}
