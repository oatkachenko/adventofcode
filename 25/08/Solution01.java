void main(String[] args) throws IOException {
    List<String> strings = Files.readAllLines(Path.of(args[0]));
    List<Box> boxes = new ArrayList<>();
    // Create boxes from strings
    for (int i = 0; i < strings.size(); i++)
        boxes.add(Box.createFromString(i, strings.get(i)));

    // Create lines between boxes and sort them by distance
    PriorityQueue<Line> lines = new PriorityQueue<>(Comparator.comparingDouble(a -> a.distance));
    for (int i = 0; i < boxes.size() - 1; i++) {
        Box p = boxes.get(i);
        for (int j = i + 1; j < boxes.size(); j++) {
            Box q = boxes.get(j);
            lines.add(new Line(p, q));
        }
    }

    // Create union find and connect boxes with the shortest distance
    UnionFind uf = new UnionFind(boxes.size());
    int MAX_CONNECTIONS = 1000; // Make at most 1000 connections (task requirement for real-input)
    IO.println("Connecting " + MAX_CONNECTIONS + " boxes...");
    int connectOpCount = 0;
    while (!lines.isEmpty() && connectOpCount < MAX_CONNECTIONS) {
        connectOpCount++;
        Line line = lines.poll();
        uf.union(line.a.id, line.b.id);
    }

    // Find 3 largest circuits
    PriorityQueue<Long> queue = new PriorityQueue<>(3, Comparator.comparingLong(a -> a));
    for (long l : uf.sizes) {
        queue.add(l);
        if (queue.size() > 3) queue.poll();
    }
    // Find product of all 3 largest circuits - this is the answer
    Long res = queue.stream().reduce(1L, (a, b) -> a * b);
    IO.println(res);
}

private static class UnionFind {
    int[] array;
    int[] sizes;

    public UnionFind(int size) {
        array = new int[size];
        sizes = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
            sizes[i] = 1;
        }
    }

    void union(int p, int q) {
        int rootP = array[p];
        int rootQ = array[q];
        if (rootP == rootQ) return; // already connected
        for (int i = 0; i < array.length; i++)
            if (array[i] == rootQ) array[i] = rootP;
        sizes[rootP] += sizes[rootQ]; // sum of sizes of trees containing p and q
        sizes[rootQ] = 0; // means that this element is no longer a root of the tree
    }
}

private static class Line {
    Box a, b;
    double distance;

    Line(Box a, Box b) {
        this.a = a;
        this.b = b;
        this.distance = a.distanceTo(b);
    }

    @Override
    public String toString() {
        return a.id + "-" + b.id + "=[ " + a + "-" + b + " ]" + " (" + distance + ")";
    }
}

private static class Box {
    final int id, x, y, z;

    Box(int id, int x, int y, int z) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    double distanceTo(Box other) {
        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2) + Math.pow(z - other.z, 2));
    }

    @Override
    public String toString() {
        return x + "," + y + "," + z;
    }

    static Box createFromString(int id, String s) {
        String[] parts = s.split(",");
        return new Box(id, Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
    }
}