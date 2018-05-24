
/**
 * Write a description of class Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int curIndex = dna.indexOf(stopCodon, startIndex+3);
        while (curIndex!=-1){
            int curLength = curIndex-startIndex;
            if (curLength%3==0){
                return curIndex;
            } else {
                curIndex = dna.indexOf(stopCodon, curIndex+1);
            }
        }
        return dna.length();
    }

    public String findGene(String dna, int where){
        int startIndex = dna.indexOf("ATG",where);
        if (startIndex==-1){
            return "";
        }
        int taaIndex = findStopCodon(dna,startIndex,"TAA");
        int tagIndex = findStopCodon(dna,startIndex,"TAG");
        int tgaIndex = findStopCodon(dna,startIndex,"TGA");

        //int temp = Math.min(taaIndex,tagIndex);
        //int minIndex = Math.min(temp,tgaIndex);
        int minIndex =0;
        if (taaIndex ==-1 
        || (tgaIndex !=-1 && tgaIndex < taaIndex)){
            minIndex = tgaIndex;
        } else {
            minIndex = taaIndex;
        }

        if (minIndex==-1 
        || (tagIndex !=-1 && tagIndex < minIndex)){
            minIndex = tagIndex;
        }

        if (minIndex == -1){
            return "";
        } 
        return dna.substring(startIndex,minIndex+3);
    }
    
    public int countGenes(String dna){
        int startIndex = 0;
        int cnt = 0;
        while ( true ){
            String currentGene = findGene( dna, startIndex);
            if (currentGene.isEmpty()){
                break;
            }
            cnt = cnt+1;
            startIndex = dna.indexOf(currentGene,startIndex)+
            currentGene.length();
        }
        return cnt;
    }
    
    public void testCountGenes(){
        int cnt1 = countGenes("ATGTAAGATGCCCTAGT"); 
        System.out.println(cnt1);
    }
}
