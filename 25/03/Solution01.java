void main(String[] args) throws IOException {
    List<String> strings = Files.readAllLines(Path.of(args[0]));
    long sum = 0;
    for (String bank : strings) {
        int length = bank.length();
        int hi = 0, lo = 0;
        for (int i = 0; i <= length - 2; i++) {
            char c1 = bank.charAt(i);
            char c2 = bank.charAt(i + 1);
            if (c1 > hi) {
                hi = c1;
                lo = c2;
            } else if (c2 > lo)
                lo = c2;
        }
        hi = hi - '0';
        lo = lo - '0';
        if (0 <= lo)
            hi = hi * 10 + lo;
        sum += hi;
    }
    IO.println(sum);
}
