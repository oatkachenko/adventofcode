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
            if (containOnlyRepeatedSeq(String.valueOf(i).toCharArray()))
                sum += i;
    }
    IO.println(sum);
}

/**
 * Iterates over all possible parts of the given char array and checks
 * if they can be repeated to form the whole array.
 */
private static boolean containOnlyRepeatedSeq(char[] chars) {
    int length = chars.length;
    // The minimal number of parts is 2, so the maximal part length is half of the array length
    int maxPartLength = length / 2;
    for (int i = 1; i <= maxPartLength; i++) {
        if (includes(chars, i, i))
            return true;
    }
    // We didn't find any part that could be repeated to form the whole array
    return false;
}

/**
 * Checks if the source array consists only of the given parts starting from the offset.
 */
private static boolean includes(char[] source, int partLength, int offset) {
    // Fail fast, no need to check the rest of the array
    if (!hasPart(source, partLength, offset))
        return false;
    // Otherwise, calculate the next offset to check
    int nextOffset = offset + partLength;
    // If the next part is out of bounds, then the last part has been processed, return true
    if (source.length < nextOffset + partLength)
        return nextOffset == source.length;
    // Otherwise, continue checking the rest of the array
    return includes(source, partLength, nextOffset);
}

/**
 * Checks if the given chars array consists of the given part starting from the given offset.
 */
private static boolean hasPart(char[] chars, int partLength, int offset) {
    for (int i = 0; i < partLength; i++)
        if (chars[offset + i] != chars[i])
            return false;
    return true;
}