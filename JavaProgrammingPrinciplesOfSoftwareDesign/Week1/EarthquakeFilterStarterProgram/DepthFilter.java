
/**
 * Write a description of class DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter
{
    private double depthMin; 
    private double depthMax;
    
    public DepthFilter(double min,double max) { 
        depthMin = min;
        depthMax = max;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        return (qe.getDepth() <= depthMin && qe.getDepth() >= depthMax); 
    } 
}
