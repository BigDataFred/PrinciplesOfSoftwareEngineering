
/**
 * Write a description of class TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {

    public String extractLastWordFromSequence(String s){

        int idx1 = 0;
        int idx2 = s.indexOf(" ",idx1);
        int lastIndex = s.lastIndexOf(" ");
        String lastWord = " ";
        while ( ( idx1 <= s.length() ) && ( idx2 <= s.length() ) ){
            lastWord = s.substring(idx1,idx2);
            idx1 = idx2+1;
            idx2 = s.indexOf(" ",idx1);
            if (idx2 == -1){
                idx2 = s.length();
            }
        }
        return lastWord;
    }

    public void testExtractWordsFromSequence(){
        String testString = "This is a sentence";
        String lastWord = extractLastWordFromSequence(testString);
        System.out.println(lastWord);
    }
    

    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String s1 = q1.getInfo();
        String s2 = q2.getInfo();
        s1 = extractLastWordFromSequence(s1);
        s2 = extractLastWordFromSequence(s2);
        
        int chck = s1.compareTo(s2);

        if (chck == 0){
            if (q1.getMagnitude() < q2.getMagnitude()){
                return -1;
            }
            if (q1.getMagnitude() > q2.getMagnitude()){
                return 1;
            }
            return 0;
        }
        return chck;
    }

}
