LinkedList<int[]> queue = new LinkedList<>();
int timelines = 0;

void main(String[] args) throws IOException {
    List<String> strings = Files.readAllLines(Path.of("./25/07/real-input.txt"));
    String topLvl = strings.getFirst();
    boolean[][] map = new boolean[strings.size()][topLvl.length()];
    for (int i = 0; i < strings.size(); i++) {
        String row = strings.get(i);
        for (int j = 0; j < row.length(); j++)
            map[i][j] = row.charAt(j) == '^';
    }
    for (int i = 0; i < topLvl.length(); i++)
        if (topLvl.charAt(i) == 'S') {
            queue.add(new int[]{i, 1});
            while (!queue.isEmpty()) {
                int[] coords = queue.poll();
                continueBeamPath(map, coords[0], coords[1], timelines++);
            }
        }


    IO.println(timelines);
}

private void continueBeamPath(boolean[][] map, int x, int y, int timeline) {
    if (!isValidCoords(map, x, y)) {
        IO.println("Finished timeline: " + timeline + ", left: " + queue.size());
        return;
    }
    int nextRow = y + 1;
    if (map[y][x]) {
        queue.add(new int[]{x + 1, nextRow});
        continueBeamPath(map, x - 1, nextRow, timeline);
    } else {
        continueBeamPath(map, x, nextRow, timeline);
    }
}

private static boolean isValidCoords(boolean[][] map, int x, int y) {
    return x >= 0 && y >= 0 && x < map.length && y < map[0].length;
}