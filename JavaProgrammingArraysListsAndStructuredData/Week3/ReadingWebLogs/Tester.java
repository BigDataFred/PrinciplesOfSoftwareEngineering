
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer LA = new LogAnalyzer();
        LA.readFile("short-test_log");
        LA.printAll();
    }
    
    public void testUniqIPs(){
        LogAnalyzer LA = new LogAnalyzer();
        LA.readFile("weblog2_log");
        int uniqIPs = LA.countUniqueIPs();
        System.out.println("There are "+uniqIPs+" IPs");
    }
    
    public void testStatusCode(){
        LogAnalyzer LA = new LogAnalyzer();
        LA.readFile("weblog1_log");
        LA.printAllHigherThanNum(400);
    }
    
    public void testerUniqIPsOnDat(){
        LogAnalyzer LA = new LogAnalyzer();
        LA.readFile("weblog-short_log");
        ArrayList<String>al1 = LA.uniqueIPVisitsOnDay("Sep 14");
        System.out.println("Sep 14");
        for (int k=0;k<al1.size();k++){
            System.out.println(al1.get(k));
        }
        ArrayList<String>al2 = LA.uniqueIPVisitsOnDay("Sep 30");
        System.out.println("Sep 30");
        for (int k=0;k<al2.size();k++){
            System.out.println(al2.get(k));
        }
        
        LA = new LogAnalyzer();
        LA.readFile("weblog2_log");
        ArrayList<String>al3 = LA.uniqueIPVisitsOnDay("Sep 27");
        System.out.println("Mar 24");
        System.out.println(al3.size());
        for (int k=0;k<al3.size();k++){
            //System.out.println(al3.get(k));
            System.out.println(k);
        }
        
    }
    
    public void testerUniqIPsRange(){
        LogAnalyzer LA = new LogAnalyzer();
        LA.readFile("weblog1_log");
        int cnt = LA.countUniqueIPsInRange(200,299);
        System.out.println(cnt);
        cnt = LA.countUniqueIPsInRange(300,399);
        System.out.println(cnt);
        
        LogAnalyzer LA2 = new LogAnalyzer();
        LA2.readFile("weblog2_log");
        int cnt2 = LA2.countUniqueIPsInRange(400,499);
        System.out.println(cnt2);
    }
    
    public void testCountVisitsPerIP(){
        LogAnalyzer LA = new LogAnalyzer();
        LA.readFile("weblog3-short_log");
        HashMap<String,Integer> counts = LA.countVisitsPerIP();
        for (String s: counts.keySet()){
            System.out.println("IP "+s+"count "+counts.get(s));
        }
    }
    
    public void testMostVisitsByIP(){
        LogAnalyzer LA = new LogAnalyzer();
        LA.readFile("weblog2_log");
        HashMap<String,Integer> counts = LA.countVisitsPerIP();
        int m = LA.mostNumberVisitsByIP(counts);
        System.out.println("max number of visits is "+m);
        ArrayList<String> maxIP = LA.iPsMostVisits(counts);
        for (String s : maxIP){
            System.out.println(s);
        }
    }
    
    public void testIPsForDays(){
        LogAnalyzer LA = new LogAnalyzer();
        LA.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> IP2days = LA.iPsForDays();
        System.out.println(IP2days);
    }
    
    public void testDayWithMostIPVisits(){
        LogAnalyzer LA = new LogAnalyzer();
        LA.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> IP2days = LA.iPsForDays();
        String mD = LA.dayWithMostIPVisits(IP2days);
        System.out.println(mD);
        ArrayList<String> maxIPs = LA.iPsWithMostVisitsOnDay(IP2days,mD);
        System.out.println(maxIPs);
        
        maxIPs = LA.iPsWithMostVisitsOnDay(IP2days,"Sep 30");
        System.out.println(maxIPs);
    }
}
