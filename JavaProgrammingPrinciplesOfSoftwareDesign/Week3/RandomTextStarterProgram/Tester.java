
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.lang.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class Tester {

    public void testGetFollows() {
        String st = "this is a test yes this is a test.";
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        ArrayList <String> follows = markov.getFollows("t.");
        for (String s : follows){
            System.out.println(s);
        }

    }

    public void testGetFollowsWithFile(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        ArrayList <String> follows = markov.getFollows("he");
        int cnt = 0;
        for (String s : follows){
            //StringBuilder chck = new StringBuilder(s);
            //if ( (s != " ") ){ //(Character.isLetter( chck.charAt(0) ) == true) &&
                System.out.println(s);
                cnt  = cnt+1;
            //}
        }
        System.out.println(cnt);
    }
}
