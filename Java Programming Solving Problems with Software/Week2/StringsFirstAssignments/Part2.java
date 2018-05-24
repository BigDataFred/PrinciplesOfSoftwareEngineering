
/**
 * Write a description of class Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    
    public String findSimpleGene (String dna,String startCodon, String stopCodon){
            String geneName = "";
            boolean hasUppercase = !dna.equals(dna.toLowerCase());
            boolean hasLowercase = !dna.equals(dna.toUpperCase());
      
            if (hasUppercase == true){
                startCodon = startCodon.toUpperCase();
                stopCodon = stopCodon.toUpperCase();
            } else {
                startCodon = startCodon.toLowerCase();
                stopCodon = stopCodon.toLowerCase();
            }
            
            int startIndex = dna.indexOf(startCodon);
            if (startIndex == -1){ //no ATG
                return "";
            }
            int endIndex = dna.indexOf(stopCodon,startIndex+3);
            if (endIndex == -1){
                return "";
            }
            boolean seqLen = (endIndex-startIndex)%3==0;
            if (seqLen==true){
                geneName = dna.substring(startIndex,endIndex+3);
            }
            
            if (hasUppercase == true){
                geneName = geneName.toUpperCase();
            } else {
                geneName = geneName.toLowerCase();
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
        
        String GENE1 = findSimpleGene (DNA1, "ATG", "TAA");
        String GENE2 = findSimpleGene (DNA2, "ATG", "TAA");
        String GENE3 = findSimpleGene (DNA3, "ATG", "TAA");
        String GENE4 = findSimpleGene (DNA4, "ATG", "TAA");
        String GENE5 = findSimpleGene (DNA5, "ATG", "TAA");
        
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
