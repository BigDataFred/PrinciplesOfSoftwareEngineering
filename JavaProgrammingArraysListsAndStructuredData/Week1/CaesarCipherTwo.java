
/**
 * Write a description of class CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwo {

    private String alpha;
    private String alphaShifted1;
    private String alphaShifted2;
    private int mainKey1;
    private int mainKey2;
    
    public CaesarCipherTwo(int key1,int key2){
        alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        alphaShifted1 = alpha.substring(key1) + alpha.substring(0,key1);
        alphaShifted2 = alpha.substring(key2) + alpha.substring(0,key2);
        mainKey1 = key1;
        mainKey2 = key2;
    } 

    public String encrypt(String input){
        StringBuilder sb = new StringBuilder(input);
        for (int k=0;k<sb.length();k++){
            char c = sb.charAt(k);
            int idx = alpha.indexOf(Character.toUpperCase(c));
            if (idx !=-1){
                if (Character.isUpperCase(c)){
                    if ((k%2)==0){
                        c = alphaShifted1.charAt(idx);
                    } else {  
                        c = alphaShifted2.charAt(idx);
                    }
                }else {
                    if ((k%2)==0){
                        c = Character.toLowerCase(alphaShifted1.charAt(idx));
                    }else {
                        c = Character.toLowerCase(alphaShifted2.charAt(idx));
                    }
                }
                sb.setCharAt(k,c);
            }
        }
        return sb.toString();
    }
    
        public String decrypt(String input){
        CaesarCipherTwo cc = new CaesarCipherTwo(alpha.length() - mainKey1,alpha.length() - mainKey2); 
        return cc.encrypt(input);
    }
}
