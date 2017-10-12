package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ContainsSeqTest {
    @Test
    public void whenPriveIveThenTrue() {
        ContainsSeq seq = new ContainsSeq();
        boolean result = seq.contains("Privet", "ive");
        assertThat(result, is(true));
    }
    @Test
    public void whenTiruvanuntapuramTapuramThenTrue() {
        ContainsSeq seq = new ContainsSeq();
        boolean result = seq.contains("Tiruvanuntapuram", "tapuram");
        assertThat(result, is(true));
    }
    @Test
    public void whenTaryBaryThenTaryba() {
        ContainsSeq seq = new ContainsSeq();
        boolean result = seq.contains("45jnkjnks3dатттр9орпslkf5", "kjnks3dатттр9орпs");
        assertThat(result, is(true));
    }
}