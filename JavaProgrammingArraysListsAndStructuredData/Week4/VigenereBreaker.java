import java.util.*;
import java.io.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        StringBuilder s = new StringBuilder();
        for ( int k = whichSlice;k<message.length();k+=totalSlices ){
            s = s.append(message.charAt(k));
        }
        return s.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int k=0;k<klength;k++){
            String SlicedStr = sliceString(encrypted, k, klength);
            key[k] = cc.getKey(SlicedStr);
        }
        return key;
    }

    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> dictionary = new HashSet<String>();

        for (String s : fr.lines()){
            s = s.toLowerCase();
            if (! dictionary.contains(s)){
                dictionary.add(s);                
            } else {
                dictionary.add(s);
            }
        }
        return dictionary;
    }

    public int countWords(String message,HashSet dictionary){
        String[] words = message.split("\\W+");
        int cnt = 0;
        for (int k=0;k<words.length;k++){
            if (dictionary.contains(words[k].toLowerCase())){
                cnt +=1;
            }
        }
        return cnt;
    }

    public String breakForLanguage(String encrypted, HashSet dictionary){
        char mostCommon = mostCommonCharIn(dictionary);

        int [] keyLength = new int [100];
        for (int k=0;k<keyLength.length;k++){
            keyLength[k] = k+1;
        }
        int [] cnt = new int[100];
        int m = 0;
        int keyIx = 0;
        for (int k=0;k<keyLength.length;k++){
            int[] key = tryKeyLength( encrypted, keyLength[k],mostCommon);
            //int[] key = tryKeyLength( encrypted, 38, mostCommon);
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            cnt[k] = countWords(decrypted,dictionary);
            if (cnt[k]>m){
                m = cnt[k];
                keyIx = k;
            }
        }
        System.out.println("Valid words" +m);
        int[] key = tryKeyLength( encrypted, keyLength[keyIx], 'e');
        VigenereCipher vc = new VigenereCipher(key);
        //System.out.println("Key-length is:");
        //System.out.println(keyLength[keyIx]);
        //System.out.println("Key is:");
        //for (int k=0;k<key.length;k++){
        //    System.out.println(key[k]);
        // }
        String decrypted = vc.decrypt(encrypted);

        return decrypted;
    }

    public char mostCommonCharIn(HashSet<String> dictionary){
        char mostCommon = ' ';
        HashMap<Character,Integer> charPool = new HashMap<Character,Integer>();
        for (String s : dictionary){
            for (int k=0;k<s.length();k++){
                char c = s.charAt(k);
                if (! charPool.containsKey(c)){
                    charPool.put(c,1);
                } else {
                    charPool.put(c,charPool.get(c)+1);
                }
            }
        }

        int m=0;
        char mC = ' ';
        for (char c : charPool.keySet()){
            if (charPool.get(c)>m){
                m = charPool.get(c);
                mC = c;
            }
        }
        return mC;
    }

    public HashSet<String> breakForAllLangs(String encrypted, HashMap<String,HashSet<String>> languages){
        int[]cnt = new int[languages.size()];
        int k=0;
        int m = 0;
        String mL = "";
        for (String l :languages.keySet()){
            HashSet<String> dictionary = languages.get(l);
            String decrypted = breakForLanguage(encrypted,dictionary);
            cnt[k] = countWords(decrypted,dictionary);
            if (cnt[k]>m){
                m = cnt[k];
                mL = l;
            }
            k +=1;
        }
        HashSet<String> dictionary = languages.get(mL);
        String decrypted = breakForLanguage(encrypted,dictionary);
        HashSet <String> outPut = new HashSet<String>();
        outPut.add(mL);
        outPut.add(decrypted);
        return outPut;
    }

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        
        HashMap<String,HashSet<String>> lang2dic = new HashMap<String,HashSet<String>>();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            // process each file in turn
            System.out.println("Reading "+f.getName());
            FileResource fr2 = new FileResource("dictionaries/"+f.getName());
            HashSet<String> dictionary = readDictionary(fr2);
            lang2dic.put(f.getName(),dictionary);
        }
        
        HashSet<String>outPut = breakForAllLangs(encrypted, lang2dic);
        for (String s : outPut){
            String lines[] = s.split("\\r?\\n");
            System.out.println(lines[0]);
        }
    }

}
