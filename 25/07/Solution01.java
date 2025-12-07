void main(String[] args) throws IOException {
    List<String> strings = Files.readAllLines(Path.of("./25/07/real-input.txt"));
    String topLvl = strings.getFirst();
    int[] beams = new int[topLvl.length()];

    for (int i = 0; i < topLvl.length(); i++)
        if (topLvl.charAt(i) == 'S')
            beams[i] = 1;

    int beamSplits = 0;
    for (int i = 1; i < strings.size(); i++) {
        char[] row = strings.get(i).toCharArray();
        int[] newBeams = new int[beams.length];
        for (int j = 0; j < beams.length; j++) {
            if (beams[j] > 0) {
                if (row[j] == '^') {
                    beamSplits++;
                    newBeams[j - 1] = 1;
                    newBeams[j + 1] = 1;
                } else {
                    newBeams[j] = 1;
                }
            }
        }
        beams = newBeams;
    }
    IO.println(beamSplits);
}