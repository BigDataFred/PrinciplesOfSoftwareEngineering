
/**
 * Write a description of class whichCountriesExport here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.math.BigInteger;
import edu.duke.*;
import org.apache.commons.csv.*;

public class whichCountriesExport {
    public void listExporters(CSVParser parser, String exportOfInterest){
        for (CSVRecord record : parser){
            String Exports = record.get("Exports");
            if (Exports.contains(exportOfInterest)){
                String Country = record.get("Country");
                System.out.println(Country);
            }
        }
    }

    public void whoExportsCoffee(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "Coffee");
    }

    public String countryInfo(CSVParser parser,String country){
        for (CSVRecord record : parser){
            String curCountry = record.get("Country");
            if (curCountry.equals(country)){
                return record.get("Country") + ":" + record.get("Exports");
            }
        }
        return "NOT FOUND";
    }

    public void testCountryInfo(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        String info = countryInfo(parser,"Nauru");
        System.out.println(info);
    }

    public void listExportersTwoProducts(CSVParser parser, String exportItem1, 
    String exportItem2){
        for (CSVRecord record : parser){
            String exports = record.get("Exports");
            boolean chck1 = exports.contains(exportItem1);
            boolean chck2 = exports.contains(exportItem2);
            if (chck1==true &&  chck2==true){
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }

    public void testListExportersTwoProducts(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();

        listExportersTwoProducts(parser, "cotton", "flowers");
    }

    public int numberOfExporters(CSVParser parser, String exportItem){
        int cnt = 0;
        for (CSVRecord record : parser){
            String Exports = record.get("Exports");
            if (Exports.contains(exportItem)){
                cnt = cnt+1;
            }
        }
        return cnt;
    }

    public void testNumberOfExporters(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        int num = numberOfExporters(parser, "cocoa");
        System.out.println(num);
    }

    public void bigExporters(CSVParser parser, String amount){
        String temp1 = amount.replace(",","");
        for (CSVRecord record : parser){
            String exportValue = record.get("Value (dollars)");
            if (exportValue.length()>amount.length()){
                    String country = record.get("Country");
                    System.out.println(country + " " + exportValue);
            }
        }
    }

    public void testBigExporters(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();

        bigExporters(parser, "$999,999,999,999”" );

    }
}
