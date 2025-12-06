void main(String[] args) throws IOException {
    List<String> strings = Files.readAllLines(Path.of(args[0]));
    ValReader[] numReaders = new ValReader[strings.size() - 1];
    ValReader opReader = new ValReader(strings.getLast());
    for (int i = 0; i < numReaders.length; i++) numReaders[i] = new ValReader(strings.get(i));
    long sum = 0;
    while (opReader.hasNext()) {
        String operation = opReader.nextVal();
        if (operation.equals("+")) sum += sumNext(numReaders);
        else sum += multiplyNext(numReaders);
    }
    IO.println(sum);
}

private static long sumNext(ValReader[] readers) {
    long sum = 0;
    for (ValReader reader : readers) {
        long aLong = Long.parseLong(reader.nextVal());
        sum += aLong;
    }
    return sum;
}

private static long multiplyNext(ValReader[] readers) {
    long product = 1;
    for (ValReader reader : readers) {
        long aLong = Long.parseLong(reader.nextVal());
        product *= aLong;
    }
    return product;
}