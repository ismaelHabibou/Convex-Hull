import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GiftWrapping {

    public static void main(String[] args) {
	// List of points
        List<Point2D> points = new ArrayList<>(Arrays.asList(new Point2D(1 ,2.4),new Point2D(2.5 ,2),
                new Point2D(1.5 ,34.5), new Point2D( 5.5 ,6), new Point2D(6 ,2.4), new Point2D( 5.5 ,9)));
        System.out.println("The lowest point is " + getLowestPoint(points) );

        System.out.println("The convex hull is : " + getConvex(points));

    }


    /** Find the lowest point in the list i.e point with the highest y-coordinate.
     * We are going to use Java coordinate system.
     * */
    private static Point2D getLowestPoint(List<Point2D> points){
        double maxYCoordinate = 0;
        int index = 0;

        for (int i = 0; i < points.size(); i++)
            if (maxYCoordinate < points.get(i).getY()){
                maxYCoordinate = points.get(i).getY(); // update the maximum y-coordinate
                index = i; // update the index of the point with maximum y-coordinate
            }
        return points.get(index); //return point
    }

    /** Check if a point is on the line, on left side or on the right side of the line.
     * The method will return 0 if the point is on the line, a positive number if the point is on the left side and a negative number if the point is on the right side.
     * This method is implemented using determinant
     * */
    private static double findRelativePosition(Point2D pointA, Point2D pointB, Point2D pointC){
        return (pointB.getX() - pointA.getX()) * (pointC.getY() - pointA.getY()) - (pointB.getY() - pointA.getY()) * (pointC.getX() - pointA.getX());
    }

   /** Get the points that form a convex hull*/
   private static ArrayList<Point2D> getConvex(List<Point2D> points){
       ArrayList<Point2D> convexHullPoints = new ArrayList<>(); // Create a list that will store the convex hull points
       convexHullPoints.add(getLowestPoint(points)); // add the lowest point to the list containing points that form a convex.
       Point2D currentPoint = convexHullPoints.get(0);
       Point2D nextPoint;


       for (int i = 0; i < points.size(); i++) {
           nextPoint = points.get(i);

           for (int j = 0; j < points.size(); j++) {
               // Check if the points at index j is on the right side of the line that goes from currentPoint to nextPoint
               if (findRelativePosition(currentPoint, nextPoint, points.get(j)) < 0){
                   nextPoint = points.get(j); // update the next point
               }

           }

           // If the next point is the same as the lowest point, it means a convex hull is found
           if (nextPoint.equals(convexHullPoints.get(0)))
               break;

               //Update the current point
               currentPoint = nextPoint;

               // Add the next point to the convex hull list
               convexHullPoints.add(nextPoint);

       }

       return convexHullPoints;
   }
}
