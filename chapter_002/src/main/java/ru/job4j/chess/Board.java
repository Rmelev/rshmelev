package ru.job4j.chess;

import ru.job4j.Input;
import ru.job4j.chess.exceptions.FigureNotFoundException;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.exceptions.OccupiedWayException;

/**
 * chess board.
 */
public class Board {
    /**
     * array of figures.
     */
    private Figures[] figures = new Figures[2];

    /**
     * counter of figures in array.
     */
    private int count = 0;
    /**
     * variable for StubInput tests.
     */
    private Input input;

    /**
     * Constructor.
     * @param input - stubinput.
     */
    public Board(Input input) {
        this.input = input;
    }

    /**
     * game method.
     */
    void init() {
        addFigure(new Bishop(new Cell(7, 2)));
        addFigure(new Bishop(new Cell(5, 4)));
        do {
            try {
                int xSource = Integer.valueOf(input.ask("Ведите координату х исходной позиции: "));
                int ySource = Integer.valueOf(input.ask("Ведите координату y исходной позиции: "));
                int xDist = Integer.valueOf(input.ask("Ведите координату х ячейки, куда идете: "));
                int yDist = Integer.valueOf(input.ask("Ведите координату y ячейки, куда идете: "));
                for (Figures arr : figures) {
                    if (move(new Cell(xSource, ySource), new Cell(xDist, yDist)) && arr.position.x == xSource && arr.position.y == ySource) {
                        arr.clone(new Cell(xDist, yDist));
                        break;
                    }
                }
                System.out.println("Успешный ход! ");
            } catch (FigureNotFoundException fne) {
                System.out.println("В выбранной ячейке нет фигуры. Выберите другую ячеку: ");
            } catch (ImpossibleMoveException ime) {
                System.out.println("Так фигура не ходит. Выберите другую ячейку: ");
            } catch (OccupiedWayException owe) {
                System.out.println("Путь к ячеке занят. Выберите другой ход: ");
            }
        } while (input.ask("Хотите продолжить? y/n ").equals("y"));
    }

    /**
     * getter for x Cell position (for testing).
     * @return - x.
     */
    int getFigureNewCoordinateX() {
        return figures[0].position.x;
    }

    /**
     * getter for y Cell position (for testing).
     * @return - y.
     */
    int getFigureNewCoordinateY() {
        return figures[0].position.y;
    }

    /**
     * adder of figures.
     * @param figure - figure for add.
     */
    void addFigure(Figures figure) {
        figures[count++] = figure;
    }

    /**
     * possible of figure trancefer.
     * @param source - from where.
     * @param dist - where.
     * @return - possible of figure trancefer.
     * @throws ImpossibleMoveException - ImpossibleMoveException.
     * @throws OccupiedWayException - OccupiedWayException.
     * @throws FigureNotFoundException - FigureNotFoundException.
     */
    boolean move(Cell source, Cell dist) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        boolean flag = false;
        int finder = -1;

        for (int i = 0; i < figures.length; i++) {
            if (source.x == figures[i].position.x && source.y == figures[i].position.y) {
                flag = true;
                finder = i;
                break;
            }
        }
        if (!flag) {
            throw new FigureNotFoundException("В ячеке, из которой ходите, нет фигуры. ");
        }

        Cell[] cellArr = figures[finder].way(dist);

        if (cellArr == null) {
            throw new ImpossibleMoveException("Туда не ходит. ");
        }
        for (Figures arrFig : figures) {
            if (dist.x == arrFig.position.x && dist.y == arrFig.position.y) {
                throw new ImpossibleMoveException("Целевая ячейка занята. ");
            }
        }
        for (Figures arrFig : figures) {
            for (Cell cell : cellArr) {
                if (cell.x == arrFig.position.x && cell.y == arrFig.position.y) {
                    throw new OccupiedWayException("На пути стоит фигура. Так ходить нельзя. ");
                }
            }
        }
        return true;
    }
}