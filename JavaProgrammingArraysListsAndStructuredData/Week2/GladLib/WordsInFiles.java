
/**
 * Write a description of class WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.*;

public class WordsInFiles {

    private HashMap<String,ArrayList>word2File;

    public WordsInFiles(){
        word2File = new HashMap<String,ArrayList>();
    }

    private void addWordsFromFile(File f){

        FileResource fr = new FileResource(f.getAbsolutePath());
        for (String s : fr.words()){
            if (!word2File.containsKey(s)){
                ArrayList wl = new ArrayList<String>(); 
                wl.add(f.getName());
                word2File.put(s,wl);                
            } else {
                ArrayList wl = word2File.get(s);
                int index = wl.indexOf(f.getName());
                if (index==-1){
                    wl.add(f.getName());
                    word2File.put(s,wl);
                }
            }
        }

    }

    public void buildWordFileMap(){
        word2File.clear();
        DirectoryResource dr = new DirectoryResource();
        for ( File f: dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }

    public int maxNumber(){
        int m = 0;
        for (String s : word2File.keySet()){
            ArrayList wl = word2File.get(s);
            int cnt = wl.size();
            if (cnt>m){
                m = cnt;
            }
        }
        return m;
    }

    public ArrayList wordsInNumFiles(int number){
        ArrayList wordSel = new ArrayList<String>();
        for (String s : word2File.keySet()){
            ArrayList wl = word2File.get(s);
            int cnt = 0;
            for (int k=0;k<wl.size();k++){
                cnt = cnt+1;
            }
            if (cnt == number){
                wordSel.add(s);
            }
        }
        return wordSel;
    }

    public void printFilesIn(String word){
        ArrayList<String> wl = word2File.get(word);
        for (int k=0;k<wl.size();k++){
            System.out.println(wl.get(k));

        }
    }

    public void tester(){
        buildWordFileMap();
        //for (String s : word2File.keySet()){
            //System.out.println(s);
            ArrayList wl = word2File.get("tree");
            for (int k=0;k<wl.size();k++){
                System.out.println(wl.get(k));
            }
        //}

        int m = maxNumber();
        System.out.println("Max. number of files any word appears in "+m);
        
        System.out.println("The words that apear in the max. number of files are:");
        ArrayList<String>wordSel = wordsInNumFiles(4);
        System.out.println(wordSel.size());
        //for (int k=0;k<wordSel.size();k++){
        //    String s = wordSel.get(k);
        //    System.out.println(s);
        //    printFilesIn(s);
        //}
    }
}
