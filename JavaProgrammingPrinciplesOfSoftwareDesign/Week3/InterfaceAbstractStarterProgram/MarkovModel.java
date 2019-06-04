
/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */
import java.lang.*;
import java.io.*;
import java.util.*;
import java.text.*;
import edu.duke.*;

public class MarkovModel extends AbstractMarkovModel {
    
    private int modelOrder;
    
    public MarkovModel(int N) {
        modelOrder = N;
    }
    
    public String toString2(){
        String st = "MarkovModel of order " + modelOrder;
        return st;
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuffer sb = new StringBuffer();
        
        int index = myRandom.nextInt( myText.length()-modelOrder );
        String key = myText.substring(index,index+modelOrder);
        sb.append(key);
        
        for(int k=0; k < numChars-modelOrder; k++){
            ArrayList<String> follows = getFollows(key); 
            if( follows.size() == 0 ){ 
                break; 
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }

        return sb.toString();
    }
}

