void main(String[] args) throws IOException {
    String input = Files.readString(Path.of(args[0]));
    String[] split = input.trim().split(",");
    long code = 0;
    for (String strRange : split) {
        String[] range = strRange.split("-");
        long min = Long.parseLong(range[0]);
        long max = Long.parseLong(range[1]);
        for (long i = min; i <= max; i++) {
            if (!isValid(String.valueOf(i))) {
                code += i;
            }
        }
    }
    IO.println(code);
}

private static boolean isValid(String s) {
    int length = s.length();
    if (length % 2 != 0)
        return true;
    String s1 = s.substring(0, length / 2);
    String s2 = s.substring(length / 2);
    return !s1.equals(s2);
}