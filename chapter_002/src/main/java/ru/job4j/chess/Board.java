package ru.job4j.chess;

import java.util.Scanner;
import java.util.Arrays;

public class Board {

    private Scanner scanner = new Scanner(System.in);
    Cell[] board = new Cell[64];
    public void fillboard() {
        board[0] = new Cell(0, new Rook());
        board[1] = new Cell(1, new Knight());
        board[2] = new Cell(2, new Bishop());
        board[3] = new Cell(3, new Queen());
        board[4] = new Cell(4, new King());
        board[5] = new Cell(5, new Bishop());
        board[6] = new Cell(6, new Knight());
        board[7] = new Cell(7, new Rook());
        for (int i = 8; i < 16; i++) {
            board[i] = new Cell(i, new Pawn());
        }
        for (int i = 16; i < 47; i++) {
            board[i] = new Cell (i,null);
        }
        for (int i = 47; i < 56; i++) {
            board[i] = new Cell(i, new Pawn());
        }
        board[56] = new Cell(56, new Rook());
        board[57] = new Cell(57, new Knight());
        board[58] = new Cell(58, new Bishop());
        board[59] = new Cell(59, new Queen());
        board[60] = new Cell(60, new King());
        board[61] = new Cell(61, new Bishop());
        board[62] = new Cell(62, new Knight());
        board[63] = new Cell(63, new Rook());
    }

    public void move(Cell source, Cell dist) {
        if (source.statement.specify(source, dist)) {
            System.out.println("Вошел в 1-й if!");
            System.out.println(board[dist.x].statement);
            if (board[dist.x].statement == null) {
                board[dist.x].x = board[dist.x].x;
                board[dist.x].statement = board[source.x].statement;
                System.out.println("Теперь ячейка, куда я встал, не ссылается на null: " + board[dist.x].statement);
                board[source.x].statement = null;
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




    private static class Bishop extends Figures{

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
            int[] range = findRange(cellSource.x);
            System.out.println("cellDist.x: " + cellDist.x);
            System.out.println("cellSource.x: " + cellSource.x);
            for (int i = 0; i < range.length; i++) {
                System.out.print(range[i] + " ");
            }
            System.out.println();
            for (int i = 0; i < range.length; i++) {
                System.out.println("Вошел в цикл!");
                if (cellDist.x == range[i]) {
                    System.out.println("Вошел в if!");
                    flag = true;
                    break;
                }
            }
            return flag;
        }
    }

    private static class King extends Figures {
        boolean specify(Cell cellSource, Cell cellDist) {
            boolean flag = false;
            return flag;
        }
    }

    private static class Queen extends Figures {
        boolean specify(Cell cellSource, Cell cellDist) {
            boolean flag = false;
            return flag;
        }
    }

    private static class Rook extends Figures {
        boolean specify(Cell cellSource, Cell cellDist) {
            boolean flag = false;
            return flag;
        }
    }

    private static class Pawn extends Figures {
        boolean specify(Cell cellSource, Cell cellDist) {
            boolean flag = false;
            return flag;
        }
    }

    private static class Knight extends Figures {
        boolean specify(Cell cellSource, Cell cellDist) {
            boolean flag = false;
            return flag;
        }
    }
}