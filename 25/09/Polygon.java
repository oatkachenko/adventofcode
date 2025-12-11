import static java.lang.Math.max;
import static java.lang.Math.min;

class Polygon {
    private final int[][] pts;

    public Polygon(int[][] pts) {
        this.pts = pts;
    }

    public boolean contains(int[] p) {
        int px = p[0], py = p[1];
        boolean inside = false;
        int n = pts.length;
        for (int i = 0, j = n - 1; i < n; j = i++) {
            int ax = pts[i][0], ay = pts[i][1];
            int bx = pts[j][0], by = pts[j][1];
            if (onSegment(ax, ay, bx, by, px, py))
                return true;
            boolean intersect = (ay > py) != (by > py) && px < (double) (bx - ax) * (py - ay) / (by - ay) + ax;
            if (intersect) inside = !inside;
        }
        return inside;
    }

    private static boolean onSegment(int x1, int y1, int x2, int y2, int px, int py) {
        long cross = (long) (px - x1) * (y2 - y1) - (long) (py - y1) * (x2 - x1);
        if (cross != 0) return false;
        return px >= min(x1, x2) && px <= max(x1, x2) && py >= min(y1, y2) && py <= max(y1, y2);
    }
}