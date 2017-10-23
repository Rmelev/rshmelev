package ru.job4j.chess;

import java.util.Scanner;
import java.util.Arrays;

public class Board {

    private Scanner scanner = new Scanner(System.in);
    Cell[] board = new Cell[64];
    public void fillboard() {
        board[0] = new Rook(0,true);
        board[1] = new Knight(1,true);
        board[2] = new Bishop(2,true);
        board[3] = new Queen(3,true);
        board[4] = new King(4,true);
        board[5] = new Bishop(5,true);
        board[6] = new Knight(6,true);
        board[7] = new Rook(7,true);
        for (int i = 8; i < 16; i++) {
            board[i] = new Pawn(i,true);
        }
        for (int i = 16; i < 47; i++) {
            board[i] = new Cell(i, false);
        }
        for (int i = 47; i < 56; i++) {
            board[i] = new Pawn(i,true);
        }
        board[56] = new Rook(56,true);
        board[57] = new Knight(57,true);
        board[58] = new Bishop(58,true);
        board[59] = new Queen(59,true);
        board[60] = new King(60,true);
        board[61] = new Bishop(61,true);
        board[62] = new Knight(62,true);
        board[63] = new Rook(63,true);
    }

    public void move(Cell source, Cell dist) {
        if (source.specify(source, dist)) {
            System.out.println("Вошел в 1-й if!");
            System.out.println(board[dist.x]);
            if (!board[dist.x].y) {
                Cell temp = board[dist.x];
                board[dist.x] = board[source.x];
                board[dist.x].x = temp.x;
                board[source.x].y = false;
                board[dist.x].y = true;
                System.out.println("Успешный ход!");
            } else {
                System.out.println("Ячейка занята. Введите другой ход: ");
            }
        } else {
            System.out.println("Так выбранная фигура не ходит. Повторите ход: ");
        }

    }

    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    public Cell pack(int step) {
        return board[step];
    }




    private static class Bishop extends Cell{

        public Bishop(int x, boolean y) {
            this.x = x;
            this.y = y;
        }

        /**
         * ищет перечень доступных ходов, не считая фигур на пути.
         * @param step - ячейка, куда хотим ходить.
         * @return - массив значений возможных ходов.
         */
        public int[] findRange(int step) {
            int[] range = new int[32];
            for (int i = 0; i < range.length; i++) {
                range[i] = -1;
            }
            int[][] rangeSq = new int[8][8];
            int countSq = 0;
            int count = 0;
            int[] xy = trans(step);
            int x = xy[0];
            int y = xy[1];
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    rangeSq[i][j] = countSq++;
                }
            }
            for (int i = 1; i < 8; i++) {
                if (x - i >= 0 && y - i >= 0) {
                    range[count++] = rangeSq[x - i][y - i];
                }
                if (x - i >= 0 && y + i < 8) {
                    range[count++] = rangeSq[x - i][y + i];
                }
                if (x + i < 8 && y - i >= 0) {
                    range[count++] = rangeSq[x + i][y - i];
                }
                if (x + i < 8 && y + i < 8) {
                    range[count++] = rangeSq[x + i][y + i];
                }
            }

            return Arrays.copyOf(range, count);
        }

        boolean specify(Cell cellSource, Cell cellDist) {
            System.out.println("Вошел!");
            boolean flag = false;
            System.out.println("cellSource.statement: " + cellSource);
            int[] range = findRange(cellSource.x);
            System.out.println("cellDist.x: " + cellDist.x);
            System.out.println("cellSource.x: " + cellSource.x);
            for (int i = 0; i < range.length; i++) {
                System.out.print(range[i] + " ");
            }
            System.out.println();
            for (int i = 0; i < range.length; i++) {
                System.out.println("Вошел в цикл!");
                System.out.println("cellDist.x " + cellDist.x);
                if (cellDist.x == range[i]) {
                    System.out.println("Вошел в if!");
                    flag = true;
                    break;
                }
            }
            return flag;
        }
    }

    private static class King extends Cell {
        public King(int x, boolean y) {
            this.x = x;
            this.y = y;
        }

        boolean specify(Cell cellSource, Cell cellDist) {
            boolean flag = false;
            return flag;
        }
    }

    private static class Queen extends Cell {
        public Queen(int x, boolean y) {
            this.x = x;
            this.y = y;
        }

        boolean specify(Cell cellSource, Cell cellDist) {
            boolean flag = false;
            return flag;
        }
    }

    private static class Rook extends Cell {
        public Rook(int x, boolean y) {
            this.x = x;
            this.y = y;
        }

        boolean specify(Cell cellSource, Cell cellDist) {
            boolean flag = false;
            return flag;
        }
    }

    private static class Pawn extends Cell {
        public Pawn(int x, boolean y) {
            this.x = x;
            this.y = y;
        }

        boolean specify(Cell cellSource, Cell cellDist) {
            boolean flag = false;
            return flag;
        }
    }

    private static class Knight extends Cell {
        public Knight(int x, boolean y) {
            this.x = x;
            this.y = y;
        }

        boolean specify(Cell cellSource, Cell cellDist) {
            boolean flag = false;
            return flag;
        }
    }
}