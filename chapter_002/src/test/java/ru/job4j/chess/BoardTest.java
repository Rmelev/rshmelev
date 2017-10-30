package ru.job4j.chess;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import ru.job4j.StubInput;
import ru.job4j.Input;

/**
 * test.
 */
public class BoardTest {
    /**
     * Test of bishop transfer (coordinate x).
     */
    @Test
    public void whenXThenX() {
        Input input = new StubInput(new String[]{"7", "2", "5", "0", "y", "5", "0", "2", "3", "n"});
        Board board = new Board(input);
        board.init();
        assertThat(board.getArrCell()[0].getX(), is(2));
    }

    /**
     * Test of bishop transfer (coordinate y).
     */
    @Test
    public void whenYThenY() {
        Input input = new StubInput(new String[]{"7", "2", "5", "0", "y", "5", "0", "2", "3", "n"});
        Board board = new Board(input);
        board.init();
        assertThat(board.getArrCell()[0].getY(), is(3));
    }

    /**
     * test for impossible transfer (figure on the way).
     */
    @Test
    public void whenFigureOnTheWayThenOldCoordinates() {
        Input input = new StubInput(new String[]{"7", "2", "4", "5", "y", "4", "5", "3", "4", "n"});
        Board board = new Board(input);
        board.init();
        assertThat(board.getArrCell()[0].getX(), is(7));
    }

    /**
     * test for impossible transfer (this kind of figures can't step like this).
     */
    @Test
    public void whenFigureCanNotStepLikeThisThenOldCoordinates() {
        Input input = new StubInput(new String[]{"7", "2", "2", "0", "y", "2", "0", "4", "2", "n"});
        Board board = new Board(input);
        board.init();
        assertThat(board.getArrCell()[0].getX(), is(7));
    }

    /**
     * Test of bishop - 4 directions way.
     */
    @Test
    public void whenFourDirectionsThenSucces() {
        Input input = new StubInput(new String[]{"7", "2", "5", "0", "y", "5", "0", "2", "3", "y", "2", "3", "5", "6", "y", "5", "6", "7", "4", "n"});
        Board board = new Board(input);
        board.init();
        assertThat(board.getArrCell()[0].getY(), is(4));
    }
}
