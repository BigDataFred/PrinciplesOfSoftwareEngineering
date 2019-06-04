
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

public class MarkovOne extends AbstractMarkovModel {
        
    public String toString2(){
        String st = "MarkovModel of order 1";
        return st;
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuffer sb = new StringBuffer();

        int index = myRandom.nextInt( myText.length()-1 );
        String key = myText.substring(index,index+1);
        sb.append(key);

        for(int k=0; k < numChars; k++){
            ArrayList<String> follows = getFollows(key); 
            if( follows.size() == 0 ){ 
                break; 
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = next;
        }

        return sb.toString();
    }
}
