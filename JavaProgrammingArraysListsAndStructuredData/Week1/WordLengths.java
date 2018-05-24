
/**
 * Write a description of class WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.Random;

public class WordLengths {

    public int indexOfMax(int [] values){
        int indexOfMax = 0;
        for (int k=0;k<values.length;k++){
            if (values[k]>values[indexOfMax]){
                indexOfMax = k;
            }
        }
        return indexOfMax;
    }

    public String removeNonLetter(String s){
        if (s.length()>1){
            while(Character.isLetter( s.charAt(0) )== false){
                s = s.substring(1);
                if (s.length()==1){
                    break;
                }
            }

            while(Character.isLetter( s.charAt(s.length()-1) )== false){
                if (s.length()==1){
                    break;
                }
                s = s.substring(0,s.length()-1);

            }
        }
        return s;
    }

    public void countWordLengths(FileResource resource,int [] counts){
        //This method should read in the words from resource and count the number 
        //of words of each length for all the words in resource, storing these 
        //counts in the array counts.
        String [] words = new String[counts.length];

        for (String s: resource.words()){

            s = removeNonLetter(s);

            int idx = s.length();
            if (idx >0){
                counts[idx-1] +=1;
                if (counts[idx-1] > 1){
                    words[idx-1] = words[idx-1] + "\t" + s.substring(0,idx);
                } else {
                    words[idx-1] = s.substring(0,idx);
                }
            }
        }

        int mIx = indexOfMax(counts);

        int wL = 1;
        System.out.println("Length\tCounts\tWords");
        for (int k=0;k<counts.length;k++){
            if (words[k]!=null){
                System.out.println(wL + "\t" +counts[k] + "\t" + words[k]);
            }
            wL +=1;
        }
        System.out.println("The most common word length is\t" + counts[mIx]);
    }

    public void testCountWordLengths(){
        FileResource resource = new FileResource();
        int maxLength =0;
        for (String s: resource.words()){
            s = removeNonLetter(s);

            if (s.length() > maxLength){
                maxLength = s.length();
            }
        }
        System.out.println("The longest word has " + maxLength + " letters");

        int [] counts = new int[maxLength];
        countWordLengths(resource,counts);

    }

}
