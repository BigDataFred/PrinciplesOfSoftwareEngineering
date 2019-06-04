
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }

    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes){

        for (int i = 0; i < quakes.size()-1;i++){
            if (quakes.get(i).getMagnitude() > quakes.get(i+1).getMagnitude()){
                return false;
            }
        }
        return true;
    }

    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in){
        int N = in.size(); 
        int cnt = 0;
        for (int i = 0;i<N-1;i++){
            boolean chck = checkInSortedOrder(in);
             if (chck==true){
                break;
            }
            onePassBubbleSort(in,i);
            cnt = cnt+1;
        }
        System.out.println("Passes needed"+ cnt);
    }

    public void onePassBubbleSort(ArrayList<QuakeEntry> quakes,int numSorted){
        int Sidx1 = 0;
        int Sidx2 = 1;
        for (int i = 0; i < quakes.size()-1-numSorted;i++){
            Sidx1 = i;
            Sidx2 = i+1;
            if (quakes.get(Sidx1).getMagnitude() > quakes.get(Sidx2).getMagnitude()) {
                QuakeEntry q1 = quakes.get(Sidx1);
                QuakeEntry q2 = quakes.get(Sidx2);
                quakes.set(Sidx1,q2);
                quakes.set(Sidx2,q1);
            }
        }

    }

    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
        int N = in.size(); 
        for (int i = 0;i<N-1;i++){
            onePassBubbleSort(in,i);
            System.out.println("Printing Quakes after pass"+ i);
            for (QuakeEntry qe: in) { 
                System.out.println(qe);
            }
        }
    }

    public int getLargestDepth(ArrayList<QuakeEntry> quakes, int from){
        int maxIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getDepth() > quakes.get(maxIdx).getDepth()) {
                maxIdx = i;
            }
        }
        return maxIdx;

    }

    public void sortByLargestDepth(ArrayList<QuakeEntry> in){
        //for (int i=0; i< in.size(); i++) {
        for (int i=0; i< 50; i++) {
            int maxIdx = getLargestDepth(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(maxIdx);
            in.set(i,qmax);
            in.set(maxIdx,qi);
        }
    }

    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in){
        
        int cnt = 0;
        for (int i=0; i< in.size(); i++) {
            boolean chck = checkInSortedOrder(in);
            if (chck==true){
                break;
            }
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);            
            cnt = cnt+1;
        }
        System.out.println(cnt + "Passes needed");

    }

    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }

    public void sortByMagnitude(ArrayList<QuakeEntry> in) {

        for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }

    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/earthquakeDataSampleSix1.atom";
        //String source = "data/earthquakeDataSampleSix2.atom";
        //String source = "data/nov20quakedata.atom";
        //String source = "data/earthQuakeDataWeekDec6sample2.atom";
        
        String source = "data/earthQuakeDataDec6sample2.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
        sortByLargestDepth(list);
        System.out.println(list.get(list.size()-1).getDepth());
        
        //sortByMagnitude(list); 
        //sortByMagnitudeWithBubbleSort(list);
        //sortByMagnitudeWithBubbleSortWithCheck(list);
        
        String source2 = "data/earthQuakeDataWeekDec6sample2.atom";
        ArrayList<QuakeEntry> list2  = parser.read(source2);
        sortByMagnitudeWithCheck(list2);
        
        String source3 = "data/earthQuakeDataWeekDec6sample1.atom";
        ArrayList<QuakeEntry> list3  = parser.read(source3);
        sortByMagnitudeWithBubbleSortWithCheck(list3);
      
        /*System.out.println("read data for "+list3.size()+" quakes");  
        System.out.println("EarthQuakes in sorted order:");
        //int c = 0;
        for (QuakeEntry qe: list3) { 
            System.out.println(qe);
        } */

    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
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
}
