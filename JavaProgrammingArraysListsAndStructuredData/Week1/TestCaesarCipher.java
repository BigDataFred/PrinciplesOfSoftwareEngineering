
/**
 * Write a description of class TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.Random;

public class TestCaesarCipher {
    
    public int[] countLetters(String input){
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[alpha.length()];
        for (int k = 0;k<input.length();k++){
            char ch = Character.toLowerCase(input.charAt(k));
            int indx = alpha.indexOf(ch);
            if (indx !=-1){
                counts[indx] +=1;
            }
        }
        return counts;
    }
    
    public int maxIndex(int[] vals){
        int maxIdx = 0;
        for (int k=0;k<vals.length;k++){
            if (vals[k]>vals[maxIdx]){
                maxIdx = k;
            }
        }
        return maxIdx;
    }
    
    public void simpleTests(){
        FileResource fr = new FileResource();
        CaesarCipherOO cc = new CaesarCipherOO(18);
        
        String s = fr.asString();
        System.out.println(s);
       
        String es = cc.encrypt(s);
        System.out.println(es);
        
        String ds = cc.decrypt(es);
        System.out.println(ds);
        
        String ds2 = breakCaesarCipher(es);
        System.out.println(ds2);
    }
    
    public String breakCaesarCipher(String input){        
        int [] count = countLetters(input);
        int key = maxIndex(count)-4;
        CaesarCipherOO cc = new CaesarCipherOO(count.length-key);
        String ds = cc.encrypt(input);
        return ds;
    }
}
