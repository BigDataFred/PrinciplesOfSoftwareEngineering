
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

public class MarkovTwo {
    private String myText;
    private Random myRandom;

    public MarkovTwo() {
        myRandom = new Random();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
    }

    public ArrayList<String> getFollows(String key){
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

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuffer sb = new StringBuffer();
        
        int index = myRandom.nextInt( myText.length()-2 );
        String key = myText.substring(index,index+2);
        sb.append(key);
        
        for(int k=0; k < numChars-2; k++){
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

