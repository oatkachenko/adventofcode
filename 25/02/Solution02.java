void main(String[] args) throws IOException {
    String input = Files.readString(Path.of(args[0]));
    String[] rangeArr = input.trim().split(",");
    long sum = 0;
    for (String range : rangeArr) {
        String[] rangeParts = range.split("-");
        long min = Long.parseLong(rangeParts[0]);
        long max = Long.parseLong(rangeParts[1]);
        // Iterate over all numbers in the range
        for (long i = min; i <= max; i++)
            if (containOnlyRepeatedSeq(String.valueOf(i)))
                sum += i;
    }
    IO.println(sum);
}

/**
 * Iterates over all possible parts of the given string and checks
 * if they can be repeated to form the whole string.
 */
private static boolean containOnlyRepeatedSeq(String s) {
    int length = s.length();
    // The minimal number of parts is 2, so the maximal part length is half of the string length
    int maxPartLength = length / 2;
    for (int i = 1; i <= maxPartLength; i++) {
        // Try to split the string into parts of length 'i' and check if
        // the string consists only of these parts
        String part = s.substring(0, i);
        if (includes(s, part, 0))
            return true;
    }
    // We didn't find any part that could be repeated to form the whole string
    return false;
}

/**
 * Checks if the source string consists only of the given parts starting from the offset.
 */
private static boolean includes(String source, String part, int offset) {
    // Fail fast, no need to check the rest of the string
    if (!source.startsWith(part, offset))
        return false;
    // Otherwise, calculate the next offset to check
    int nextOffset = offset + part.length();
    // If 'nextOffset' is out of bounds, then the last part has been processed, return true
    if (source.length() <= nextOffset)
        return true;
    // Otherwise, continue checking the rest of the string
    return includes(source, part, nextOffset);
}