import edu.duke.*;
import java.io.File;
import java.util.Arrays;

public class PerimeterAssignmentRunner {
    public int getNumPoints (Shape s) {
        // Put code here
        int nPoints = 0;
        for (Point p : s.getPoints()) {
            nPoints = nPoints+1;
        }  
        return nPoints;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        // Start with avgLen = 0
        double avgLen = 0.0;
        double totPerim = getPerimeter(s);
        int nPoints = getNumPoints(s);
        avgLen = totPerim/nPoints;
        // avgLen is the answer
        return avgLen;
    }

    public double[] getSideLength(Shape s) {
        // Put code here
        int nPoints = getNumPoints(s);
        double[] sideLen = new double[nPoints];
        Point prevPt = s.getLastPoint();
        int c = 0;
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            sideLen[c] = currDist;
            c = c+1;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        return sideLen;
    }
    
    public double getLargestSide(Shape s) {
        // Put code here
        int nPoints = getNumPoints(s);
        double[] sideLen = new double[nPoints];
        sideLen = getSideLength(s);
        double max = sideLen[0];

        for (int i = 1; i < sideLen.length; i++) {
            if (sideLen[i] > max) {
                max = sideLen[i];
            }
        }
        return max;
    }

    public int getLargestX(Shape s) {
        // Put code here
        Point p = s.getLastPoint();
        int max = 0;
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            int currX = currPt.getX();
            if (currX > max){
                max = currX;
            }
        }
        return max;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        double maxPerim = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            System.out.println(f);
            Shape s = new Shape(fr);
            double totalPerim = getPerimeter(s);
            if (totalPerim>maxPerim){
                maxPerim = totalPerim;
            }
        }
        return maxPerim;
    }
    

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        double maxPerim = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double totalPerim = getPerimeter(s);
            if (totalPerim>maxPerim){
                maxPerim = totalPerim;
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeterMultipleFiles() {
        // Put code here
        double maxPerim = getLargestPerimeterMultipleFiles();
        System.out.println("max perimeter = " + maxPerim);
    }
    

    public void testFileWithLargestPerimeter() {
        // Put code here
        String maxFile = getFileWithLargestPerimeter();
        System.out.println("max file = " + maxFile);
    }
    
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int nPoints = getNumPoints(s);
        System.out.println("number of points = " +nPoints);
        double avgLen = getAverageLength(s);
        System.out.println("average length = " +avgLen);
        double maxLen = getLargestSide(s);
        System.out.println("max length = " +maxLen);
        double maxX = getLargestX(s);
        System.out.println("max X = " +maxX);
    }
    
    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        //pr.testPerimeter(); // Assignment #1
        pr.testPerimeterMultipleFiles(); // Assignment #2
        pr.testFileWithLargestPerimeter();// Assignment #2
    }
}
