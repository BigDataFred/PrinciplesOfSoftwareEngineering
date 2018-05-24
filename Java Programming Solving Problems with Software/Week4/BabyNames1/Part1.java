
/**
 * Write a description of class Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Part1 {

    public void printNames(){
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <=100){
                System.out.println("Name : "+rec.get(0) +
                    "Gender " + rec.get(1) +
                    "Num born" + rec.get(2));
            }
        }      

    } 

    public void totalBirths(FileResource fr){
        int totalBirths = 0;
        int totalB = 0;
        int totalG = 0;
        int cnt = 0;
        for (CSVRecord rec : fr.getCSVParser(false)){
            cnt +=1;
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if(rec.get(1).equals("M")){
                totalB += numBorn;
            } 
            else{
                totalG += numBorn;
            }
        }  
        System.out.println("total births = "+totalBirths);
        System.out.println("total girls = "+totalG);
        System.out.println("total boys = "+ totalB);
        System.out.println("total names = "+ cnt);
    }

    public void testTotalBirths(){
        FileResource fr = new FileResource();
        totalBirths(fr);
    }

    public void totalGender(FileResource fr){
        int totalGender1 = 0;
        int totalGender2 = 0;
        for (CSVRecord rec : fr.getCSVParser(false)){
            if(rec.get(1).equals("M")){
                totalGender1 = totalGender1+1;
            } 
            else{
                totalGender2 = totalGender2+1;
            }
        }  
        System.out.println("total girls names = "+totalGender2);
        System.out.println("total boys names = "+ totalGender1);
    }

    public void testTotalGender(){
        FileResource fr = new FileResource();
        totalGender(fr);
    }

    public void uniqueBirths(FileResource fr){
        int uBirths = 0;
        int uB = 0;
        int uG = 0;
        int cnt = 0;
        for (CSVRecord rec : fr.getCSVParser(false)){
            cnt = cnt+1;
            int numBorn = Integer.parseInt(rec.get(2));
            if(numBorn ==1){
                uBirths += numBorn;
                if (rec.get(1).equals("M")){
                    uB += numBorn;
                }
                else{
                    uG += numBorn;
                }
            } 
        }  
        System.out.println("unique births = "+uBirths);
        System.out.println("unique girls = "+uG);
        System.out.println("unique boys = "+ uB);
        System.out.println("total names = "+ cnt);
    }

    public void testUniqueBirths(){
        FileResource fr = new FileResource();
        uniqueBirths(fr);
    }

    public int getRank(int year, String name, String gender ){
        int rank =0;
        int cnt = 0;
        FileResource fr = new FileResource("/Users/froux/Downloads/us_babynames/us_babynames_by_year/yob"+year+".csv");

        for (CSVRecord rec : fr.getCSVParser(false)){
            if (rec.get(1).equals(gender)){
                cnt = cnt+1;
                if (rec.get(0).equals(name)){
                    rank = cnt;
                }
            }
        }
        if (rank>0){
            return rank;
        }
        else{
            return -1;
        }
    }

    public void testGetRank(){
        int rank1 = getRank(1960,"Emily","F");
        System.out.println(rank1);

        rank1 = getRank(1971,"Frank","M");
        System.out.println(rank1);
    }

    public String getName(int year, int rank, String gender){
        String name = "NO NAME";
        int cnt = 0;
        FileResource fr = new FileResource("/Users/froux/Downloads/us_babynames/us_babynames_by_year/yob"+year+".csv");
        for (CSVRecord rec : fr.getCSVParser(false)){
            if ( rec.get(1).equals(gender)){
                cnt +=1;
                if (cnt==rank){
                    name = rec.get(0);
                }
            }
        }
        return name;
    }

    public void testGetName(){
        String name = getName(1980,350,"F");
        System.out.println(name);

        name = getName(1982,450,"M");
        System.out.println(name);

    }

    public void whatIsNameInYear(String name,int year,int newYear, String gender){
        FileResource fr1 = new FileResource("/Users/froux/Downloads/us_babynames/us_babynames_by_year/yob"+year+".csv");
        FileResource fr2 = new FileResource("/Users/froux/Downloads/us_babynames/us_babynames_by_year/yob"+newYear+".csv");
        int rank1 = 0;
        int rank2 = 0;
        for ( CSVRecord rec : fr1.getCSVParser(false) ){
            if ( rec.get(1).equals(gender) ){
                rank1 +=1;
                if ( rec.get(0).equals(name) ){
                    break;
                }
            }
        }

        for (CSVRecord rec : fr2.getCSVParser(false)){
            if ( rec.get(1).equals(gender) ){
                rank2 +=1;
                if ( rank2 == rank1 ){
                    System.out.println( rec.get(0) );
                }
            }
        }

    }

    public void testWhatIsNameInYear(){
        whatIsNameInYear("Susan",1972,2014,"F");     
        whatIsNameInYear("Owen",1974,2014,"M");  
    }

    public int yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int curRank = 0;
        int maxRank = 0;
        int hY  = 0;
        int cnt = 0;
        for ( File f : dr.selectedFiles() ){
            FileResource fr = new FileResource(f);
            cnt = cnt+1;
            for ( CSVRecord rec : fr.getCSVParser(false) ){

                if (rec.get(1).equals(gender)){
                    curRank = curRank+1;

                    if ( rec.get(0).equals(name) ){
                        if (cnt ==1){
                            maxRank = curRank;
                        }
                        if (curRank<maxRank){
                            maxRank = curRank;
                            String fName = f.getName();
                            int startIx = fName.indexOf("yob");
                            String x = fName.substring(startIx+3,startIx+7);
                            hY = Integer.parseInt(x);
                        }
                    } 
                }
            }
            curRank = 0;
        }
        if (hY>0){
            return hY;
        }else{
            return -1;
        }
    }

    public void testyearOfHighestRank(){
        int hY = yearOfHighestRank("Genevieve", "F");
        System.out.println("the year with the highest rank was " + hY);
        hY = yearOfHighestRank("Mich","M");
        System.out.println("the year with the highest rank was " + hY);
    }

    public double getAverageRank(String name, String gender){
        double avgRank = 0;
        double nFiles = 0;
        double curRank = 0;
        double chck = 0;
        DirectoryResource dr = new DirectoryResource();
        for ( File f : dr.selectedFiles() ){
            FileResource fr = new FileResource(f);
            nFiles =nFiles+1;
            for ( CSVRecord rec : fr.getCSVParser(false) ){
                if (rec.get(1).equals(gender)){
                    chck = chck +1;
                    curRank = curRank+1;
                    if ( rec.get(0).equals(name) ){
                        avgRank = avgRank+curRank;
                    } 
                }
            }
            curRank = 0;
        }
        avgRank = avgRank/nFiles;

        //if (chck == nFiles){
        return avgRank;
        //}
        //else{
        //    return -1;
        //}
    }

    public void testAvgRank(){
        //double aR1 = getAverageRank("Mason", "M");
        //System.out.println("average rank is " + aR1);

        double aR2 = getAverageRank("Susan", "F");
        System.out.println("average rank is " + aR2);

        aR2 = getAverageRank("Robert", "M");
        System.out.println("average rank is " + aR2);
    }

    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        FileResource fr = new FileResource("/Users/froux/Downloads/us_babynames/us_babynames_by_year/yob"+year+".csv");
        int totalBirths = 0;
        for ( CSVRecord rec : fr.getCSVParser(false) ){
            if ( rec.get(0).equals(name) && rec.get(1).equals(gender)){
                break;    
            } 

            if (rec.get(1).equals(gender)){
                totalBirths = totalBirths + Integer.parseInt(rec.get(2));
            }
        }
        return totalBirths;
    }

    public void testGetTotalBirthsRankedHigher(){

        int totalBirths = getTotalBirthsRankedHigher(1990, "Emily", "F");
        System.out.println(totalBirths);

        totalBirths = getTotalBirthsRankedHigher(1990, "Drew", "M");
        System.out.println(totalBirths);
    }
}
