public class Point implements Comparable<Point> {
    /** Data fields: coordinates*/
    private double x;
    private double y;

    /** Data field: the origin of the coordinate system*/
     private static Point origin = new Point(0,0);

    /** Create a point*/
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    /** Implement the equal method*/
    @Override
    public boolean equals(Object o){
        if (o instanceof Point){
            // Check if one point is multiple of the other
            return Math.abs(angle(this) - angle((Point) o)) < 0.00000001;
        } else
            return false;
    }

    /** get the x-coordinate*/
    public double getX() {
        return x;
    }

    /** get the y-coordinate*/
    public double getY() {
        return y;
    }

    /** Compute the angle between the pointB and the x-axis, using the data field defined above as the origin.
     * Moreover we are going to use Java coordinate system
     * */
    public static double angle(Point pointA){
        /* The cosine of two vectors i and j is given by :
        *                                                      cosine = i.j/ (modulus(i) * modulus(j))
        * we will compute the angle between the vector i = <1, 0> and AB = <xB - xOrigin, yB - yOrigin>
        */
        double cosine = ((pointA.getX() - origin.getX())/
                Math.sqrt((pointA.getY() - origin.getY()) * (pointA.getY() - origin.getY()) + (pointA.getX() - origin.getX()) * (pointA.getX() - origin.getX())));

        if (new Double(cosine).isNaN())
            return 0;
        else
            return Math.toDegrees(Math.acos(cosine));
    }

    /**Implement the compareTo method to compare two points*/
    @Override
    public int compareTo(Point point){
        if (angle(this) > angle(point))
            return 1;
        else
            return this.equals(point)? 0 : -1;
    }

    /** set the origin of the system*/
    public static void setOrigin(Point point){
        origin = point;
    }

    /** Compute the length of the vector*/
    public double length(){
    return Math.sqrt((getX() - origin.getX()) * (getX() - origin.getX()) + (getY() - origin.getY()) * (getY() - origin.getY()));
    }

    /** Implement the toString() method*/
    @Override
    public String toString(){
        return "(" + getX() + ", " + getY() + ")";
    }
}
