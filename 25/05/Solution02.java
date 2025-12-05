void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(args[0]));
    RangeSet freshItemIds = new RangeSet();
    String nextLine;
    while ((nextLine = br.readLine()) != null) {
        if (nextLine.isBlank()) break;
        freshItemIds.add(Range.parse(nextLine));
    }
    long sum = 0;
    for (Range range : freshItemIds.ranges)
        sum += (range.max - range.min + 1);
    IO.println(sum);
}