void main(String[] args) throws IOException {
    List<String> strings = Files.readAllLines(Path.of(args[0]));
    long sum = 0;
    for (String bank : strings) {
        int length = bank.length();
        char[] buff = new char[12];
        int startIdx = 0;
        for (int i = 0; i < 12; i++) {
            int maxVal = 0;
            for (int j = startIdx; j <= length - (12 - i); j++) {
                char c = bank.charAt(j);
                if (c > maxVal) {
                    maxVal = c;
                    buff[i] = c;
                    startIdx = j + 1;
                }
            }
        }
        sum += Long.parseLong(new String(buff));
    }
    IO.println(sum);
}
