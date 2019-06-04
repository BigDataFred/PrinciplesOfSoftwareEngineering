import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 

        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        /*Filter f = new MinMagFilter(4.0); 
        ArrayList<QuakeEntry> m7  = filter(list, f); 
        for (QuakeEntry qe: m7) { 
        System.out.println(qe);
        } */

        Filter f2 = new MagnitudeFilter(3.5,4.5); 
        ArrayList<QuakeEntry> m8  = filter(list, f2); 
        
        Filter f3 = new DepthFilter(-20000.0,-55000.0); 
        ArrayList<QuakeEntry> m9  = filter(m8, f3); 
        
        for (QuakeEntry qe: m9) { 
            System.out.println(qe);
        } 
        System.out.println("There are "+m9.size()+ " cases that match this criteria");
        
       
        /*Location city =  new Location(39.7392, -104.9903);
        Filter f4 = new DistanceFilter(city,1000000); 
        ArrayList<QuakeEntry> m10  = filter(list, f4); 

        Filter f5 = new PhraseFilter("end","a"); 
        ArrayList<QuakeEntry> m11  = filter(m10, f5); 

        for (QuakeEntry qe: m11) { 
            System.out.println(qe);
        } 
        System.out.println("There are "+m11.size()+ " cases that match this criteria");
        */
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

    public void testMatchAllFilter(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        Filter f1 = new MagnitudeFilter(1.0,4.0);
        Filter f2 = new DepthFilter(-30000.0,-180000.0);                   
        Filter f3 = new PhraseFilter("any","o"); 

        MatchAllFilter maf = new MatchAllFilter();

        maf.addFilter(f1);
        maf.addFilter(f2);
        maf.addFilter(f3);

        ArrayList<QuakeEntry> mafList  = filter(list, maf);
        for (QuakeEntry qe: mafList) { 
            System.out.println(qe);
        } 
        System.out.println("There are "+mafList.size()+ " cases that match this criteria");
    }

    public void testMatchAllFilter2(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        Filter magFilter = new MagnitudeFilter(0.0,5.0);
        Location city =  new Location(55.7308, 9.1153);
        Filter distFilter = new DistanceFilter(city,3000000); 
        Filter phraseFilter = new PhraseFilter("any","e"); 

        MatchAllFilter maf = new MatchAllFilter();

        maf.addFilter(magFilter);
        maf.addFilter(distFilter);
        maf.addFilter(phraseFilter);

        ArrayList<QuakeEntry> mafList  = filter(list, maf);
        for (QuakeEntry qe: mafList) { 
            System.out.println(qe);
        } 
        System.out.println("There are "+mafList.size()+ " cases that match this criteria");
        String fNames = maf.getName();
        System.out.println("Filters used are: "+fNames);
    }
}
