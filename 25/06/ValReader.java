class ValReader {
    final String source;
    int index;

    ValReader(String source) {
        this.source = source;
    }

    boolean hasNext() {
        return index < source.length();
    }

    String nextVal() {
        StringBuilder sb = new StringBuilder();
        while (hasNext()) {
            char c = source.charAt(index);
            index++;
            if (isSpace(c)) {
                if (!sb.isEmpty()) break;
                else continue;
            }
            sb.append(c);
        }
        return sb.toString();
    }

    private static boolean isSpace(char c) {
        return c == ' ';
    }
}