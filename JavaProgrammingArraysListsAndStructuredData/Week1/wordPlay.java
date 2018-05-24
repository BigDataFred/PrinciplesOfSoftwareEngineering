
/**
 * Write a description of class wordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class wordPlay {

    public boolean isVowel(char ch){
        boolean out = false;
        int idx;
        ch = Character.toUpperCase(ch);
        String chNew = Character.toString(ch);
        System.out.println(chNew);
        String[] vowels = {"A", "E", "I", "O", "U"};
        for (int curVow=0; curVow< vowels.length; curVow++){
            idx = chNew.indexOf(vowels[curVow]);
            if (idx!=-1){
                out = true;
            };
        }
        return out;
    }

    public void testVowerl(){
        char ch;
        ch ='e';
        boolean chck = isVowel(ch);
        if (chck == true){
            System.out.println("char is a vowel");
        } 
        else{
            System.out.println("char is not a vowel");
        }

    }

    public String replaceVowels(String phrase,char ch){
        char ch2;
        StringBuilder sb = new StringBuilder(phrase);
        for (int curChar=0; curChar < phrase.length(); curChar++){
            ch2 = phrase.charAt(curChar);
            boolean chck = isVowel(ch2);
            if (chck == true){
                sb.setCharAt(curChar,ch);
            } 
        }
        return sb.toString();
    }

    public void testReplaceVowels(){
        String s = replaceVowels("Hello World",'*');
        System.out.println(s);
    }

    public String emphasize(String phrase,char ch){
        char ch2;
        StringBuilder sb = new StringBuilder(phrase);
        for (int curChar=0; curChar < phrase.length(); curChar++){
            ch2 = phrase.charAt(curChar);
            if (Character.toUpperCase(ch)==Character.toUpperCase(ch2)){
                if ((curChar % 2)==0){
                    sb.setCharAt(curChar,'*');
                } else{
                    sb.setCharAt(curChar,'+');
                }
            }
        }
        return sb.toString();
    }

    public void testEmphasize(){
        String s = emphasize("Mary Bella Abracadabra",'a');
        System.out.println(s);
    }
}

