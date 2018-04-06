package ru.job4j.changes;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

//тест на вакансию Java-стажер 6.04.
public class CashMachine {

    private final int[] values;

    public CashMachine(final int[] values) {
        this.values = values;
    }

    public List<List<Integer>> exchange(int note) {
        List<Integer> coins = new ArrayList<>();
        for (Integer next : values) {
            coins.add(next);
        }
        List<List<Integer>> result = new ArrayList<>();

        class Wrapper<T> {
            private T function;
        }
        Wrapper<Function<Integer, BiConsumer<Integer, List<Integer>>>> recursion = new Wrapper<>();
        recursion.function = money -> (numberOfCoin, buffer) -> {
            if (money < 0 || numberOfCoin < 0)
                return;

            if (money == 0) {
                result.add(buffer);
                return;
            }

            recursion.function.apply(money).accept(numberOfCoin - 1, new ArrayList<>(buffer));
            int coin = coins.get(numberOfCoin);
            buffer = new ArrayList<>(buffer);
            buffer.add(coin);
            recursion.function.apply(money - coin).accept(numberOfCoin, buffer);
        };

        recursion.function.apply(note).accept(coins.size() - 1, new ArrayList<>());

        return result;
    }
}

