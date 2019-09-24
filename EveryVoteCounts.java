import java.util.stream.IntStream;

import edu.princeton.cs.algs4.*;

/* KEVIN'S EVERYVOTECOUNTS.JAVA */
public class EveryVoteCounts {

    /* BLOCK VALUES AND THRESHOLD */
    private static final int[] ALL_BUT_N = new int[]{4, 7, 2, 4};
    private static final int blockIndex = 1;
    private static final int THRESHOLD = IntStream.of(ALL_BUT_N).sum() / 2 + 1;

    /* MAIN METHOD */
    public static void main(String[] args) {
        StdOut.println("Kevin's EveryVoteCounts.java\n");

        StdOut.println("N-1 blocks have the following values:");
        for (int i = 0; i < ALL_BUT_N.length; i++)
            StdOut.print(ALL_BUT_N[i] + " ");
        StdOut.println("\n");
        StdOut.print("N block has the following value: ");
        StdOut.println(ALL_BUT_N[blockIndex] + "\n");

        StdOut.println("Critical vote possibilities for N method 1: " + countCriticalVotesWrapperFunction(ALL_BUT_N, blockIndex));
        StdOut.println("Critical vote possibilities for N method 2: " + countCriticalVotesWrapperFunction2(ALL_BUT_N, blockIndex));
    }

    /* WRAPPER METHOD */
    private static int countCriticalVotesWrapperFunction(int[] blocks, int blockIndex) {
        int[] temp = new int[blocks.length - 1];
        for (int i = 0; i < blocks.length; i++)
            if (i < blockIndex)
                temp[i] = blocks[i];
            else if (i > blockIndex)
                temp[i - 1] = blocks[i];
        return countCriticalVotes(temp, blocks[blockIndex]);
    }

    /* COUNT CRITICAL VOTE RECURSIVE */
    private static int countCriticalVotes(int[] blocks, int blockCount) {
        int total = 0;
        int currentSum = IntStream.of(blocks).sum();

        if (currentSum < THRESHOLD && blockCount + currentSum >= THRESHOLD)
            total++;

        if (blocks.length > 0) {
            int[] returned = subBlocks(blocks);
            total += countCriticalVotes(returned, blockCount);

            if (blocks.length > 1) {
                returned = skipBlocks(blocks);
                total += countCriticalVotes(returned, blockCount);
            }
        }
        return total;
    }

    /* WRAPPER METHOD 2 */
    private static int countCriticalVotesWrapperFunction2(int[] blocks, int blockIndex) {
        int[] temp = new int[blocks.length - 1];
        for (int i = 0; i < blocks.length; i++)
            if (i < blockIndex)
                temp[i] = blocks[i];
            else if (i > blockIndex)
                temp[i - 1] = blocks[i];
        return countCriticalVotes2(temp, blocks[blockIndex], 0, 0, 0);
    }

    /* COUNT CRITICAL VOTE FUNCTION 2 */
    private static int countCriticalVotes2(int[] blocks, int blockValue, int currentIndex, int currentSum, int critical) {
        if (currentIndex == blocks.length) {
            if (currentSum < THRESHOLD && currentSum + blockValue >= THRESHOLD) return critical + 1;
        } else if (currentIndex < blocks.length)
            return countCriticalVotes2(blocks, blockValue, currentIndex + 1, currentSum + blocks[currentIndex], critical) + countCriticalVotes2(blocks, blockValue, currentIndex + 1, currentSum, critical);
        return 0;
    }

    /* RETURNS SUB-BLOCKS */
    private static int[] subBlocks(int[] blocks) {
        int[] returned = new int[blocks.length - 1];

        for (int i = 1; i < blocks.length; i++)
            returned[i - 1] = blocks[i];

        return returned;
    }

    /* RETURNS SKIPPING-BLOCK */
    private static int[] skipBlocks(int[] blocks) {
        int[] returned = new int[blocks.length - 1];

        returned[0] = blocks[0];
        for (int i = 2; i < blocks.length; i++)
            returned[i - 1] = blocks[i];

        return returned;
    }
}
