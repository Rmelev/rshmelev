package ru.job4j.loop;

public class Board {
    public String paint(int width, int height) {
        String x = "x";
        String o = " ";
        int count = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= width; j++) {
                count++;
                if (count % 2 == 0) builder.append(o);
                else builder.append(x);
            }
            builder.append("\n");
        }
        String completedBoard = builder.toString();
        return completedBoard;
    }
}