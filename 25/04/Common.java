import java.util.List;

class Common {
    /**
     * Creates a boolean matrix from the given list of lines.
     * The content of the matrix is determined by the presence of '@' symbols in the lines.
     * -1 - removed
     * 0 - no roll
     * 1 - roll
     * 2 - marked as accessible
     */
    static byte[][] createMatrix(List<String> lines) {
        byte[][] matrix = new byte[lines.size()][lines.getFirst().length()];
        for (int i = 0; i < lines.size(); i++) {
            String s = lines.get(i);
            for (int j = 0; j < s.length(); j++)
                matrix[i][j] = s.charAt(j) == '@' ? (byte) 1 : (byte) 0;
        }
        return matrix;
    }

    /**
     * Checks if the given cell is accessible to forklift.
     * A cell is accessible if it has less than 4 adjacent cells with rolls.
     */
    static boolean isAccessible(byte[][] matrix, int i, int j) {
        if (!hasRoll(matrix, i, j)) return false;
        byte adjacents = 0;
        for (int k = i - 1; k <= i + 1; k++) {
            for (int l = j - 1; l <= j + 1; l++) {
                if (k == i && l == j) continue; // Skip the current cell
                if (hasRoll(matrix, k, l))
                    adjacents++;
            }
        }
        return adjacents < 4;
    }

    /**
     * Checks if the given cell has valid coordinates and contains a roll.
     */
    static boolean hasRoll(byte[][] matrix, int i, int j) {
        boolean isValidCell = !(i < 0 || i >= matrix.length || j < 0 || j >= matrix[i].length);
        return isValidCell && matrix[i][j] > 0;
    }

    /**
     * Prints the given matrix. Just for fun :)
     */
    static void printMap(byte[][] matrix) {
        for (byte[] booleans : matrix) {
            for (byte state : booleans)
                IO.print(state <= 0 ? '.' : state == 1 ? '@' : 'x');
            IO.println();
        }
    }
}
