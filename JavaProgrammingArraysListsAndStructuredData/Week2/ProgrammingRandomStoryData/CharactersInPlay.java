
/**
 * Write a description of class CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class CharactersInPlay {

    private ArrayList<String> characterName;
    private ArrayList<Integer> characterCount;

    public CharactersInPlay(){
        characterName = new ArrayList<String>();
        characterCount = new ArrayList<Integer>();
    }

    public void update(String person){
        int index = characterName.indexOf(person);
        if (index ==-1){
            characterName.add(person);
            characterCount.add(1);
        }else {
            int value = characterCount.get(index);
            characterCount.set(index,value+1);
        }
    }

    public void findAllCharacters(){
        characterName.clear();
        characterCount.clear();
        FileResource fr = new FileResource();
        for (String l:fr.lines()){
            int index = l.indexOf(".");
            if (index !=-1){
                String person = l.substring(0,index);
                update(person);
            }
        }

    }
    
    
    public void charactersWithNumParts(int num1, int num2){
        for (int k=0;k<characterName.size();k++){
            
            if (characterCount.get(k) >= num1 && characterCount.get(k)<=num2){
                System.out.println(characterName.get(k));
                System.out.println(characterCount.get(k));
            }
        }
    }
    
    public int findMax(){
        int max = characterCount.get(0);
        int maxIndex = 0;
        for(int k=0; k < characterCount.size(); k++){
            if (characterCount.get(k) > max){
                max = characterCount.get(k);
                maxIndex = k;
            }
        }
        return maxIndex;
    }
    
    public void tester(){
        findAllCharacters();
        //for (int k=0;k<characterName.size();k++){
        //    System.out.println(characterName.get(k));
        //    System.out.println(characterCount.get(k));
        //}
        
        charactersWithNumParts(50,150);
        //int mIx = findMax();
        //System.out.println(characterName.get(mIx));
        //System.out.println(characterCount.get(mIx));
    }
}
