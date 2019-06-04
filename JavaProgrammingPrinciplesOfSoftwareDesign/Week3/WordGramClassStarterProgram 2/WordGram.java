
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        // TODO: Complete this method
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        // TODO: Complete this method
        for (int k=0;k<myWords.length;k++){
            ret += myWords[k] + " ";
        }
        return ret.trim();
    }
    
    public int hashCode(){
       return toString().hashCode();
    }
    

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        // TODO: Complete this method
        if ( this.length() != other.length() ){
            return false;
        }
        for (int k=0;k<myWords.length;k++){
            if ( ! myWords[k].equals(other.wordAt(k))){
                return false;
            }
        }
        
        return true;
    }

    
    public WordGram shiftAdd(String word) { 
        WordGram out = new WordGram(myWords, 0, myWords.length);
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        // TODO: Complete this method
        String[] out2 = out.toString().split("\\s+");
        String [] out3 = out.toString().split("\\s+");
        
        /*System.out.println("orig");
        for (int k=0;k<out2.length;k++){
            System.out.println(out2[k]);
        }*/
        
        for (int k= 0;k<out3.length-1;k++){
            out2[k] = out3[k+1];
        }
        
        out2[out2.length-1] = word;
        
        /*System.out.println("shifted");
        for (int k=0;k<out2.length;k++){
            System.out.println(out2[k]);
        }*/
        out = new WordGram(out2, 0, myWords.length);
        return out;
    }

}