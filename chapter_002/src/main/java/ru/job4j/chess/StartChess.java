package ru.job4j.chess;

public class StartChess {
    public static void main(String[] args) {
        Board board = new Board();
        board.fillboard();
        int stepSource;
        int stepDist;
        Cell sourse;
        Cell dist;

        do {
            stepSource = Integer.valueOf(board.ask("Выберите клетку для старта: "));
            System.out.println(stepSource);
            if (stepSource == 0) {
                return;
            }
            sourse = board.pack(stepSource);
            System.out.println(sourse.x);
            stepDist = Integer.valueOf(board.ask("Сделайте ход: "));
            System.out.println(stepDist);
            dist = board.pack(stepDist);
            System.out.println(dist.x);
            board.move(sourse, dist);
        } while (true);
    }
}