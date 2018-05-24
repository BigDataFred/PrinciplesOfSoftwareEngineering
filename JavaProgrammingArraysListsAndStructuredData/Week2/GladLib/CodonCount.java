
/**
 * Write a description of class CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class CodonCount {

    private HashMap<String,Integer> codonMap;

    public CodonCount(){
        codonMap = new HashMap<String,Integer>();
    }

    public void buildCodonMap(int start, String dna){
        codonMap.clear();
        for (int l=start;l<dna.length()-2;l+=3){
            String s = dna.substring(l,l+3);
            if (s.length()>=3 && Character.isLetter(s.charAt(2))){
                boolean chck = codonMap.containsKey(s);
                if (chck==true){
                    codonMap.put(s,codonMap.get(s)+1);
                } else {
                    codonMap.put(s,1);
                }
            }
        }

    }

    public String getMostCommonCodon(){
        int m = 0;
        String mS = "NOTHING HERE";
        for (String s: codonMap.keySet()){
            int count = codonMap.get(s);
            if (count > m) {
                m = count;
                mS = s;
            }
        }
        return mS;
    }

    public void printCodonCounts(int start, int end){
        for (String s : codonMap.keySet()){
            int count = codonMap.get(s);
            if (count >= start && count <= end){
                System.out.println("Codon: "+s+", Count: "+count);
            }
        }
    }

    public void tester(){
        FileResource fr = new FileResource();
        String dna = fr.asString();
        dna = dna.toLowerCase();

        for (int idx = 0;idx<3;idx++){
            System.out.println("Starting frame at index " + idx);
            buildCodonMap(idx, dna);
            System.out.println("Total unique codons:" +codonMap.size());      
            printCodonCounts(1,100);
            String mS = getMostCommonCodon();
            System.out.println("Most common codon:"+mS);
        }

    }
}
