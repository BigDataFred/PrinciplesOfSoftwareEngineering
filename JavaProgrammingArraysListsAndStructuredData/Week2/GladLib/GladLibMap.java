import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String,ArrayList<String>> myMap;
    private ArrayList<String> usedWords;
    private ArrayList<String> usedCat;
    private ArrayList<Integer> wordsCount;

    private Random myRandom;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";

    public GladLibMap(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }

    private void initializeFromSource(String source) {
        myMap = new HashMap<String,ArrayList<String>>();

        String [] categories = {"adjective","noun","color","country",
                "name","animal","timeframe","verb","fruit"};
        for (int k=0;k<categories.length;k++){
            ArrayList aL = readIt(source+"/"+categories[k]+".txt"); 
            myMap.put(categories[k],aL);
        }
        usedWords = new ArrayList<String>();
        usedCat = new ArrayList<String>();
        wordsCount = new ArrayList<Integer>();
    }

    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        } else {
            if (usedCat.indexOf(label) ==-1){
                usedCat.add(label);
            }
            return ""+randomFrom(myMap.get(label));
        }
    }

    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }

        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        int flag = 0;
        String sub = "";
        while (flag<1){
            sub = getSubstitute(w.substring(first+1,last));
            int index = usedWords.indexOf(sub);
            if (index==-1){
                flag = 1;
                usedWords.add(sub);
                wordsCount.add(1);
            }
        }
        return prefix+sub+suffix;
    }

    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source){
        String story = "";
        usedWords.clear();
        usedCat.clear();
        wordsCount.clear();

        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }

    public int totalWordsInMap(){
        int twc = 0;
        for (String s: myMap.keySet()){
            ArrayList aL = myMap.get(s);
            for (int k=0;k<aL.size();k++){
                twc = twc+1;
            }
        }
        return twc;
    }

    public int totalWordsConsidered(){
        int twc = 0;
        for (int k=0;k<usedCat.size();k++){
            String cat = usedCat.get(k);
            ArrayList aL = myMap.get(cat);
            for (int l=0;l<aL.size();l++){
                twc = twc+1;
            }
        }
        return twc;
    }

    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println(wordsCount.size());
        int twc = totalWordsInMap();
        System.out.println("The total word count is: " + twc);
        int twc2 = totalWordsConsidered();
        System.out.println("The total word count across categories: " + twc2);
    }

}
