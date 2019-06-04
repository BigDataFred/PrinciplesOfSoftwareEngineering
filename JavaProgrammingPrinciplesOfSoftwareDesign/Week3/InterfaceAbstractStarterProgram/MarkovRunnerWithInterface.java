
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 
import java.util.*;

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov.toString2() );
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }

    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        int seed = 531;

        /*MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);

        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);

        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);

        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);*/
        
        EfficientMarkovModel mEff = new EfficientMarkovModel(5);
        runModel(mEff, st, size, seed);
        mEff.printHashMapInfo();

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

    public void testHashMap(){

        /*int seed = 42;
        int size = 50;
        String st = "yes-this-is-a-thin-pretty-pink-thistle";
        st = st.replace('\n', ' ');
        EfficientMarkovModel mThree = new EfficientMarkovModel(2);
        runModel(mThree, st, size, seed);
        mThree.printHashMapInfo();*/
        
        
        int seed = 615;
        int size = 10;
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        
        EfficientMarkovModel mThree = new EfficientMarkovModel(5);
        runModel(mThree, st, size, seed);
        mThree.printHashMapInfo();

    }

    public void compareMethods(){
        int seed = 42;
        int size = 1000;
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');

        long maxTime1 = 0;
        for (int k =0;k<=3;k++){
            long time0 = System.nanoTime();
            MarkovModel model1 = new MarkovModel(2);
            runModel(model1, st, size, seed);
            long dt = System.nanoTime() - time0;
            if (dt > maxTime1){
                maxTime1 = dt;
            }
        }

        long maxTime2 = 0;
        for (int k =0;k<=3;k++){
            long time0 = System.nanoTime();
            EfficientMarkovModel model2 = new EfficientMarkovModel(2);
            runModel(model2, st, size, seed);
            long dt = System.nanoTime() - time0;
            if (dt > maxTime2){
                maxTime2 = dt;
            }
        }
        System.out.println("Model 1 " + (double)maxTime1 / 1000000000.0 );
        System.out.println("Model 2 " + (double)maxTime2 / 1000000000.0 );
    }

}
