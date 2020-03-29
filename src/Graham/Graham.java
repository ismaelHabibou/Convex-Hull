import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Graham {


    public static void main(String[] args) {
        List<Point> points = new ArrayList<>(Arrays.asList(new Point(1 ,2.4),new Point(2.5 ,2),
                new Point(1.5 ,34.5), new Point( 5.5 ,6), new Point(6 ,2.4), new Point( 5.5 ,9)));

        Stack<Point> convexHullPoints = getConvexHull(points);
        System.out.println("The convex points are: " + convexHullPoints);


    }

    /**
     * Find the lowest and rightmost point.
     * We are going to use the Java coordinate system
     */
    private static Point getLowestPoint(List<Point> points) {
        double maxYCoordinate = 0;
        int index = 0;

        for (int i = 0; i < points.size(); i++)
            if (maxYCoordinate < points.get(i).getY()) {
                maxYCoordinate = points.get(i).getY(); // update the maximum y-coordinate
                index = i; // update the index of the point with maximum y-coordinate

            }
        return points.get(index); //return point
    }

    /**
     * Sort the points according angle with the lowest point.
     * If two points have the same angle discard the one that is closer to the lowestPoint.
     */
    private static void sort(List<Point> points) {
        Point lowestPoint = getLowestPoint(points); // get the lowest point
        // set the origin of the coordinate system to lowestPoint
        Point.setOrigin(lowestPoint);

        Point temp; // temporary variable

        // Use Bubble sort
        for (int i = 0; i < points.size(); i++) {

            for (int j = 0; j < points.size() - i - 1; j++) {

                if (points.get(j).compareTo(points.get(j + 1)) > 0) {
                    temp = points.get(j);
                    points.set(j, points.get(j + 1));
                    points.set(j + 1, temp);

                } else if (points.get(j).equals(points.get(j + 1))) {
                    System.out.println(points.get(j));
                    System.out.println(points.get(j + 1));

                    if (points.get(j).length() > points.get(j + 1).length())
                        // remove the point at index j - 1
                        points.remove(j + 1);
                    else
                        points.remove(j);

                    // Decrement i and j
                    i--;
                    j--;
                }
            }
        }

    }


    /** Get the points that form a convex hull*/
    private static Stack<Point> getConvexHull(List<Point> points){
        // Sort the list first
        sort(points);

        // Create a stack that will hold the points of the convex hull
        Stack<Point> stack = new Stack<>();

        // Add the first three elements to the stack
        Point secondTop;
        Point top;

        stack.push(points.get(0));
        stack.push(points.get(1));
        stack.push(points.get(2));

        int index = 3;

        while (index < points.size()){
            top = stack.peek();
            secondTop = stack.elementAt(stack.size() - 2);

            // check if the next element in the list is on the left of the two elements of the stack
            if (findRelativePosition(top,secondTop,points.get(index)) > 0){
                stack.push(points.get(index));
                index++;


            } else {

               stack.pop(); // pop the top of the stack

            }
        }

        return stack;
    }

    /** Check if a point is on the line, on left side or on the right side of the line.
     * The method will return 0 if the point is on the line, a positive number if the point is on the left side and a negative number if the point is on the right side.
     * This method is implemented using determinant
     * */
    private static double findRelativePosition(Point pointA, Point pointB, Point pointC){
        return (pointB.getX() - pointA.getX()) * (pointC.getY() - pointA.getY()) - (pointB.getY() - pointA.getY()) * (pointC.getX() - pointA.getX());
    }
}
