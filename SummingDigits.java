import edu.princeton.cs.algs4.*;

/* KEVIN'S SUMMINGDIGITS.JAVA */
public class SummingDigits {

    public static void main(String[] args) {
        StdOut.println("Kevin's SummingDigits.java\n");

        StdOut.print("Enter number: ");
        int value = StdIn.readInt();

        StdOut.println("sum of all digits : " + sum(value) + "\n");

        StdOut.println("that was anticlimactic...");
    }

    /* FINDS SUM */
    private static int sum(int n) {
        if (n == 0)
            return n;
        else
            return n % 10 + sum(n / 10);
    }
}
