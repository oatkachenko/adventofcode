void main(String[] args) throws IOException {
    List<String> strings = Files.readAllLines(Path.of(args[0]));
    byte[][] matrix = Common.createMatrix(strings);
    int numOfRemoved = 0;
    for (int i = 0; i < matrix.length; i++)
        for (int j = 0; j < matrix[i].length; j++)
            if (Common.isAccessible(matrix, i, j))
                numOfRemoved += deleteRecursively(matrix, i, j);
    Common.printMap(matrix);
    IO.println(numOfRemoved);
}

/**
 * Recursively deletes all accessible cells starting from the given cell.
 * Starts from the cell with the given coordinates,
 * deletes it and tries to do the same for all adjacent, non-empty cells.
 *
 * @return the number of removed cells
 */
private static int deleteRecursively(byte[][] matrix, int i, int j) {
    LinkedList<int[]> queue = new LinkedList<>();
    queue.add(new int[]{i, j});
    int numOfRemoved = 0;
    int[] coords;
    while ((coords = queue.poll()) != null) {
        if (!Common.hasRoll(matrix, coords[0], coords[1])) continue; // Empty cell
        matrix[coords[0]][coords[1]] = -1; // Marked as removed
        numOfRemoved++; // Count the removed cells
        for (int k = coords[0] - 1; k <= coords[0] + 1; k++)
            for (int l = coords[1] - 1; l <= coords[1] + 1; l++)
                if (Common.isAccessible(matrix, k, l))
                    queue.add(new int[]{k, l}); // Add adjacent cells to the queue
    }
    return numOfRemoved;
}