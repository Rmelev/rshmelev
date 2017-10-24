package ru.job4j.chess;

import ru.job4j.chess.exceptions.*;

public class StartChess {
    public static void main(String[] args) {
        Board board = new Board();
        board.addFigure(new Bishop(new Cell(7, 2)));
        board.addFigure(new Bishop(new Cell(5, 4)));
        do {
            try {
                int xSource = Integer.valueOf(board.ask("Ведите координату х исходной позиции: "));
                int ySource = Integer.valueOf(board.ask("Ведите координату y исходной позиции: "));
                int xDist = Integer.valueOf(board.ask("Ведите координату х ячейки, куда идете: "));
                int yDist = Integer.valueOf(board.ask("Ведите координату y ячейки, куда идете: "));
                for (Figures arr : board.figures) {
                    if (board.move(new Cell(xSource, ySource), new Cell(xDist, yDist)) && arr.position.x == xSource && arr.position.y == ySource) {
                        System.out.println("Подошел к клону! ");
                        arr.clone(new Cell(xDist, yDist));
                        System.out.println("Новые координаты: " + arr.position.x + "  " + arr.position.y);
                        break;
                    }
                }
                System.out.println("Успешный ход! ");
            } catch (FigureNotFoundException fne) {
                System.out.println("В выбранной ячейке нет фигуры. Выберите другую ячеку: ");
            } catch (ImpossibleMoveException ime) {
                System.out.println("Так выбранная фигура не ходит. Выберите другую ячейку: ");
            } catch (OccupiedWayException owe) {
                System.out.println("Путь к ячеке занят. Выберите другой ход: ");
            }
        } while (board.ask("Хотите продолжить? y/n ").equals("y"));
    }
}