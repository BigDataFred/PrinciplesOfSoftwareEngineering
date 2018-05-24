
/**
 * Write a description of class CeasarCipherOO here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.Random;

public class CaesarCipherOO {

    private String alpha;
    private String alphaShifted;
    private int mainKey;
    
    public CaesarCipherOO(int key){
        alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        alphaShifted = alpha.substring(key) + alpha.substring(0,key);
        mainKey = key;
    } 
    
    public String encrypt(String input){
        StringBuilder sb = new StringBuilder(input);
        for (int k=0;k<sb.length();k++){
            char c = sb.charAt(k);
            int idx = alpha.indexOf(Character.toUpperCase(c));
            if (idx !=-1){
                if (Character.isUpperCase(c)){
                    c = alphaShifted.charAt(idx);
                }else {
                    c = Character.toLowerCase(alphaShifted.charAt(idx));
                }
                sb.setCharAt(k,c);
            }
        }
        return sb.toString();
    }
    
    public String decrypt(String input){
        CaesarCipherOO cc = new CaesarCipherOO(alpha.length() - mainKey); 
        return cc.encrypt(input);
    }
}
