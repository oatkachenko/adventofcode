class Range implements Comparable<Range> {
    final long min, max;

    public Range(long min, long max) {
        this.min = min;
        this.max = max;
    }

    public Range(String sMin, String sMax) {
        this(Long.parseLong(sMin), Long.parseLong(sMax));
    }

    public boolean contains(long value) {
        return value >= min && value <= max;
    }

    public boolean intersects(Range other) {
        return contains(other.min) || contains(other.max) || other.contains(min) || other.contains(max);
    }

    public Range merge(Range other) {
        return new Range(Math.min(min, other.min), Math.max(max, other.max));
    }

    public int compareTo(Range other) {
        return Long.compare(min, other.min);
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Range range)) return false;

        return min == range.min && max == range.max;
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(min);
        result = 31 * result + Long.hashCode(max);
        return result;
    }

    public String toString() {
        return "[" + min + ", " + max + "]";
    }

    public static Range parse(String s) {
        String[] r = s.split("-");
        return new Range(Long.parseLong(r[0]), Long.parseLong(r[1]));
    }
}