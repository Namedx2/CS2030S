/**
 * CS2030S Lab 0: Point.java
 * Semester 2, 2021/22
 *
 * <p>The Point class encapsulates a point on a 2D plane.
 *
 * @author Yuchen (Group 10B)
 * @version CS2030S AY 21/22 Sem 2
 */
import java.lang.Math;

class Point {
	public double x;
	public double y;

   	public Point(double x, double y) {
		this.x = x;
	    	this.y = y;
	}
	
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}

	// Get distance method implemented
	public double getDistance(Point c) {
		return Math.sqrt((this.x - c.x) * (this.x - c.x) + (this.y - c.y) * (this.y - c.y));
	}
}
