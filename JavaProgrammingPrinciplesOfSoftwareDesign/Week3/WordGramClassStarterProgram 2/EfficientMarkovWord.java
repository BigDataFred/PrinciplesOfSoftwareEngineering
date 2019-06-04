
/**
 * Write a description of class EfficientMarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
public class EfficientMarkovWord implements IMarkovModel {

    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap < String,ArrayList<String> > followMap;

    public EfficientMarkovWord(int markovOrder) {
        myRandom = new Random();
        myOrder = markovOrder;
        followMap   = new HashMap < String,ArrayList<String> >();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
        buildMap();
        printHashMapInfo();
    }

    public int indexOf(String[] words, WordGram target, int start){
        ArrayList<WordGram> list = new ArrayList<WordGram>();
        for (int k=start;k<words.length-myOrder;k++){
            WordGram wg = new WordGram(words,k,myOrder);

            if ( target.equals(wg) ) {
                return k;
            }

        }
        return -1;
    }

    public void buildMap(){
        for (int k=0; k<myText.length-1;k++){

            if (k+myOrder <= myText.length-1){
                WordGram wg = new WordGram(myText,k,myOrder);
                String next = myText[k+myOrder];
                if ( followMap.containsKey( wg.toString() ) == false  ){
                    ArrayList <String> follows = new ArrayList <String>();
                    follows.add(next);
                    followMap.put(wg.toString(),follows);
                } else {
                    ArrayList <String> follows = followMap.get(wg.toString() );
                    follows.add(next);
                    followMap.put(wg.toString(),follows);
                }
            }

            if (k +myOrder > myText.length-1) {
                WordGram wg = new WordGram(myText,k,myOrder);
                if ( followMap.containsKey( wg.toString() ) == false  ){
                    ArrayList <String> follows = new ArrayList <String>();
                    followMap.put(wg.toString(),follows);
                }
                break;
            }
        }
    }

    public String getRandomText(int numWords){
        if (myText == null){
            return "";
        }

        StringBuilder sb = new StringBuilder();

        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram key = new WordGram(myText,index,myOrder);
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);       
            WordGram nextWg = key.shiftAdd(next);
            sb.append(nextWg.wordAt(myOrder-1));
            sb.append(" ");
            key = nextWg;
        }

        return sb.toString().trim();
    }

    private ArrayList<String> getFollows(WordGram kGram) {
        int hC = kGram.hashCode();
        return followMap.get(kGram.toString());
    }

    public void printHashMapInfo(){
        int maxVal = 0;
        for (String s : followMap.keySet() ) {
            // process each key in turn 
            ArrayList <String> follows = followMap.get(s);
            if ( follows.size() >maxVal){
                maxVal = follows.size();
            }
            System.out.println("key: " + s);
            for (String s2 : follows){
                System.out.println( s2 );
            }
        }
        System.out.println("Total number of keys in HashMap: " + followMap.size() );
        System.out.println("The maximum number of elements following a key is : " + maxVal);
        System.out.println("Keys with maximum value in HashMap are: " );

    }
    
}
