package ru.job4j.chess;

import ru.job4j.chess.exceptions.*;

abstract class Figures {
    Cell position;

    public Figures(Cell position) {
        this.position = position;
    }

    public Figures() {

    }

    abstract Cell[] way(Cell dist) throws ImpossibleMoveException;

    public Figures clone(Cell dist) {
        this.position.x = dist.x;
        this.position.y = dist.y;
        return this;
    }

}