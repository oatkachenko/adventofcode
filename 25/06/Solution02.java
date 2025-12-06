void main(String[] args) throws IOException {
    List<String> strings = Files.readAllLines(Path.of(args[0]));
    ValReader opReader = new ValReader(strings.getLast());
    int lastRowWithNumbers = strings.size() - 2;
    long sum = 0;
    int colIdx = 0;
    while (opReader.hasNext()) {
        String operation = opReader.nextVal();
        StringBuilder columnVal;
        boolean sumOperation = operation.equals("+");
        long accum = sumOperation ? 0 : 1;
        do {
            columnVal = new StringBuilder();
            for (int i = 0; i <= lastRowWithNumbers; i++) {
                String nextRow = strings.get(i);
                if (nextRow.length() <= colIdx)
                    continue;
                char c = nextRow.charAt(colIdx);
                if (c != ' ')
                    columnVal.append(c);
            }
            colIdx++;
            if (!columnVal.isEmpty()) {
                long l = Long.parseLong(columnVal.toString());
                if (sumOperation)
                    accum += l;
                else accum *= l;
            }
        } while (!columnVal.isEmpty());
        sum += accum;
    }
    IO.println(sum);
}
