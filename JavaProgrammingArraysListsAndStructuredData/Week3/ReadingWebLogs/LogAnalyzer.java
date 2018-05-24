
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        // complete constructor
        records = new ArrayList<LogEntry>();
    }

    public void readFile(String filename) {
        // complete method
        FileResource fr = new FileResource(filename);
        for (String s : fr.lines()){
            LogEntry le = WebLogParser.parseEntry(s);
            records.add(le);
        }
    }

    public int countUniqueIPs(){
        ArrayList <String> uniqueIPs = new ArrayList<String>();

        for (LogEntry le : records){
            String ipAddr = le.getIpAddress();
            if (!uniqueIPs.contains(ipAddr)){
                uniqueIPs.add(ipAddr);
            }
        }

        return uniqueIPs.size();
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday){
        ArrayList <String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records){
            Date d = le.getAccessTime();
            String str = d.toString();
            String ipAddr = le.getIpAddress();
            if (str.contains(someday)){
                if (!uniqueIPs.contains(ipAddr)){
                    uniqueIPs.add(ipAddr);
                }
            }
        }
        return uniqueIPs;

    }

    public int countUniqueIPsInRange(int low,int high){
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records){
            String ipAddr = le.getIpAddress();
            int status = le.getStatusCode();
            if (status >=low && status <=high){
                if (!uniqueIPs.contains(ipAddr)){
                    uniqueIPs.add(ipAddr);
                }
            }
        }
        return uniqueIPs.size();
    }

    public void printAllHigherThanNum(int num){
        //This method should examine all the web log entries in records and
        //print those LogEntrys that have a status code greater than num.
        for (LogEntry le : records){
            int status = le.getStatusCode();
            if (status > num){
                System.out.println(le);
            }
        }
    }

    public HashMap<String,Integer> countVisitsPerIP(){
        HashMap<String,Integer>counts = new HashMap<String,Integer>();
        for ( LogEntry le : records ) {
            String ipAddr = le.getIpAddress();
            if (!counts.containsKey(ipAddr)){
                counts.put(ipAddr,1);
            } else {
                counts.put(ipAddr,counts.get(ipAddr)+1);
            }
        }
        return counts;
    }

    public int mostNumberVisitsByIP(HashMap<String,Integer> counts){
        int m = 0;
        for (String s : counts.keySet()){    
            int cnt = counts.get(s);
            if (cnt>m){
                m = cnt;
            }
        }
        return m;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts){
        ArrayList<String> maxIPs = new ArrayList<String>();
        int m = mostNumberVisitsByIP(counts);
        for (String s : counts.keySet()){    
            int cnt = counts.get(s);
            if (cnt==m){
                maxIPs.add(s);
            }
        }
        return maxIPs;
    }

    public HashMap<String, ArrayList<String>> iPsForDays(){
        HashMap<String, ArrayList<String>> IP2day = new HashMap<String, ArrayList<String>>();
        ArrayList<String> days = new ArrayList<String>();
        for (LogEntry le : records){
            Date d = le.getAccessTime();
            String str = d.toString();
            str = str.substring(4,10);
            if (!days.contains(str)){
                days.add(str);
            }
        }

        for (String c : days){
            ArrayList<String> aL = new ArrayList<String>();
            for (LogEntry le : records){
                Date d = le.getAccessTime();
                String str = d.toString();
                str = str.substring(4,10);
                String ipAddr = le.getIpAddress();
                if (c.equals(str)){    
                    aL.add(ipAddr);
                    IP2day.put(c,aL);
                }
            }
        }
        return IP2day;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> IP2day){
        String mD = "";
        int m= 0;
        for (LogEntry le : records){
            Date d = le.getAccessTime();
            String str = d.toString();
            str = str.substring(4,10);
            ArrayList<String>aL = IP2day.get(str);
            int cnt = aL.size();
            if (cnt>m){
                m  = cnt;
                mD = str; 
            }
        }
        return mD;
    }
    
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> IP2day,String mD){
        ArrayList<String> aL = IP2day.get(mD);
        HashMap <String,Integer> IPsCnt = new HashMap<String,Integer>();
        for (String s : aL){
            if (!IPsCnt.containsKey(s)){
                IPsCnt.put(s,1);
            } else {
                IPsCnt.put(s,IPsCnt.get(s)+1);
            }
        
        }
        ArrayList<String> maxIP = iPsMostVisits(IPsCnt);
        return maxIP;
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

}
