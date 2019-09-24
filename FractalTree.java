import edu.princeton.cs.algs4.*;

import java.util.Random;

/* KEVIN'S FRACTALTREE.JAVA */
public class FractalTree {

    /* PARAMETERS */
    private static final double x = 0.5;
    private static final double y = 0.5;
    private static final double length = 0.2;
    private static final int angle = 0;
    private static final int currentOrder = 0;

    /* RANDOM */
    private static final Random r = new Random();

    public static void main(String[] args) {
        StdOut.println("Kevin's FractalTree.java\n");

        StdOut.print("Enter order: ");
        int order = StdIn.readInt();

        int branches = 4;
        int[] angleChanges = new int[branches];
        for (int i = 0; i < angleChanges.length; i++) {
            angleChanges[i] = 30 + r.nextInt(15);
        }

        drawTree(x, y, length, angle, order, currentOrder, angleChanges, branches);

        StdOut.println("\nA tree was just generated.");
    }

    /* DRAWS TREE */
    private static void drawTree(double x, double y, double length, int angle, int order, int currentOrder, int[] angleChanges, int branches) {
        StdDraw.setPenColor(StdDraw.GRAY);

        if (currentOrder == 0)
            StdDraw.line(x, y, x + length * Math.cos(Math.toRadians(angle - 90)), y + length * Math.sin(Math.toRadians(angle - 90)));

        if (currentOrder < order) {
            for (int j = 0; j < branches; j++) {
                int i = angleChanges[0];

                for (int l = 0; l < j; l++)
                    i += angleChanges[l];
                
                if (order - 1 == currentOrder)
                    StdDraw.setPenColor(StdDraw.BOOK_BLUE);

                StdDraw.line(x, y, x + length * Math.cos(Math.toRadians(angle + i)), y + length * Math.sin(Math.toRadians(angle + i)));
                drawTree(x + length * Math.cos(Math.toRadians(angle + i)), y + length * Math.sin(Math.toRadians(angle + i)), r.nextDouble() * length / 2, (int) r.nextDouble() * angle + i, order, currentOrder + 1, angleChanges, branches);
            }
        }
    }
}

