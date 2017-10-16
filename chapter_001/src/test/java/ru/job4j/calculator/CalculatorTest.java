package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * class for test of calculator.
 */
public class CalculatorTest {
    /**
     * test1.
     */
    @Test
    public void whenAddOnePlusOneThenTwo() {
        Calculator calc = new Calculator();
        calc.add(1D, 1D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

    /**
     * test2.
     */
    @Test
    public void whenSubtractFiveMinusTwoThenThree() {
        Calculator calc = new Calculator();
        calc.subtract(5D, 2D);
        double result = calc.getResult();
        double expected = 3D;
        assertThat(result, is(expected));
    }

    /**
     * test3.
     */
    @Test
    public void whenDivEightDivideFourThenTwo() {
        Calculator calc = new Calculator();
        calc.div(8D, 4D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

    /**
     * test4.
     */
    @Test
    public void whenMultipleThreeMultiplyThreeThenNine() {
        Calculator calc = new Calculator();
        calc.multiple(3D, 3D);
        double result = calc.getResult();
        double expected = 9D;
        assertThat(result, is(expected));
    }

}
