import static java.lang.Math.*;
import static java.lang.Math.max;

void main(String[] args) throws IOException {
    List<String> strings = Files.readAllLines(Path.of(args[0]));
    int[][] corners = new int[strings.size()][2];

    for (int i = 0; i < strings.size(); i++) {
        String[] sCoords1 = strings.get(i).split(",");
        corners[i][0] = Integer.parseInt(sCoords1[0]);
        corners[i][1] = Integer.parseInt(sCoords1[1]);
    }

    Polygon poly = new Polygon(corners);
    long biggestArea = 0;
    for (int i = 0; i < corners.length - 1; i++) {
        int ax = corners[i][0], ay = corners[i][1];
        out:
        for (int j = i + 1; j < corners.length; j++) {
            int cx = corners[j][0], cy = corners[j][1];
            if (ay == cy || ax == cx) continue;
            long area = max(biggestArea, (long) (abs(ax - cx) + 1) * (abs(ay - cy) + 1));
            if (area > biggestArea) {
                // Check corners are inside polygon
                if (poly.contains(new int[]{ax, cy}) && poly.contains(new int[]{cx, ay})) {
                    // check that rectangle lines are also inside polygon
                    for (int x = min(ax, cx); x < max(ax, cx); x += 100) {
                        if (!poly.contains(new int[]{x, ay})) continue out;
                        if (!poly.contains(new int[]{x, cy})) continue out;
                    }
                    for (int y = min(ay, cy); y < max(ay, cy); y += 100) {
                        if (!poly.contains(new int[]{ax, y})) continue out;
                        if (!poly.contains(new int[]{cx, y})) continue out;
                    }
                    biggestArea = area;
                }
            }
        }
    }
    IO.println(biggestArea);
}