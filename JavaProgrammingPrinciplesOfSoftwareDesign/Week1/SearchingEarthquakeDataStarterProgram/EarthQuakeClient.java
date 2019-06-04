import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry quake : quakeData){
            if (quake.getMagnitude() > magMin){
                answer.add(quake);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (int k=0;k<quakeData.size();k++){
            QuakeEntry currentQuake = quakeData.get(k);
            Location quakeLoc = currentQuake.getLocation();
            float dist = quakeLoc.distanceTo(from);
            if (dist < distMax){
                answer.add(currentQuake);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, 
    double maxDepth){

        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry quake : quakeData){
            if (quake.getDepth() < minDepth && quake.getDepth() > maxDepth){
                answer.add(quake);
            }
        }
        return answer;

    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,String where, String phrase){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry quake : quakeData){
            String title = quake.getInfo();
            if (where == "start"){
                String chck = title.substring(0, phrase.length());
                int indx = phrase.indexOf(chck);
                if ( indx !=-1 ){
                    answer.add(quake);
                }
            }
            if (where == "end"){
                String chck = title.substring(title.length()-phrase.length(), title.length());
                int indx = phrase.indexOf(chck);
                if ( indx !=-1 ){
                    answer.add(quake);
                }
            }
            if (where == "any"){
                int indx = title.indexOf(phrase);
                if ( indx !=-1 ){
                    answer.add(quake);
                }
            }
        }
        return answer;
    }
    
    public void quakesByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        /*ArrayList<QuakeEntry> listPhrase1 = filterByPhrase(list, "start", "Explosion");
        for (QuakeEntry qe : listPhrase1){
            System.out.println(qe);
        } 
        System.out.println("Found "+listPhrase1.size()+" quakes that match that criteria");*/
        
        /*ArrayList<QuakeEntry> listPhrase2 = filterByPhrase(list, "end", "California");
        for (QuakeEntry qe : listPhrase2){
            System.out.println(qe);
        } 
        System.out.println("Found "+listPhrase2.size()+" quakes that match that criteria");*/
        
        ArrayList<QuakeEntry> listPhrase3 = filterByPhrase(list, "any", "Can");
        for (QuakeEntry qe : listPhrase3){
            System.out.println(qe);
        } 
        System.out.println("Found "+listPhrase3.size()+" quakes that match that criteria");
        
    }
    
    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        ArrayList<QuakeEntry> listDepth = filterByDepth(list, -2000.0, -4000.0);
        
        System.out.println("Find quakes with depth between -4000.0 and -2000.0");
        for (QuakeEntry qe : listDepth){
            System.out.println(qe);
        } 
        System.out.println("Found "+listDepth.size()+" quakes that match that criteria");
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        ArrayList<QuakeEntry> listBig = filterByMagnitude(list,5.0);
        System.out.println("read data for "+list.size()+" quakes");
        for (QuakeEntry qe : listBig){
            System.out.println(qe);
        } 
        System.out.println("Found "+listBig.size()+" quakes that match that criteria");
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);

        // TODO
        ArrayList<QuakeEntry> closeQuakes = filterByDistanceFrom(list,1000000.0,city);
        for (QuakeEntry quake : closeQuakes){
            Location loc = quake.getLocation();
            double dist = loc.distanceTo(city)/1000.0;
            System.out.println(dist + quake.getInfo());
        }
        System.out.println("Found "+closeQuakes.size()+" quakes that match that criteria");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }

}
