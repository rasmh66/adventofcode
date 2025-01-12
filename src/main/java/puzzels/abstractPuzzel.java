package puzzels;

import java.util.List;

public class abstractPuzzel {

    protected final String fileName;

    public abstractPuzzel(int jaar, int puzzelNr) {
        String dir = System.getProperty("user.dir");
        fileName = dir + "/input/" + jaar + "/input" + puzzelNr + ".txt";
    }

    // Function to convert List<char[]> to char[][]
    public static char[][] convertListToCharArray(List<char[]> charList) {
        int rows = charList.size();
        int cols = charList.get(0).length; // Assuming all char[] have the same length

        char[][] charArray = new char[rows][cols];

        return charList.toArray(charArray);
    }

    public static Character[][] convertListToCharacterArray(List<Character[]> charList) {
        int rows = charList.size();
        int cols = charList.get(0).length; // Assuming all char[] have the same length

        Character[][] charArray = new Character[rows][cols];

        return charList.toArray(charArray);
    }

    public static char[][] transposeMatrix(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        char[][] transposed = new char[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposed[j][i] = matrix[i][j];
            }
        }

        return transposed;
    }

    // Functie om de matrix af te drukken
    public static void printMatrix(char[][] matrix) {
        for (char[] row : matrix) {
            for (char element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    protected int[] readIntegers(String input, String regex) {

        String[] bits = input.split(regex); //""\\s+");
        int[] nums = new int[bits.length];
        int i = 0;
        for (String s : bits)
            nums[i++] = Integer.parseInt(s);

        return nums;
    }

    protected int[] readIntegers(String input) {

        int[] leeg = new int[2];

        String[] bits = input.split(","); //""\\s+");
//        if (bits.length != 2) return leeg;

        int[] nums = new int[bits.length];
        int i = 0;
        try {
            for (String s : bits) {
                nums[i++] = Integer.parseInt(s);
            }
        } catch (NumberFormatException e) {
            return leeg;
        }

        return nums;
    }

    protected long[] readLong(String input) {

        String[] bits = input.split("\\s+");
        long[] nums = new long[bits.length];
        int i = 0;
        for (String s : bits)
            nums[i++] = Long.parseLong(s);

        return nums;
    }

    protected char[] readChars(String input) {
//        String[] bits = input.split("\\s+");
        char[] nums = input.trim().toCharArray();
        return nums;
    }
    protected int[] readInts(String input) {
//        String[] bits = input.split("\\s+");
        char[] nums = input.trim().toCharArray();
        return new int[2];
    }
}
