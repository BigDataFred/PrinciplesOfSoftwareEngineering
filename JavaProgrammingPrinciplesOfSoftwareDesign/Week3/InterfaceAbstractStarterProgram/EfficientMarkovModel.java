
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

public class EfficientMarkovModel extends AbstractMarkovModel {

    private int modelOrder;
    private HashMap <String, ArrayList>  followMap;

    public EfficientMarkovModel(int N) {
        modelOrder = N;
        followMap = new HashMap <String, ArrayList>();
    }

    public String toString2(){
        String st = "EfficientMarkovModel of order " + modelOrder;
        return st;
    }

    public void buildMap(String key){
        ArrayList <String> follows = new ArrayList<String>();       
        int pos = 0;

        if ( (followMap.size()==0 ) || ( followMap.containsKey(key) == false ) ){
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
            followMap.put(key,follows);
        }

        //printHashMapInfo();
    }

    public ArrayList<String> getFollows(String key){
        ArrayList<String> follows = followMap.get(key); 
        return follows;
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuffer sb = new StringBuffer();

        for (int k=0; k<myText.length()-(modelOrder-1);k++){
            String key2 = myText.substring(k,k+modelOrder);
            buildMap(key2);
        }

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

    public void printHashMapInfo(){
        int maxArraySize = 0;
        HashMap <String,Integer> maxKeyList = new HashMap <String,Integer>();
        for (String s : followMap.keySet()) {
            //System.out.println("key:" + s);
            //System.out.println("values:" + followMap.get(s) );
            int curArraySize = followMap.get(s).size();
            maxKeyList.put(s,curArraySize);
            if (curArraySize>=maxArraySize){
                maxArraySize = curArraySize;              
            }
            //System.out.println("---------------------------");
        } 
        System.out.println("HashMap has " + followMap.size() + " key entries");
        System.out.println("The maximum number of keys following a key is " + maxArraySize );
        System.out.println("Keys that have the largest ArrayList (of size " + maxArraySize+ " ) are:");
        int cnt = 0;
        for (String s : maxKeyList.keySet()) {
            int arraySize = maxKeyList.get(s);
            if (arraySize == maxArraySize){
                System.out.println("key '" + s + "' has " + maxArraySize + " entries");
            }
        } 

    }

}

