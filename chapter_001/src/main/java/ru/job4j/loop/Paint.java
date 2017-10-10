package ru.job4j.loop;

public class Paint {
    public String piramid(int h) {
        String x = "^";
        String o = " ";
        int width = h * 2 - 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < width/2 - i; j++) builder.append(o);
            for (int j = width/2 - i; j < width/2 +1 + i; j++) builder.append(x);
            //for (int j = width/2 +1 + i; j < width; j++) builder.append(o); - отлично рисуется
            // без этой строки, так как пробелов не видно. Но, если вместо пробелов будут знаки
            // эту строчку можно активировать.
            builder.append("\n");

        }
        return builder.toString();
    }
}