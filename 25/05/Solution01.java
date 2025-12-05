void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(args[0]));
    RangeSet freshItemIds = new RangeSet();
    String nextLine;
    while ((nextLine = br.readLine()) != null) {
        if (nextLine.isBlank()) break;
        freshItemIds.add(Range.parse(nextLine));
    }
    int numOfFreshProducts = 0;
    while ((nextLine = br.readLine()) != null)
        if (freshItemIds.contains(Long.parseLong(nextLine))) numOfFreshProducts++;
    IO.println(numOfFreshProducts);
}