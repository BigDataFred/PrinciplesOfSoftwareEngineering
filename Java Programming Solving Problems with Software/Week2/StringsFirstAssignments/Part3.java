
/**
 * Write a description of class Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {

    public boolean twoOccurences(String stringA, String stringB) {
        int Index1 = stringB.indexOf(stringA);
        int Index2 = stringB.indexOf(stringA,Index1+1);
        
        if ((Index1 != -1) && (Index2 !=-1)){
            return true;
        } else{
            return false; }
    }
    
        public String lastPart(String stringA, String stringB){
        String str = "";
        int Index = stringB.indexOf(stringA);
        if (Index!=-1){
            str = stringB.substring(Index+stringA.length(),stringB.length());
        } else {
            str = stringB;
        }
        return str;
    }
    
    public void testing(){
        String str1 = "banana";
        String str2 = "A story by Abby Long";
        String str3 = "ctgtatgta";
        
        boolean chck1 = twoOccurences("an", str1 );
        boolean chck2 = twoOccurences("by", str2 );
        boolean chck3 = twoOccurences("atg", str3 );
        
        String detectStr = "more than 2 occurences detected";
        System.out.println(str1);
        if (chck1 == true){
            System.out.println(detectStr);
        }
        
        System.out.println(str2);
        if (chck2 == true){
            System.out.println(detectStr);
        }
        
        System.out.println(str3);
        if (chck3 == true){
            System.out.println(detectStr);
        }
        
        String newStr1 = lastPart("an",str1);
        System.out.println("The part of the string after an in " + str1 + " is " +newStr1);
        
        String newStr2 = lastPart("by",str2);
        System.out.println("The part of the string after by in " + str2 + " is " +newStr2);
        
    }
    
}
