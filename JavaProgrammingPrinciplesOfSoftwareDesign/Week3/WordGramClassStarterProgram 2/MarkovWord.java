
/**
 * Write a description of class MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWord implements IMarkovModel {

    private String[] myText;
    private Random myRandom;
    private int myOrder;

    public MarkovWord(int markovOrder) {
        myRandom = new Random();
        myOrder = markovOrder;
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
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

    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();

        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram key = new WordGram(myText,index,myOrder);
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }

            index = myRandom.nextInt(follows.size());
            
            String next = follows.get(index);       
            //System.out.println(key);
            //System.out.println(next);
            WordGram nextWg = key.shiftAdd(next);
            //System.out.println(nextWg.wordAt(myOrder-1));
            sb.append(nextWg.wordAt(myOrder-1));
            sb.append(" ");
            key = nextWg;
        }

        return sb.toString().trim();
    }

    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while (pos<myText.length-1){
            int index = indexOf(myText,kGram,pos);
            if ( index == -1 ){
                break;
            }

            if ( index +myOrder >= myText.length-1 ){
                break;
            }
            String next = myText[index+myOrder];
            follows.add(next);
            pos = index+myOrder;
        }
        return follows;
    }

}
