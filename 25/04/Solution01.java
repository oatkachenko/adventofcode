void main(String[] args) throws IOException {
    List<String> strings = Files.readAllLines(Path.of(args[0]));
    byte[][] matrix = Common.createMatrix(strings);
    int numOfAccessible = 0;
    for (int i = 0; i < matrix.length; i++)
        for (int j = 0; j < matrix[i].length; j++)
            if (Common.isAccessible(matrix, i, j)) {
                matrix[i][j] = 2; // Marked as accessible, just for visualization
                numOfAccessible++;
            }
    IO.println(numOfAccessible);
    Common.printMap(matrix);
}