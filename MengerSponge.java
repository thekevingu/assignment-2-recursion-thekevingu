import edu.princeton.cs.algs4.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/* KEVIN'S MENGERSPONGE.JAVA */
public class MengerSponge {

    /* CONSTANTS */
    private static final double SIDE_LENGTH = 0.6;

    /* SQUARE BOTTOM-LEFT COORDINATES */
    private static final double X_COORDINATE = (1 - SIDE_LENGTH) / 2;
    private static final double Y_COORDINATE = (1 - SIDE_LENGTH) / 2;

    /* TO IMPORT IMAGE OF MONKEY */
    private static BufferedImage img = null;

    public static void main(String[] args) {
        StdOut.println("Kevin's MengerSponge.java\n");

        getMonkey();
        Color[][] monkeyArray = getMonkeyArray(img);

        StdOut.print("For higher monkey resolution, enter order >= 4.\nEnter order: ");
        int order = StdIn.readInt();
        StdOut.println();

        StdOut.println("Top right square coordinates:");

        solveSquare(X_COORDINATE, Y_COORDINATE, SIDE_LENGTH, order, monkeyArray);

        StdOut.println("\nCurious George!");
    }

    private static void solveSquare(double x, double y, double sideLength, int order, Color[][] monkeyArray) {
        if (order == 0) {
            StdOut.println("(" + (x + sideLength) + ", " + (y + sideLength) + ")");
            StdDraw.setPenColor(monkeyArray[(int) ((x - X_COORDINATE) / SIDE_LENGTH * monkeyArray.length)][(int) ((y - Y_COORDINATE) / SIDE_LENGTH * monkeyArray[0].length)]);
            StdDraw.line(x, y, x + sideLength, y);
            StdDraw.line(x, y, x, y + sideLength);
            StdDraw.line(x + sideLength, y, x + sideLength, y + sideLength);
            StdDraw.line(x, y + sideLength, x + sideLength, y + sideLength);
        } else {
            solveSquare(x, y, sideLength / 3, order - 1, monkeyArray);
            solveSquare(x + sideLength / 3, y, sideLength / 3, order - 1, monkeyArray);
            solveSquare(x + 2 * sideLength / 3, y, sideLength / 3, order - 1, monkeyArray);
            solveSquare(x, y + sideLength / 3, sideLength / 3, order - 1, monkeyArray);
            solveSquare(x + 2 * sideLength / 3, y + sideLength / 3, sideLength / 3, order - 1, monkeyArray);
            solveSquare(x, y + 2 * sideLength / 3, sideLength / 3, order - 1, monkeyArray);
            solveSquare(x + sideLength / 3, y + 2 * sideLength / 3, sideLength / 3, order - 1, monkeyArray);
            solveSquare(x + 2 * sideLength / 3, y + 2 * sideLength / 3, sideLength / 3, order - 1, monkeyArray);
        }
    }

    private static Color[][] getMonkeyArray(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        Color[][] monkeyArray = new Color[width][height];
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                monkeyArray[i][height - 1 - j] = new Color(img.getRGB(i, j));
        return monkeyArray;
    }

    private static void getMonkey() {
        try {
            img = ImageIO.read(new File("src/Monkey.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
