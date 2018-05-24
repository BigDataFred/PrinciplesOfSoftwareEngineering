
/**
 * Write a description of class TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.Random;

public class TestCaesarCipherTwo {
    
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
    
    public String halfOfString(String message, int start){
        String answer ="";
        for (int k = start; k< message.length() ; k+= 2) {
            answer = answer + message.charAt(k);        
        }
        return answer;
    }
    
        public int getKey(String s, int refIdx){
        int[] freqs = countLetters(s);
        int idx = maxIndex(freqs);

        int key = idx-refIdx;
        if (idx<refIdx){
            key = 26 - (refIdx-idx);
        }

        return key;
    }
    
    void simpleTests(){
        
        FileResource fr = new FileResource();
        CaesarCipherTwo cc = new CaesarCipherTwo(17,3);
        
        String s = fr.asString();
        System.out.println(s);
       
        String es = cc.encrypt(s);
        System.out.println(es);
        
        String ds = cc.decrypt(es);
        System.out.println(ds);
        
        String ds2 = breakCaesarCipher(es);
        System.out.println(ds2);
        
        String es2 = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        String ds3 = breakCaesarCipher(es2);
        System.out.println(ds3);
        
    }
    
    public String breakCaesarCipher(String input){    
        String s1 = halfOfString(input, 0);
        //System.out.println(s1);
        String s2 = halfOfString(input, 1);
        //System.out.println(s2);
        int key1 = getKey(s1,4);
        int key2 = getKey(s2,4);
        System.out.println(key1);
        System.out.println(key2);
        CaesarCipherTwo cc = new CaesarCipherTwo(26-key1,26-key2);
        String ds = cc.encrypt(input);
        return ds;
    }

}
