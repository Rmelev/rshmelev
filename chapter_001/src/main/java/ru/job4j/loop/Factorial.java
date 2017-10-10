package ru.job4j.loop;

class Factorial {
    /**
     * Calculate factorial for n.
     * @param n - value for factorial calculate
     * @return - fanal value of n!.
     */
    public int calc(int n) {
        if (n==0) return 1;
        int mult = 1;
        for (int i = 1; i <= n; i++) {
            mult *= i;
        }
        return mult;
    }

    public int calcRec(int n) {
        if (n==0) {
            return 1;
        } else {
            int mult = n * calcRec(n - 1);
            return mult;
        }
    }
}