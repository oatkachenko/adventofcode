import java.util.LinkedList;
import java.util.StringJoiner;
import java.util.TreeSet;

class RangeSet {
    final TreeSet<Range> ranges = new TreeSet<>();

    public void add(Range range) {
        Range merged = range;
        LinkedList<Range> toDelete = new LinkedList<>();
        for (Range existing : ranges) {
            if (existing.intersects(range)) {
                merged = existing.merge(merged);
                toDelete.add(existing);
            }
        }
        for (Range existing : toDelete) {
            ranges.remove(existing);
        }
        ranges.add(merged);
    }

    public boolean contains(long value) {
        for (Range range : ranges)
            if (range.contains(value)) return true;
        return false;
    }

    public String toString() {
        StringJoiner sb = new StringJoiner("\n");
        for (Range range : ranges)
            sb.add(range.toString());
        return sb.toString();
    }
}