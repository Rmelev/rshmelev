package ru.job4j.changes;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

//тест на вакансию Java-стажер 6.04.

/**
 *
 */
public class CashMachine {
    /**
     *
     */
    private final int[] values;
    /**
     *
     */
    private List<List<Integer>> result = new ArrayList<>();
    /**
     *
     */
    private List<Integer> arrR = new ArrayList<>();

    /**
     *
     * @param values - values.
     */
    public CashMachine(final int[] values) {
        this.values = values;
    }


//    public static void partition(int n) {
//        partition(n, n, "");
//    }
//    public static void partition(int n, int max, String prefix) {
//        if (n == 0) {
//            System.out.println(prefix);
//            return;
//        }
//
//        for (int i = Math.min(max, n); i >= 1; i--) {
//            partition(n-i, i, prefix + " " + i);
//        }
//    }
//
//
//    public static void main(String[] args) {
////        int N = Integer.parseInt(args[0]);
//        partition(10);
//    }

//    public List<List<Integer>> exchange(int money) {
//        return exchange(money, values.length - 1, 0);
//    }
//
//    public List<List<Integer>> exchange(int maxcoin, int sum, int part) {
////        System.out.println("Вызов с maxcoin: " + maxcoin + "  sum:   " + sum);
////        if (maxcoin == 0 && sum == 0 && arrR.size() == 5) {
////            arrR.add(values[1]);
////        }
//        if (sum == 0) {
//            if (arrR.size() > 0) {
//
//                result.add(arrR);
//                arrR.add(part);
//            }
//            arrR = new ArrayList<>();
//        }
//        if (sum >= values[maxcoin]) {
//            arrR.add(values[maxcoin]);
//            exchange(maxcoin, sum - values[maxcoin], values[maxcoin]);
//        }
//
//        if (maxcoin > 0) {
//            exchange(maxcoin - 1, sum, values[maxcoin]);
//        }
//        return result;
//    }
//
//    public List<List<Integer>> exchange(int note) {
//        return exchange(values.length - 1, note);
//    }


//    public static void main(String[] args) {
//        CashMachine machine = new CashMachine(new int[] {1, 5, 10});
//        List<List<Integer>> newList = machine.exchange(2, 10, 0);
//        System.out.println(newList);
//    }
//    int rem;
//    int count = 0;
//
//    public List<List<Integer>> exchange(int note) {
//        List<Integer> arr1 = new ArrayList<>();
//        List<Integer> arr2 = new ArrayList<>();
//        List<Integer> arr3 = new ArrayList<>();
//        List<Integer> arr4 = new ArrayList<>();
//        List<Integer> arr5 = new ArrayList<>();
//        List<Integer> arr6 = new ArrayList<>();
//
//        arr1 = Collections.singletonList(10);
//        arr2 = asList(5, 5);
//        arr3 = asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
//        arr4 = Collections.singletonList(5);
//        arr5 = asList(1, 1, 1, 1, 1);
//        arr6 = Collections.singletonList(1);
//
//        List<List<Integer>> tempResult = asList(arr1, arr2, arr3, arr4, arr5, arr6);
//
//        for (int i = 0; i < tempResult.size(); i++) {
//            int sum = 0;
//            List<Integer> newList = new ArrayList<>(tempResult.get(i));
//            for (int j = i; j < tempResult.size(); j++) {
//                int sum1 = tempResult.get(i).get(0) * tempResult.get(i).size();
//                int sum2 = tempResult.get(j).get(0) * tempResult.get(j).size();
//                int sum3 = sum1 + sum2;
//                newList.addAll(tempResult.get(j));
//                for (int k = 0; k < newList.size(); k++) {
//                    sum = sum + newList.get(k);
//                }
//                if (sum3 == note) {
//                    System.out.println("newList: " + newList + "  sum: " + sum);
//                    newList.addAll(tempResult.get(j));
//                    result.add(newList);
//                }
//            }
//        }
//
//        return result;
//    }

//        for (int out = values.length - 1; out > 0; out--) {
//            for (int in = 0; in < out; in++) {
//                if (values[in] > values[out]) {
//                    swap(in, in+1);
//                }
//            }
//        }
//
//        List<Integer> arrR = new ArrayList<>();
//
//            arrR.add(values[count]);
//            rem = note - values[count];
//            if (rem == 0) {
//                result.add(arrR);
//            }
//            if (rem > values[count]) {
//                while (rem > 0) {
//                    rem = rem - values[count];
//                    arrR.add(values[count]);
//                }
//                result.add(arrR);
//                if (count < values.length - 1) {
//                    count++;
//                    exchange(note);
//                }
//            } else {
//                if (count < values.length - 1) {
//                    count++;
//                    exchange(note);
//                }
//            }
//
//        return result;
//    }
//
//    public void swap(int one, int two) {
//        int temp = values[one];
//        values[one] = values[two];
//        values[two] = temp;
//    }

    /**
     *
     * @param note - note.
     * @return - list.
     */
    public List<List<Integer>> exchange(int note) {
        List<Integer> coins = new ArrayList<>();
        for (Integer next : values) {
            coins.add(next);
        }
        List<List<Integer>> result = new ArrayList<>();

        /**
         *
         * @param <T> - param.
         */
        class Wrapper<T> {
            private T function;
        }
        Wrapper<Function<Integer, BiConsumer<Integer, List<Integer>>>> recursion = new Wrapper<>();
        recursion.function = money -> (numberOfCoin, buffer) -> {
            if (money < 0 || numberOfCoin < 0) {
                return;
            }

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

//итерационный способ.
//    public List<List<Integer>> exchange(int note) {
//        List<List<Integer>> twoArr = new ArrayList<>();
//        List<Integer> terms = new ArrayList<>();
//        for (int z = 0; z <= note / 10; z++) {
//            for (int y = 0; y <= note / 5; y++) {
//                for (int x = 0; x <= note; x++) {
//                    if ((x + 5 * y + 10 * z) == note) {
//                        terms.add(x);
//                        terms.add(y);
//                        terms.add(z);
//                    }
//                    if (terms.size() > 0) {
//                        twoArr.add(terms);
//                        terms = new ArrayList<>();
//                    }
//                }
//            }
//        }
//        System.out.println(twoArr);
//        return twoArr;
//    }

//    public static void main(String[] args) {
//        CashMachine cashMachine = new CashMachine(new int[] {10, 5, 1});
//        cashMachine.exchange(10);
//    }
}

