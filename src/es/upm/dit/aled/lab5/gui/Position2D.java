package es.upm.dit.aled.lab5.gui;

/**
 * Represents a position in 2D space.
 * 
 * @author rgarciacarmona
 */
public class Position2D {

    private final double x;
    private final double y;

    /**
     * Builds a Position2D from the (x,y) coordinates.
     * 
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public Position2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
	 * Returns the x coordinate.
	 * 
	 * @return The x coordinate.
	 */
    public double getX() {
        return x;
    }

    /**
	 * Returns the y coordinate.
	 * 
	 * @return The y coordinate.
	 */
    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
