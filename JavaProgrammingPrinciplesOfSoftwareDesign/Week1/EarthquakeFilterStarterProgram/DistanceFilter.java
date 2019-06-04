
/**
 * Write a description of class DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter
{
    private Location curLoc;
    private double maxDist;
    
    public DistanceFilter(Location loc, double max) {
        curLoc = loc;
        maxDist = max;
    }
    
    public boolean satisfies(QuakeEntry qe) { 
        return (qe.getLocation().distanceTo(curLoc) < maxDist ); 
    } 
}
