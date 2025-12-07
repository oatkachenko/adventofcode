void main(String[] args) throws IOException {
    List<String> strings = Files.readAllLines(Path.of(args[0]));
    String topLvl = strings.getFirst();
    long[] beams = new long[topLvl.length()];
    for (int i = 0; i < topLvl.length(); i++)
        if (topLvl.charAt(i) == 'S')
            beams[i] = 1;
    for (int row = 1; row < strings.size(); row++) {
        char[] nextRow = strings.get(row).toCharArray();
        long[] nextRowBeams = new long[beams.length];
        for (int i = 0; i < beams.length; i++) {
            if (beams[i] > 0) {
                if (nextRow[i] == '^') {
                    nextRowBeams[i - 1] += beams[i];
                    nextRowBeams[i + 1] += beams[i];
                } else {
                    nextRowBeams[i] += beams[i];
                }
            }
        }
        beams = nextRowBeams;
    }
    IO.println(Arrays.stream(beams).sum());
}