import edu.princeton.cs.algs4.*;

/* KEVIN'S FRACTALTREE.JAVA */
public class FractalTree {

    /* PARAMETERS */
    private static final double x = 0.5;
    private static final double y = 0.5;
    private static final double length = 0.2;
    private static final int angle = 0;
    private static final int currentOrder = 0;

    public static void main(String[] args) {
        StdOut.println("Kevin's FractalTree.java\n");

        StdOut.print("Enter order: ");
        int order = StdIn.readInt();

        drawTree(x, y, length, angle, order, currentOrder);

        StdOut.println("\nA tree was just generated.");
    }

    /* DRAWS TREE */
    private static void drawTree(double x, double y, double length, int angle, int order, int currentOrder) {
        StdDraw.setPenColor(StdDraw.GRAY);

        if (currentOrder == 0)
            StdDraw.line(x, y, x + length * Math.cos(Math.toRadians(angle - 90)), y + length * Math.sin(Math.toRadians(angle - 90)));

        if (currentOrder < order) {
            for (int i = 45; i <= 135; i += 15) {
                if (order - 1 == currentOrder)
                    StdDraw.setPenColor(StdDraw.BOOK_BLUE);
                StdDraw.line(x, y, x + length * Math.cos(Math.toRadians(angle + i)), y + length * Math.sin(Math.toRadians(angle + i)));
                drawTree(x + length * Math.cos(Math.toRadians(angle + i)), y + length * Math.sin(Math.toRadians(angle + i)), length / 2, angle + i - 90, order, currentOrder + 1);
            }
        }
    }
}
