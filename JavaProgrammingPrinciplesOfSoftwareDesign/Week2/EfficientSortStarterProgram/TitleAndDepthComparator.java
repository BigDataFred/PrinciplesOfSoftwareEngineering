import java.util.*;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String s1 = q1.getInfo();
        String s2 = q2.getInfo();
        int chck = s1.compareTo(s2);
        
        if (chck == 0){
            if (q1.getDepth() < q2.getDepth()){
                return -1;
            }
            if (q1.getDepth() > q2.getDepth()){
                return 1;
            }
            return 0;
        }
        return chck;
    }
    
}
