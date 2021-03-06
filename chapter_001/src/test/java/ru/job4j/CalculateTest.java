package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test.
*
* @author Roman Shmelev (mailto:roman.shmelev@gmail.com)
* @version $Id$
* @since 0.1
*/

public class CalculateTest {

  /**
    * Test echo.
    */
  @Test
  public void whenTakeNameThenTreeEchoPlusName() {
	String input = "Roman Shmelev";
	String expect = "Echo, echo, echo : Roman Shmelev";
	Calculate calc = new Calculate();
	String result = calc.echo(input);
	assertThat(result, is(expect));
  }
}