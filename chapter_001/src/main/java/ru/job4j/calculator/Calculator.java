package ru.job4j.calculator;

/**
  * class Calculator calculate add, subtract, div and multiple of two numbers.
*/
public class Calculator {
    double result;

    /**
     *
     * @param first - first number in our calculation.
     * @param second - second number in our calculation.
     *        this parameters have the same meaning in 3 methods below
     */
    public void add(double first, double second) {
        this.result = first + second;
    }

    public void subtract(double first, double second) {
        this.result = first - second;
    }

    public void div(double first, double second) {
        this.result = first / second;
    }

    public void multiple(double first, double second) {
        this.result = first * second;
    }

    /**
     *
     * @return - return result of calculation.
     */
    public double getResult() {
        return this.result;
    }

  /**
    * Output Message "Hello, World!".
    * @param args - String type array.
  */
  public static void main(String[] args) {
	System.out.println("Hello World");
  }

  /**
    * Method echo.
    * @param name Your name.
    * @return Echo plus your name.
   */
  public String echo(String name) {
    return "Echo, echo, echo : " + name;
}

}
