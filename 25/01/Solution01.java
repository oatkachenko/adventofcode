void main(String[] args) throws IOException {
    int position = 50;
    int stopsAtZero = 0;
    for (String string : Files.readAllLines(Path.of(args[0]))) {
        int direction = string.charAt(0) == 'L' ? -1 : 1;
        int steps = Integer.parseInt(string.substring(1)) * direction;
        position = (position + steps) % 100;
        if (position < 0)
            position += 100;
        if (position == 0)
            stopsAtZero++;
    }
    IO.println(stopsAtZero);
}