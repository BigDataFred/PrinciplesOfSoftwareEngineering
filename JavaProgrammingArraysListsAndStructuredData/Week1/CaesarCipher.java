
/**
 * Write a description of class CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CaesarCipher {

    public String encrypt(String input,int key){
        StringBuilder encrypted = new StringBuilder(input);
        String abcU = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String abcL = abcU.toLowerCase();
        String shifedAbcU = abcU.substring(key) + abcU.substring(0,key);
        String shifedAbcL = abcL.substring(key) + abcL.substring(0,key);

        for (int curChar=0;curChar<encrypted.length();curChar++){
            char ch1 = encrypted.charAt(curChar);
            if (Character.isUpperCase(ch1)){
                int idx = abcU.indexOf(ch1);
                if (idx !=-1){
                    char newChar = shifedAbcU.charAt(idx);
                    encrypted.setCharAt(curChar,newChar);
                }
            } 
            else {
                int idx = abcL.indexOf(ch1);
                if (idx !=-1){
                    char newChar = shifedAbcL.charAt(idx);
                    encrypted.setCharAt(curChar,newChar);
                }
            }
        }
        return encrypted.toString();
    }

    public void testEncrypt(){
        String encrypted = encrypt("FIRST LEGION ATTACK EAST FLANK!",23);
        System.out.println(encrypted);
        String test ="CFOPQ IBDFLK XQQXZH BXPQ CIXKH!";
        System.out.println(test);
        System.out.println(encrypted.equalsIgnoreCase(test));

        encrypted = encrypt("First Legion",23);
        System.out.println(encrypted);
        test ="Cfopq Ibdflk";
        System.out.println(test);
        System.out.println(encrypted.equalsIgnoreCase(test));

        encrypted = encrypt("First Legion",17);
        System.out.println(encrypted);
        test ="Wzijk Cvxzfe";
        System.out.println(test);
        System.out.println(encrypted.equalsIgnoreCase(test));
        
        encrypted = encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!",15);
        System.out.println(encrypted);
        
        encrypted = encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?",15);
        System.out.println(encrypted);

    }

    public void testCaesar(){
        int key = 23;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
    
    public String encryptTwoKeys(String input, int key1, int key2){
        
        StringBuilder s1 = new StringBuilder(encrypt(input,key1));
        StringBuilder s2 = new StringBuilder(encrypt(input,key2));
        
        StringBuilder encrypted = new StringBuilder(s1);
        
        for (int curChar=0;curChar<s1.length();curChar++){
            if ((curChar %2)==0){
                //
            } else {
                encrypted.setCharAt(curChar,s2.charAt(curChar));
            }
        }
        
        return encrypted.toString();
    }
    
    public void testEncryptTwoKeys(){
        String encrypt = encryptTwoKeys("First Legion", 23, 17);
        System.out.println(encrypt);
        String test = "Czojq Ivdzle";
        System.out.println(test);
        System.out.println(encrypt.equalsIgnoreCase(test));
        
        encrypt = encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21);
        System.out.println(encrypt);
        
        encrypt = encryptTwoKeys("Can you imagine life WITHOUT the internet AND computers in your pocket?", 21, 8);
        System.out.println(encrypt);
        
    }
}
