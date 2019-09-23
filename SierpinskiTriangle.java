import edu.princeton.cs.algs4.*;

import java.util.Random;

/* KEVIN'S SIERPINSKITRIANGLE.JAVA */
public class SierpinskiTriangle {

    /* CONSTANTS */
    private static final double HEIGHT_CONSTANT = Math.sqrt(3) / 2;
    private static final double SIDE_LENGTH = 0.6;

    private static final Random r = new Random();

    /* TRIANGLE BOTTOM-LEFT COORDINATES */
    private static final double X_COORDINATE = (1 - SIDE_LENGTH) / 2;
    private static final double Y_COORDINATE = (1 - SIDE_LENGTH * HEIGHT_CONSTANT) / 2;

    public static void main(String[] args) {
        StdOut.println("Kevin's SierpinskiTriangle.java\n");

        StdOut.print("Enter order: ");
        int order = StdIn.readInt();
        StdOut.println();

        StdOut.println("Top triangle coordinates:");
        solveTriangle(X_COORDINATE, Y_COORDINATE, SIDE_LENGTH, order);
    }

    private static void solveTriangle(double x, double y, double sideLength, int order) {
        if (order == 0) {
            StdOut.println("(" + (x + 0.5 * sideLength) + ", " + (y + HEIGHT_CONSTANT * sideLength) + ")");
            StdDraw.setPenColor(r.nextInt(256), r.nextInt(256),r.nextInt(256));
            StdDraw.line(x, y, x + 0.5 * sideLength, y + HEIGHT_CONSTANT * sideLength);
            StdDraw.line(x, y, x + sideLength, y);
            StdDraw.line(x + 0.5 * sideLength, y + HEIGHT_CONSTANT * sideLength, x + sideLength, y);
        } else {
            solveTriangle(x, y, sideLength / 2, order - 1);
            solveTriangle(x + sideLength / 2, y, sideLength / 2, order - 1);
            solveTriangle(x + sideLength / 4, y + HEIGHT_CONSTANT / 2 * sideLength, sideLength / 2, order - 1);
        }
    }
}
