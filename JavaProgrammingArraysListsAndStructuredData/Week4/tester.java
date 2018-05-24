
/**
 * Write a description of class tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.*;

public class tester {

    public void testCaesarCipher(){
        FileResource fr = new FileResource("VigenereTestData/titus-small.txt");
        CaesarCipher cc = new CaesarCipher(17);
        System.out.println(fr.asString());
        String encrypted = cc.encrypt(fr.asString());
        String decrypted = cc.decrypt(encrypted);
        System.out.println(encrypted);
        System.out.println(decrypted);
    }

    public void testCaesarCrackerE(){
        FileResource fr = new FileResource("VigenereTestData/titus-small_key5.txt");
        CaesarCracker cc = new CaesarCracker();
        System.out.println(fr.asString());
        String decrypted = cc.decrypt(fr.asString());
        System.out.println(decrypted);
    }

    public void testCaesarCrackerO(){
        FileResource fr = new FileResource("VigenereTestData/oslusiadas_key17.txt");
        CaesarCracker cc = new CaesarCracker('a');
        System.out.println(fr.asString());
        String decrypted = cc.decrypt(fr.asString());
        System.out.println(decrypted);
    }

    public void testVignereCiper(){
        FileResource fr = new FileResource("VigenereTestData/titus-small.txt");
        System.out.println(fr.asString());

        int[] keyArray = {17, 14, 12, 4};
        VigenereCipher vc = new VigenereCipher(keyArray);
        String encrypted = vc.encrypt(fr.asString());
        String decrypted = vc.decrypt(encrypted);
        System.out.println(encrypted);
        System.out.println(decrypted);
    }

    public void testSliceString(){

        int[] key = {0,3,1,3,2,3,0,4,1,4,2,4,3,4,0,5,1,5,2,5,3,5,4,5};
        int ix1 = 0;
        int ix2 = 1;
        VigenereBreaker vb = new VigenereBreaker();

        for (int k=0;k<key.length/2;k++){    
            String slicedStr = vb.sliceString("abcdefghijklm", key[ix1], key[ix2]);
            System.out.println(slicedStr);
            ix1 += 2; ix2 +=2;
        }
    }

    public void testVignereBreakerKnownLanguageANDkey(){
        FileResource fr = new FileResource("VigenereTestData/athens_keyflute.txt");
        String encrypted = fr.asString();
        char mostCommon = 'e';//alph.charAt(m);
        String eK = "flute";
        System.out.println(encrypted);
        System.out.println(eK.length());
        System.out.println(mostCommon);

        VigenereBreaker vb = new VigenereBreaker();
        int [] key = vb.tryKeyLength(encrypted,eK.length(),mostCommon);     
        for (int k=0;k<key.length;k++){
            System.out.println(key[k]);
        }
    }
    
    public void testBreakForLanguage(){
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere ();
    }
    
    public void testMostCommonCharIn(){
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource("dictionaries/English");
        HashSet<String> dictionary = vb.readDictionary(fr);
        char mostCommon = vb.mostCommonCharIn(dictionary);
        System.out.println(mostCommon);
    }
    
    public void testDR(){
        DirectoryResource dr = new DirectoryResource();
        VigenereBreaker vb = new VigenereBreaker();
        for (File f : dr.selectedFiles()) {
            // process each file in turn
            FileResource fr2 = new FileResource("dictionaries/"+f.getName());
            HashSet<String> dictionary = vb.readDictionary(fr2);
        }
    
    }
    
    public void testBreakVigenereFinal(){
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere ();
    }
}
