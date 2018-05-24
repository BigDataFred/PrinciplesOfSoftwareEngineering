
/**
 * Write a description of class DecryptingCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.Random;

public class DecryptingCaesarCipher {

    public int[] countCharOccurences(String message){
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[alpha.length()];
        for (int k = 0;k<message.length();k++){
            char ch = Character.toLowerCase(message.charAt(k));
            int indx = alpha.indexOf(ch);
            if (indx !=-1){
                counts[indx] +=1;
            }
        }
        return counts;
    }

    public void testCountCharOccurences(){
        int[] counts = countCharOccurences("abcdefghijklmnopqrstuvwxyz");
        for (int k = 0; k<counts.length;k++){
            System.out.println(counts[k]);
        }
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

    public String decrypt(String encrypted, char mostFreqChar){
        CaesarCipher cc = new CaesarCipher();
        String alpha = "abcdefghijklmnopqrstuvwxyz"; 
        int refIdx = 0;
        for (int k = 0;k<alpha.length();k++){
            char ch = alpha.charAt(k);
            if (ch == Character.toLowerCase(mostFreqChar)){
                refIdx = k;
            }
        }
        int[] freqs = countCharOccurences(encrypted);
        int maxIdx = maxIndex(freqs);
        int dkey = maxIdx-refIdx;
        if (maxIdx<refIdx){
            dkey = alpha.length() - (refIdx-maxIdx);
        }
        return cc.encrypt(encrypted,26-dkey);
    }

    public void BruteForceDecrypt(){
        String alpha = "ABCDEGFGHIJKLMNOPQRSTUVWXYZ";
        String encrypted = "CFOPQ IBDFLK XQQXZH BXPQ CIXKH!";
        for (int k=0;k<alpha.length();k++){
            char ch = alpha.charAt(k);
            String decrypted = decrypt(encrypted,ch);
            System.out.println(decrypted);
        }
    }

    public String halfOfString(String message, int start){
        String answer ="";
        for (int k = start; k< message.length() ; k+= 2) {
            answer = answer + message.charAt(k);    	
        }
        return answer;
    }

    public void testHalfOfString(){
        String s1 = halfOfString("Qbkm Zgis",0);
        String s2 = halfOfString("Qbkm Zgis",1);
        System.out.println(s1);
        System.out.println(s2);
    }

    public int getKey(String s, int refIdx){
        int[] freqs = countCharOccurences(s);
        int idx = maxIndex(freqs);

        int key = idx-refIdx;
        if (idx<refIdx){
            key = 26 - (refIdx-idx);
        }

        return key;
    }

    public void decryptTwoKeys(String encrypted,int refIdx){
        String s1 = halfOfString(encrypted,0);
        String s2 = halfOfString(encrypted,1);
        int key1 = getKey(s1,refIdx);
        int key2 = getKey(s2,refIdx);
        System.out.println(key1);
        System.out.println(key2);
        CaesarCipher cc = new CaesarCipher();
        String decrypted = cc.encryptTwoKeys(encrypted,26-key1,26-key2);

        System.out.println(decrypted);
    }

    
    public void decryptTwoKeys2(String encrypted,int key1,int key2){
        
        CaesarCipher cc = new CaesarCipher();
        String decrypted = cc.encryptTwoKeys(encrypted,26-key1,26-key2);

        System.out.println(decrypted);
    }
    
    public void testDecryptTwoKeys(){
        String encrypted = "";
        
        encrypted = "Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu";
        decryptTwoKeys(encrypted,4);

        encrypted = "Top ncmy qkff vi vguv vbg ycpx";
        decryptTwoKeys2(encrypted,2,20);
        //// try brute force
        //for (int k=0;k<26;k++){
        //    decryptTwoKeys(encrypted,k);
        //}
        
        encrypted = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
        decryptTwoKeys(encrypted,4);

        FileResource resource = new FileResource("PracticeBreakingCaesarData/mysteryTwoKeysPractice.txt");
        String newS = "";
        int cnt = 0;
        for (String word : resource.words()){
            cnt = cnt+1;
            if (cnt ==1){
                newS = word;
            }else { 
                newS = newS + " "+ word;
            }
        }
        decryptTwoKeys(newS,4);
        
        encrypted = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        decryptTwoKeys2(encrypted,14,24);
    }
}
