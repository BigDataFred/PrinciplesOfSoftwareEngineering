
/**
 * Write a description of class CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.*;
import edu.duke.*;

public class CharactersInPlay {

    private ArrayList<String>characterName;
    private ArrayList<Integer>characterCount;

    public CharactersInPlay(){
        characterName = new ArrayList<String>();
        characterCount = new ArrayList<Integer>();
    }

    public void update(String character){
        int index = characterName.indexOf(character);
        if (index==-1){
            characterName.add(character);
            characterCount.add(1);
        } else {
            int value = characterCount.get(index);
            characterCount.set(index,value+1);
        }
    }

    public void findAllCharacters(){
        characterName.clear();//Make sure you clear the appropriate 
        characterCount.clear();//instance variables before each new file.
        FileResource fr = new FileResource();
        for (String l : fr.lines()){
            int index = l.indexOf(".");
            if (index !=-1){
                update(l.substring(0,index));
            }
        }
    }

    public void charactersWithNumParts(int num1,int num2){
        for (int k = 0;k<characterCount.size();k++){
            if (num1 !=0 && num2 !=0){
                if(characterCount.get(k) >= num1 && characterCount.get(k) <= num2){
                    System.out.println(characterName.get(k));
                }
            } else if (num1 !=0 && num2 ==0){
                if(characterCount.get(k) >= num1){
                    System.out.println(characterName.get(k));
                }
            } else if (num1 ==0 && num2 !=0){
                if(characterCount.get(k) <= num2){
                    System.out.println(characterName.get(k));
                }
            }
        }
    }
    
    public int findIndexOfMax(){
        int m = 0;
        int index = 0;
        for (int k=0;k<characterCount.size();k++){
            if (characterCount.get(k) > m){
                m = characterCount.get(k);
                index = k;
            }
        }
        return index;
    }
    
    public void tester(){
        findAllCharacters();        
        //for (int k=0;k<characterName.size();k++){
        //    System.out.println(characterName.get(k));
        //    System.out.println(characterCount.get(k));
        //}
        int mIx = findIndexOfMax();
        System.out.println(characterName.get(mIx) + " has " + characterCount.get(mIx) +" speaking parts");
        charactersWithNumParts(10,15);
    }
}
