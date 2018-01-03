package ru.job4j.nonblocking;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test non blocking cash.
 */
public class NonBlockCashTest {
    /**
     * non blocking cash.
     */
    private NonBlockCash cash = new NonBlockCash();

    /**
     * Test1.
     */
    @Test
    public void whenAddDeleteUpdateElemThenCorrectResult() {
        Model m1 = new Model("Margo");
        Model m2 = new Model("Diane");
        Model m3 = new Model("Ro");
        cash.add("ahh", m1);
        cash.add("uhh", m2);
        cash.add("ehh", m3);
        assertThat(cash.getCache().get("ahh"), is(m1));
        assertThat(cash.getCache().get("uhh"), is(m2));
        cash.delete("ahh");
        assertThat(cash.getCache().containsKey("ahh"), is(false));
        assertThat(cash.getCache().containsValue(m1), is(false));
        assertThat(cash.getCache().containsKey("uhh"), is(true));
        assertThat(cash.getCache().containsValue(m2), is(true));
        m2.setName("Soleil");
        System.out.println(m2);
        cash.update("uhh", m2);
        System.out.println(m2);
        assertThat(cash.getCache().containsValue(m2), is(true));
        assertThat(m2.getVersion(), is(1));
        assertThat(cash.getCache().get("uhh").getName(), is("Soleil"));
    }
}