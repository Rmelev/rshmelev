package ru.job4j.chess;

import ru.job4j.chess.exceptions.*;

import java.util.Scanner;

public class Board {

    Figures[] figures = new Figures[2];

    Scanner scanner = new Scanner(System.in);

    int count = 0;

    public void addFigure (Figures figure) {
        figures[count++] = figure;
        //System.out.println("Добавил фигуру: " + figures[count - 1].position.x + " " + figures[count - 1].position.y);
    }

    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    public boolean move(Cell source, Cell dist) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
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
            throw new FigureNotFoundException("Че нах? ");
        }

        Cell[] cellArr = figures[finder].way(dist);

        for (Cell arr : cellArr) {
            System.out.println("Элемент: " + arr.x + "  " + arr.y + "  Длина массива: " + cellArr.length);
        }

        if (cellArr == null) {
            throw new ImpossibleMoveException("Че них? ");
        }
        for (Figures arrFig : figures) {
            // Проверяем, не занята ли ячейка назначения.
            if (dist.x == arrFig.position.x && dist.y == arrFig.position.y) {
                throw new ImpossibleMoveException("Че них? ");
            }
        }
        // Проверяем, нет ли фигур на пути.
        for (Figures arrFig : figures) {
            for (Cell cell : cellArr) {
                System.out.println("x: " + cell.x + "  y: " + cell.y);
                if (cell.x == arrFig.position.x && cell.y == arrFig.position.y) {
                    throw new OccupiedWayException("Че нух? ");
                }
                System.out.println("Один проход закончен ");
            }
            System.out.println("Одна фигура закончена ");
        }
        return true;
    }

}