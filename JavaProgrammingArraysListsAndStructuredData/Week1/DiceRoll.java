
/**
 * Write a description of class examplePrograms here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.Random;

public class DiceRoll {

    public void simulateDiceRoll(int rolls){
    
        Random rand = new Random();
        int [] counts = new int [11];
        for (int j=0; j <= rolls; j++){
            int d1 = rand.nextInt(6)+1;
            int d2 = rand.nextInt(6)+1;
            counts[(d1+d2)-2] +=1; 
        }
        System.out.println("Dice\tCounts\tFrequency");
        for (int j=0;j<counts.length;j++){
            System.out.println(j+2 + "\t" + counts[j] + "\t" + 100.0*counts[j]/rolls);
        }
    }
    
    public void testSimulateDiceRoll(){
        simulateDiceRoll(100000);     
    }
    
    
    
}
