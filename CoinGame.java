import edu.princeton.cs.algs4.*;

/* KEVIN'S COINGAME.JAVA */
public class CoinGame {

    public static void main(String[] args) {
        StdOut.println("Kevin's CoinGame.java\n");

        StdOut.print("P1 name: ");
        String playerOne = StdIn.readLine();
        StdOut.print("P2 name: ");
        String playerTwo = StdIn.readLine();

        StdOut.print("\nNumber of coins: ");
        int coinNumber = StdIn.readInt();

        if (coinNumber % 3 == 0) StdOut.print(playerTwo + " wins in ");
        else StdOut.print(playerOne + " wins in ");
        StdOut.print(playCoinGame(coinNumber, 0) + " strategies.");
    }

    /* PLAYS COIN GAME */
    private static int playCoinGame(int n, int player) {
        int total = 0;
        if (n >= 0) {
            if (n == 0) total++;
            else {
                if ((n - 1) % 3 == 0) {
                    total += playCoinGame(n - 1, -player);
                    total += playCoinGame(n - 4, -player);
                } else if ((n - 2) % 3 == 0) {
                    total += playCoinGame(n - 2, -player);
                } else {
                    total += playCoinGame(n - 1, -player);
                    total += playCoinGame(n - 2, -player);
                    total += playCoinGame(n - 4, -player);
                }
            }
        }
        return total;
    }
}
