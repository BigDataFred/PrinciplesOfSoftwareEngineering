
/**
 * Write a description of class CommonWords here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.Random;

public class CommonWords {
    
    public String[] getCommon(){
        FileResource fr = new FileResource("CommonWordsData/common.txt");
        String[] common = new String[20];
        int index = 0;
        for (String s : fr.words()){
            common[index] = s;
            index +=1;
        }
        return common;
    }
    
    public void testGetCommon(){
        
        String[] common = getCommon();
        for (int k = 0; k<common.length;k++){
            System.out.println(common[k]);
        }
        
    
    }
    
    public int indexOf(String[] list, String word){
        for (int k = 0;k<list.length;k++){
            if (list[k].equals(word)){
                return k;
            }
        }
        return -1;
    }
    
    public void countWords(FileResource fr, String[] common, int[] counts){
        for (String s: fr.words()){
           s = s.toLowerCase();
           int index = indexOf(common,s);
           if (index!=-1){
               counts[index] +=1;
            }
        }
    }
    
    public void countShakespeare(){
        String path2Dat = "CommonWordsData/";
        String[] plays={"caesar.txt","errors.txt","hamlet.txt","likeit.txt",
                            "macbeth.txt","romeo.txt"};
                           
        String[] common = getCommon();
        int [] counts = new int[common.length];
        for (int k=0;k<plays.length;k++){
            FileResource fr = new FileResource(path2Dat+plays[k]);
            countWords(fr,common,counts);
            System.out.println("Done with\t" + plays[k]);
        }
        
        for (int k=0;k<common.length;k++){
            System.out.println(common[k] + "\t" + counts[k]);
        }
    }
    
}
