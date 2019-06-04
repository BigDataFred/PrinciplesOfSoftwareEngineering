
/**
 * Write a description of class PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter
{
    private String refPhrase;
    private String chkPhrase;

    public PhraseFilter(String string1, String string2){
        refPhrase = string1;
        chkPhrase = string2;
    }

    public boolean satisfies(QuakeEntry qe) { 
        int startIx = 0;
        int endIx = 0;
        if (refPhrase.indexOf("start")!=-1){
            startIx = 0;
            endIx = chkPhrase.length();
        }   
        if (refPhrase.indexOf("end")!=-1){
            startIx = qe.getInfo().length()-chkPhrase.length();
            endIx = qe.getInfo().length();
        }   
        if (refPhrase.indexOf("any")!=-1){
            startIx =  qe.getInfo().indexOf(chkPhrase);
            endIx =startIx+chkPhrase.length();
            if( startIx ==-1 || endIx ==-1){
                return false;
            }
        }   
        return (qe.getInfo().substring(startIx,endIx).indexOf(chkPhrase) !=-1);
    } 

}
