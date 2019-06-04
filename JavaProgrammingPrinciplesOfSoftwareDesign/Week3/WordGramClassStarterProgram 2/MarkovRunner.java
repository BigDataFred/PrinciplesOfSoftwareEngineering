
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 1; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 1; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        /*FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWord markovWord = new MarkovWord( 3 ); 
        runModel(markovWord, st, 200, 621 );*/ 
        
        
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        EfficientMarkovWord markovWord = new EfficientMarkovWord(5); 
        runModel(markovWord, st, 100, 531); 
        
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
        /*String st = "this is a test yes this is really a test"; 
        EfficientMarkovWord markovWord = new EfficientMarkovWord(2); 
        runModel(markovWord, st, 50, 42);

        st = "this is a test yes this is really a test yes a test this is wow"; 
        EfficientMarkovWord markovWord2 = new EfficientMarkovWord(2); 
        runModel(markovWord2, st, 50, 42);*/
        
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        EfficientMarkovWord markovWord = new EfficientMarkovWord(3); 
        runModel(markovWord, st, 100, 371); 

        
    }

    public void compareMethods(){

        FileResource fr1 = new FileResource(); 
        String st1 = fr1.asString(); 
        st1 = st1.replace('\n', ' '); 
        MarkovWord markovWord1 = new MarkovWord(2); 
        long t0 = System.nanoTime();
        runModel(markovWord1, st1, 100, 43); 
        long t1 = System.nanoTime();

        long dt1 = t1-t0;

        FileResource fr2 = new FileResource(); 
        String st2 = fr2.asString(); 
        st2 = st2.replace('\n', ' '); 
        EfficientMarkovWord markovWord2 = new EfficientMarkovWord(2); 
        t0 = System.nanoTime();
        runModel(markovWord2, st2, 100, 43); 
        t1= System.nanoTime();

        long dt2 = t1-t0;
        
        if (dt2 < dt1){
            System.out.println("Efficient Markov is faster");
        }
        
        
    }

}
