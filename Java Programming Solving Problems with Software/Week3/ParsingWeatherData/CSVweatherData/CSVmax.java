
/**
 * Write a description of class CSVmax here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVmax {

    public CSVRecord hottestHourInFile(CSVParser parser){
        CSVRecord largestSofar = null;

        for (CSVRecord currentRow : parser ){
            largestSofar = getLargestOfTwo( currentRow, largestSofar);
        }

        return largestSofar;
    }

    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord smallestSofar = null;
        for (CSVRecord currentRow : parser ){
            if ( Double.parseDouble(currentRow.get("TemperatureF")) != -9999){
                smallestSofar = getSmallestOfTwo( currentRow, smallestSofar,"TemperatureF");
            }   
        }

        return smallestSofar;
    }

    public void testColdestHourInFile(){
        FileResource fr = new FileResource("nc_weather/2014/weather-2014-05-01.csv");
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature on that day was " + smallest.get("TemperatureF") + "at " 
            + smallest.get("TimeEDT"));
    }

    public void testHottestInDay(){
        FileResource fr = new FileResource("nc_weather/2015/weather-2015-01-02.csv");
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("hottest temperature was " + largest.get("TemperatureF") + "at " 
            + largest.get("TimeEST"));
    }

    public CSVRecord getLargestOfTwo(CSVRecord currentRow, CSVRecord largestSoFar){
        if (largestSoFar == null){
            largestSoFar = currentRow;
        } else {
            double currentTemp = Double.parseDouble( 
                    currentRow.get("TemperatureF"));

            double largesttTemp = Double.parseDouble( 
                    largestSoFar.get("TemperatureF"));

            if (currentTemp>largesttTemp){
                largestSoFar = currentRow;
            }
        }
        return largestSoFar;
    }

    public CSVRecord getSmallestOfTwo(CSVRecord currentRow, CSVRecord smallestSoFar, String Parameter){

        if (smallestSoFar == null){
            smallestSoFar = currentRow;
        } else {
            double currentTemp = Double.parseDouble( 
                    currentRow.get(Parameter));

            double smallestTemp = Double.parseDouble( 
                    smallestSoFar.get(Parameter));

            if (currentTemp<smallestTemp){
                smallestSoFar = currentRow;
            }
        }
        return smallestSoFar;
    }

    public CSVRecord hottestInManyDays(){
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord current = hottestHourInFile(fr.getCSVParser());

            largestSoFar =  getLargestOfTwo( current, largestSoFar);

        }
        return largestSoFar;
    }

    public void testHottestInManyDays(){
        CSVRecord largest = hottestInManyDays();
        System.out.println("hottest temperature was " + largest.get("TemperatureF") + "at " 
            + largest.get("DateUTC"));
    }

    public String fileWithColdestTemperature(){
        String currentName = "";
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){

            FileResource fr = new FileResource(f);
            CSVRecord current = coldestHourInFile(fr.getCSVParser());

            smallestSoFar =  getSmallestOfTwo( current, smallestSoFar,"TemperatureF");

            if (current == smallestSoFar){
                currentName = f.getName();
            }

        }
        return currentName;
    }

    public void testFileWithColdestTemperature(){
        String coldest = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + coldest);
        FileResource fr = new FileResource("nc_weather/2013/"+coldest);
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature on that day was " + smallest.get("TemperatureF") + "at " 
            + smallest.get("TimeEST"));
        CSVRecord smallestSofar = null;

        System.out.println("All the Temperatures on the coldest day were:");
        for (CSVRecord currentRow : fr.getCSVParser() ){
            String currentTemp = currentRow.get("TemperatureF"); 
            String currentTime = currentRow.get("TimeEST"); 
            String currentDate = currentRow.get("DateUTC"); 
            System.out.println(currentDate +  ": " + currentTemp);
        }
    }

    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord smallestSofar = null;
        for (CSVRecord currentRow : parser ){
            String curHum = currentRow.get("Humidity");
            //System.out.println(curHum);
            if ( curHum.equals("N/A")){
            } else {
                smallestSofar = getSmallestOfTwo( currentRow, smallestSofar,"Humidity");
            }   
        }

        return smallestSofar;
    }

    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest humidity on that day was " + csv.get("Humidity") + " at " 
            + csv.get("DateUTC"));
    }

    public void lowestHumidityInManyFiles(){
        String currentName = "";
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord current = lowestHumidityInFile(fr.getCSVParser());

            lowestSoFar =  getSmallestOfTwo( current, lowestSoFar,"Humidity");

        }
        System.out.println(lowestSoFar.get("Humidity"));
        System.out.println(lowestSoFar.get("DateUTC"));
        //return lowestSoFar;
    }

    public void testLowestHumidityInManyFiles(){
        //CSVRecord lowestH =  lowestHumidityInManyFiles();
        lowestHumidityInManyFiles();
        //System.out.println("toto");
        //System.out.println("Lowest humidity was " + lowestH.get("Humidity") + " at " 
        //    + lowestH.get("DateUTC"));
    }

    public double averageTemperatureInFile(CSVParser parser){
        double avgTemp = 0;
        double x = 0;
        double n = 0;
        for (CSVRecord currentRow : parser ){
            double curTemp = Double.parseDouble( currentRow.get("TemperatureF") );  
            x = x + curTemp;
            n = n+1;
        }
        avgTemp = x/n;
        return avgTemp;
    }

    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avgTemp = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + avgTemp );
    }

    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value ){
        double avgTemp = 0;
        double x = 0;
        double n = 0;
        for (CSVRecord currentRow : parser ){
            double curTemp = Double.parseDouble( currentRow.get("TemperatureF") );
            double curHumid = Double.parseDouble( currentRow.get("Humidity") );
            if (curHumid> (1.0 * value) ){
                x = x + curTemp;
                n = n+1;
            }
        }
        if (n!=0){
            avgTemp = x/n;
        }
        return avgTemp;
    }

    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avgTemp = averageTemperatureWithHighHumidityInFile(parser,80);
        if (avgTemp == 0){
            System.out.println("No temperatures with that humidity");
        } else {
            System.out.println("Average temperature in file is " + avgTemp );
        }
    } 
}
