void main(String[] args) throws IOException {
    List<String> strings = Files.readAllLines(Path.of(args[0]));
    int[][] corners = new int[strings.size()][2];
    for (int i = 0; i < strings.size(); i++) {
        String[] sCoords1 = strings.get(i).split(",");
        corners[i][0] = Integer.parseInt(sCoords1[0]);
        corners[i][1] = Integer.parseInt(sCoords1[1]);
    }
    long biggestArea = 0;
    for (int i = 0; i < corners.length - 1; i++) {
        int x1 = corners[i][0], y1 = corners[i][1];
        for (int j = i + 1; j < corners.length; j++) {
            int x2 = corners[j][0], y2 = corners[j][1];
            long area = Math.max(biggestArea, (long) (Math.abs(x1 - x2) + 1) * (Math.abs(y1 - y2) + 1));
            if (area > biggestArea) biggestArea = area;
        }
    }
    IO.println(biggestArea);
}