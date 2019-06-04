
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*;
import java.lang.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class MarkovRunner {

    public void runMarkovZero() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        //String st = "wohowoho wohowoho wohowoho wohowoho";
        MarkovZero markov = new MarkovZero();
        markov.setTraining(st);
        markov.setRandom(1024);
        
        for(int k=0; k < 3; k++){
            //markov.setRandom(18);
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }

    public void runMarkovOne() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        markov.setRandom(365);
        for(int k=0; k < 1; k++){
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }

    public void runMarkovTwo() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovTwo markov = new MarkovTwo();
        markov.setTraining(st);
        markov.setRandom(42);
        for(int k=0; k < 3; k++){
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }
    
    public void runMarkovFour() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovFour markov = new MarkovFour();
        markov.setTraining(st);
        markov.setRandom(715);
        for(int k=0; k < 3; k++){
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }
    
    public void runMarkovModel() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovModel markov = new MarkovModel();
        markov.setTraining(st);
        markov.setRandom(953);
        int numOfChar = 7;
        markov.setNumOfChar(numOfChar);
        for(int k=0; k < 3; k++){
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }
    
    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
}
