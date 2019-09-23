import edu.princeton.cs.algs4.*;

/* KEVIN'S HUMANPYRAMIDS.JAVA */
public class HumanPyramids {

    /* COUNTS TO SHOW EFFICIENCY */
    private static int memoizationCount = 0;
    private static int normalCount = 0;

    public static void main(String[] args) {
        StdOut.println("Kevin's HumanPyramids.java\n");

        StdOut.print("Enter row: ");
        int row = StdIn.readInt();
        StdOut.print("Enter col: ");
        int col = StdIn.readInt();
        StdOut.println();

        double[][] array = new double[row + 1][row + 1];

        long memoizationTime = System.currentTimeMillis();
        StdOut.println("Memoization: " + weightOnBackButBetter(row, col, array) + " in " + memoizationCount + " calls in " + (System.currentTimeMillis() - memoizationTime) + " ms.");

        long normalTime = System.currentTimeMillis();
        StdOut.println("Normal: " + weightOnBack(row, col) + " in " + normalCount + " calls in " + (System.currentTimeMillis() - normalTime) + " ms.");
    }

    private static double weightOnBackButBetter(int row, int col, double[][] array) {
        memoizationCount++;
        double temp;
        if (row <= 0 || array[row][col] != 0)
            return array[row][col];
        else if (col == 0)
            array[row][col] = 0.5 * (200 + weightOnBackButBetter(row - 1, col, array));
        else if (row == col)
            array[row][col] = 0.5 * (200 + weightOnBackButBetter(row - 1, col - 1, array));
        else if (array[row][col] == 0)
            array[row][col] = 0.5 * (200 + weightOnBackButBetter(row - 1, col - 1, array)) + 0.5 * (200 + weightOnBackButBetter(row - 1, col, array));
        return array[row][col];
    }

    private static double weightOnBack(int row, int col) {
        normalCount++;
        if (row <= 0)
            return 0;
        else if (col == 0)
            return 0.5 * (200 + weightOnBack(row - 1, col));
        else if (row == col)
            return 0.5 * (200 + weightOnBack(row - 1, col - 1));
        else
            return 0.5 * (200 + weightOnBack(row - 1, col - 1)) + 0.5 * (200 + weightOnBack(row - 1, col));
    }
}