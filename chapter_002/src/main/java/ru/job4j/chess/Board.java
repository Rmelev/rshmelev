package ru.job4j.chess;

//import ru.job4j.ConsoleInput;
import ru.job4j.Input;
import ru.job4j.MenuOutException;
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
    private Figures[] figures = new Figures[32];

    /**
     * array of cells.
     */
    private Cell[] arrCell = new Cell[32];

    /**
     * counter of cells in array.
     */
    private int countCell = 0;

    /**
     * counter of figures in array.
     */
    private int countFigure = 0;

    /**
     * variable for StubInput tests.
     */
    private Input input;

    /**
     * range of possible steps.
     */
    private int[] range = {0, 1, 2, 3, 4, 5, 6, 7};

    /**
     * Constructor.
     * @param input - stubinput.
     */
    public Board(Input input) {
        this.input = input;
        Cell cell72 = new Cell(7, 2);
        addCell(cell72);
        Bishop bishop1 = new Bishop(cell72);
        addFigure(bishop1);
        Cell cell54 = new Cell(5, 4);
        Bishop bishop2 = new Bishop(cell54);
        addFigure(bishop2);
        addCell(cell54);
    }

    /**
     * getter.
     * @return - array of engaged cells.
     */
    public Cell[] getArrCell() {
        return this.arrCell;
    }

    /**
     * getter.
     * @return - range.
     */
    public int[] getRange() {
        return range;
    }

    /**
     * adder of cells.
     * @param cell - additional cell.
     */
    void addCell(Cell cell) {
        arrCell[countCell++] = cell;

    }

    /**
     * adder of figures.
     * @param figure - - additional figure.
     */
    void addFigure(Figures figure) {
        figures[countFigure++] = figure;
    }

    /**
     * game method.
     */
    void init() {
        do {
            try {
                int xSource = input.ask("Ведите координату х исходной позиции: ", getRange());
                int ySource = input.ask("Ведите координату y исходной позиции: ", getRange());
                int xDist = input.ask("Ведите координату х ячейки, куда идете: ", getRange());
                int yDist = input.ask("Ведите координату y ячейки, куда идете: ", getRange());
                for (int i = 0; i < arrCell.length; i++) {
                    if (arrCell[i] != null) {
                        if (move(new Cell(xSource, ySource), new Cell(xDist, yDist)) && arrCell[i].getX() == xSource && arrCell[i].getY() == ySource) {
                            figures[i] = figures[i].clone(new Cell(xDist, yDist));
                            arrCell[i] = new Cell(xDist, yDist);
                            break;
                        }
                    }
                }
                System.out.println("Успешный ход! ");
            } catch (FigureNotFoundException fne) {
                System.out.println("В выбранной ячейке нет фигуры. Выберите другую ячеку: ");
            } catch (ImpossibleMoveException ime) {
                System.out.println("Так фигура не ходит. Выберите другую ячейку: ");
            } catch (OccupiedWayException owe) {
                System.out.println("Путь к ячеке занят. Выберите другой ход: ");
            } catch (MenuOutException moe) {
                System.out.println("Выбирайте ячейку из диапазона от 0 до 7: ");
            } catch (NumberFormatException nfe) {
                System.out.println("Номер ячейки может быть только цифрой от 0 до 7. Попробуйте снова: ");
            }
        } while (input.ask("Хотите продолжить? y/n ").equals("y"));
    }


    /**
     * @param source - source cell.
     * @param dist - dist cell.
     * @return - true, if move is possible.
     * @throws ImpossibleMoveException - exception.
     * @throws OccupiedWayException - exception.
     * @throws FigureNotFoundException - exception.
     */
    boolean move(Cell source, Cell dist) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        boolean flag = false;
        int finder = -1;

        for (int i = 0; i < arrCell.length; i++) {
            if (arrCell[i] != null) {
                if (source.getX() == arrCell[i].getX() && source.getY() == arrCell[i].getY()) {
                    flag = true;
                    finder = i;
                    break;
                }
            }
        }
        if (!flag) {
            throw new FigureNotFoundException("В ячеке, из которой ходите, нет фигуры. ");
        }

        Cell[] cellArr = figures[finder].way(dist);

        for (Cell arr : arrCell) {
            if (arr != null) {
                if (dist.getX() == arr.getX() && dist.getY() == arr.getY()) {
                    throw new ImpossibleMoveException("Целевая ячейка занята. ");
                }
            }
        }
        for (Cell arr : arrCell) {
            if (arr != null) {
                for (Cell cell : cellArr) {
                    if (cell.getX() == arr.getX() && cell.getY() == arr.getY()) {
                        throw new OccupiedWayException("На пути стоит фигура. Так ходить нельзя. ");
                    }
                }
            }
        }
        return true;
    }

//    public static void main(String[] args) {
//        Input input = new ConsoleInput();
//        Board board = new Board(input);
//        board.init();
//    }
}