import edu.duke.*;

/**
 * Write a description of class Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {

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
        return -1;
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

    public void printAllGenes(String dna){
        int startIndex = 0;
        while ( true ){
            String currentGene = findGene( dna, startIndex);
            if (currentGene.isEmpty()){
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene,startIndex)+
            currentGene.length();
        }
    }

    public StorageResource getAllGenes(String dna){
        int startIndex = 0;
        StorageResource geneList = new StorageResource();
        while ( true ){
            String currentGene = findGene( dna, startIndex);
            if (currentGene.isEmpty()){
                break;
            }
            geneList.add(currentGene);
            startIndex = dna.indexOf(currentGene,startIndex)+
            currentGene.length();
        }

        return geneList;
    }

    public float cgRatio( String dna ){
        float cntC = countAminoBase( dna, "C");
        float cntG = countAminoBase( dna, "G");
        float n = dna.length();
        float cgR = (cntC+cntG)/n;
        return cgR;
    }

    public int countAminoBase(String dna, String aBase){
        int cnt = 0;
        int w = 0;
        while (true) {
            int index = dna.indexOf(aBase,w);

            if (index==-1 || index+aBase.length()>dna.length()){
                break;
            }
            cnt = cnt+1;
            w = index+aBase.length();
        }
        return cnt;
    }

    public void processGenes(StorageResource sr){
        int max = 0;
        int cnt1 = 0;
        int n = 0;
        
        for (String s : sr.data()){
            n = n+1;
            System.out.println(n);
            
            if (s.length() > max){
                max = s.length();
            }

            if (s.length() > 60){
                cnt1 = cnt1+1;
                System.out.println(s);
            }
        }

        int cnt2 = 0;
        for (String s : sr.data()){
            float cgR = cgRatio(s);
            if (cgR>0.35){
                cnt2 = cnt2+1;
                System.out.println(s);
            }
        }

        System.out.println("Total number of genes is: " +n);
        System.out.println("Genes >60 : " +cnt1);
        System.out.println("Genes with CG-ratio >0.35 " + cnt2);
        System.out.println("Longest Gene has length " +max);
    }

    public void testFindStopCodon(){
        int startIndex;
        int stopIndex;
        String stopCodon1 = "TAA";
        String stopCodon2 = "TGA";
        String stopCodon3 = "TAG";
        String stopCodon4 = "CCC";

        //            1     7  10    16  20  24
        //            v     v  v     v   v   v
        String dna = "ATGGAGTAATAGTTCTGATTAAATAGCC";

        startIndex = dna.indexOf("ATG");
        System.out.println("StartIndex found @ " + startIndex);

        stopIndex = findStopCodon(dna, startIndex , stopCodon1);
        System.out.println("Codon " + stopCodon1 + " found @:");
        System.out.println(stopIndex);
        stopIndex = findStopCodon(dna, startIndex, stopCodon2);
        System.out.println("Codon " + stopCodon2 + " found @:");
        System.out.println(stopIndex);
        stopIndex = findStopCodon(dna, startIndex, stopCodon3);
        System.out.println("Codon " + stopCodon3 + " found @:");
        System.out.println(stopIndex);
        stopIndex = findStopCodon(dna, startIndex, stopCodon4);
        System.out.println("Codon " + stopCodon4 + " found @:");
        System.out.println(stopIndex);

    }

    public void testFindGene(){
        //             v     v  v     v
        String dna1 = "ATGGAGTAATAGTTCTGATTAAATAGCC";
        //             v        v    
        String dna2 = "ATGGAGTTATAGTTCTGATTAAATAGCC";
        //             v              v   
        String dna3 = "ATGGAGTTATACTTCTGATTAAATAGCC";
        //             v                 
        String dna4 = "ATGGAGTTATACTTCTCATTAAATAGCC";

        String dna5 = "ATCGAGTTATACTTCTCATTAAATAGCC";

        String gene1 = findGene(dna1,1);
        String gene2 = findGene(dna2,1);
        String gene3 = findGene(dna3,1);
        String gene4 = findGene(dna4,1);
        String gene5 = findGene(dna5,1);

        System.out.println(dna1);
        System.out.println("Gene detected " + gene1);
        System.out.println(dna2);
        System.out.println("Gene detected " + gene2);
        System.out.println(dna3);
        System.out.println("Gene detected " + gene3);
        System.out.println(dna4);
        System.out.println("Gene detected " + gene4);
        System.out.println(dna5);
        System.out.println("Gene detected " + gene5);
    }

    public void testOn( String dna ){
        //System.out.println("Testing printAllGenes on " +dna);
        System.out.println("Testing getAllGenes on " +dna);
        //printAllGenes(dna);
        StorageResource geneList = getAllGenes(dna);

        for (String s: geneList.data()){
            System.out.println(s);
        }
    }

    public void test(){
        testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        testOn("");
        testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
    }

    public void testCGratio(){
        //double cgR = cgRatio("ATGCCATAG");
        //System.out.println("CG-ratio for gene ATGCCATAG is: " + cgR);

        String dna = "CTGCTGAAACTGCTGCTGAAACTG";
        int cnt =  countAminoBase(dna,"CTG");
        System.out.println("CTG occurs " + cnt + " times in String " +dna);
        //String testString = "ATGCCATAG";
        //int index = testString.indexOf("A",6);
        //System.out.println(index);
    }

    public void testProcessGenes(){
        //one DNA string that has some genes longer than 9 characters
        StorageResource sr = new StorageResource();
        sr.add("ACCATGCGATACGGCCTAAAGGTATAAGAC");
        //one DNA string that has no genes longer than 9 characters
        sr.add("CACATGTAA");
        //one DNA string that has some genes whose C-G-ratio is higher than 0.35
        sr.add("GAGATCTAACCGTCACCCATCA");
        //one DNA string that has some genes whose C-G-ratio is lower than 0.35
        sr.add("CACATGTAAGGCTGAGGGATGA");

        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        dna = dna.toUpperCase();
        int x  = countAminoBase(dna,"CTG");
        System.out.println("CTG occurs " + x + " times in dna Strand ");
        System.out.println(dna.length());
        StorageResource geneList = getAllGenes(dna);
        processGenes(geneList);

    }

}
