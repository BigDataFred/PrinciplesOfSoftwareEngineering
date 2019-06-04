
/**
 * Write a description of class MatchAllFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MatchAllFilter implements Filter
{
    private ArrayList<Filter> maf;
    
    public MatchAllFilter(){
        maf = new ArrayList<Filter>();
    }
    
    public void addFilter( Filter filter){
        maf.add(filter);
    }
    
    public String getName(){
        String filterNames = "";
        for (Filter f: maf){
            filterNames = filterNames + " " + f.toString().substring(0,f.toString().indexOf("@"));
        }
        return filterNames;
    }
    
    public boolean satisfies(QuakeEntry qe){
          for (Filter f: maf){
              if (! f.satisfies(qe)){
                  return false;
                }              
          }
          return true;
    }
}
