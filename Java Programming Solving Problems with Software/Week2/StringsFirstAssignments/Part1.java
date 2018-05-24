
/**
 * Write a description of class Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    
    public String findSimpleGene (String dna){
        String geneName = "";
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1){ //no ATG
            return "";
        }
        int endIndex = dna.indexOf("TAA",startIndex+3);
        if (endIndex == -1){
            return "";
        }
        boolean seqLen = (endIndex-startIndex)%3==0;
        if (seqLen==true){
            geneName = dna.substring(startIndex,endIndex+3);
        }
        return geneName;
    }
    
    public void testSimpleGene(){
        // Thyamine, Guanine, Cytogine, Adenine
        String DNA1 ="ATGCGTACGTGCAACTTGCGTTAA";// ATG & TAA multiple of 3
        String DNA2 ="ACTGTCCCTATGATCTGGGCTATATACCACCGTGCACTTTAGCATAC";//no TAA
        String DNA3 ="TGAAATAAT";// no ATG
        String DNA4 ="CATGGCAGTAAGTATCGA";// ATG & TAA not a multiple of 3
        String DNA5 ="TCAACTCTATAGGATAGCATATAGTCG";// no ATG & no TAA
        
        String GENE1 = findSimpleGene (DNA1);
        String GENE2 = findSimpleGene (DNA2);
        String GENE3 = findSimpleGene (DNA3);
        String GENE4 = findSimpleGene (DNA4);
        String GENE5 = findSimpleGene (DNA5);
        
        System.out.println("DNA strand is " + DNA1);
        System.out.println("Gene is " + GENE1);
        System.out.println("DNA strand is " + DNA2);
        System.out.println("Gene is " + GENE2);
        System.out.println("DNA strand is " + DNA3);
        System.out.println("Gene is " + GENE3);
        System.out.println("DNA strand is " + DNA4);
        System.out.println("Gene is " + GENE4);
        System.out.println("DNA strand is " + DNA5);
        System.out.println("Gene is " + GENE5);
    }
    
}
