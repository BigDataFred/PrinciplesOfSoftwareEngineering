
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    protected ArrayList<String> getFollows(String key){
        ArrayList <String> follows = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length() ){
            int index = myText.indexOf(key,pos);
            if (index ==-1){
                break;
            }

            if ( (index+key.length() > myText.length()-1) ){
                break;
            }
            String next  = myText.substring(index+key.length(),index+key.length()+1);
            follows.add(next);
            pos = index+1;

        }
        return follows;
    }
    
    abstract public String getRandomText(int numChars);
    
    abstract public String toString2();
}
