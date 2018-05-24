
/**
 * Write a description of class Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany( String stringA, String stringB ) {
        int startIndex = 0;
        int cnt = 0;
        while ( true ) {
            int curIndex = stringB.indexOf(stringA,startIndex);
            if (curIndex==-1){
                break;
            }
            String curString = stringB.substring(curIndex,curIndex + 
                stringA.length());
            cnt = cnt+1;
            startIndex = stringB.indexOf(stringA, startIndex) + 
                curString.length();
        }
        return cnt;
    }
    
    public void testHowMany(){
        int cnt1 = howMany("GAA", "ATGAACGAATTGAATC");
        int cnt2 = howMany("AA", "ATAAAA");
        
        System.out.println(cnt1);
        System.out.println(cnt2);
    }   
}
