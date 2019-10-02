import java.util.stream.IntStream;

import edu.princeton.cs.algs4.*;

/* KEVIN'S EVERYVOTECOUNTS.JAVA */
public class EveryVoteCounts {

    /* BLOCK VALUES AND THRESHOLD */
    private static int[] allBlocks;
    private static int blockIndex;
    private static int threshold;

    /* MAIN METHOD */
    public static void main(String[] args) {
        StdOut.println("Kevin's EveryVoteCounts.java\n");

        StdOut.println("Enter number of blocks.");
        int blocks = StdIn.readInt();
        allBlocks = new int[blocks];

        StdOut.println("\nEnter block values:");
        int entered = 0;
        while (entered < blocks) {
            int read = StdIn.readInt();
            allBlocks[entered] = read;
            entered++;
        }

        StdOut.println("\nEnter block index:");
        blockIndex = StdIn.readInt();
        threshold = IntStream.of(allBlocks).sum() / 2 + 1;

        if (blockIndex >= 0 && blockIndex < blocks) {
            StdOut.println("\nCritical vote possibilities for N using recursive method 1: " + countCriticalVotesWrapperFunction(allBlocks, blockIndex));
            StdOut.println("Critical vote possibilities for N using recursive method 2: " + countCriticalVotesWrapperFunction2(allBlocks, blockIndex));
        } else {
            StdOut.println("\nError: block index out of bounds.");
        }
    }

    /* WRAPPER METHOD 1 */
    private static int countCriticalVotesWrapperFunction2(int[] blocks, int blockIndex) {
        int[] temp = new int[blocks.length - 1];
        for (int i = 0; i < blocks.length; i++)
            if (i < blockIndex)
                temp[i] = blocks[i];
            else if (i > blockIndex)
                temp[i - 1] = blocks[i];
        return countCriticalVotes2(temp, blocks[blockIndex], 0, 0, 0);
    }

    /* COUNT CRITICAL VOTE FUNCTION 1 */
    private static int countCriticalVotes2(int[] blocks, int blockValue, int currentIndex, int currentSum, int critical) {
        if (currentIndex == blocks.length) {
            if (currentSum < threshold && currentSum + blockValue >= threshold) return critical + 1;
        } else if (currentIndex < blocks.length)
            return countCriticalVotes2(blocks, blockValue, currentIndex + 1, currentSum + blocks[currentIndex], critical) + countCriticalVotes2(blocks, blockValue, currentIndex + 1, currentSum, critical);
        return 0;
    }

    /* WRAPPER METHOD 2 */
    private static int countCriticalVotesWrapperFunction(int[] blocks, int blockIndex) {
        int[] temp = new int[blocks.length - 1];
        for (int i = 0; i < blocks.length; i++)
            if (i < blockIndex)
                temp[i] = blocks[i];
            else if (i > blockIndex)
                temp[i - 1] = blocks[i];
        return countCriticalVotes(temp, blocks[blockIndex]);
    }

    /* COUNT CRITICAL VOTE FUNCTION 2 */
    private static int countCriticalVotes(int[] blocks, int blockCount) {
        int total = 0;
        int currentSum = IntStream.of(blocks).sum();

        if (currentSum < threshold && blockCount + currentSum >= threshold)
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
