package ru.job4j.changes;

//тест на вакансию Java-стажер 6.04.

/**
 *
 */
public class TicTacToe {
    /**
     *
     */
    private final int[][] values;

    /**
     *
     * @param values - values.
     */
    public TicTacToe(final int[][] values) {
        this.values = values;
    }

    /**
     *
     * @return - booolean.
     */
    public boolean hasWinner() {
        boolean result = false;

        for (int i = 0; i < values.length; i++) {
            int counter = 0;
            int l = 0;
            while (l < values.length) {
                if (values[i][l] != 1) {
                    counter = 0;
                } else {
                    counter++;
                }
                if (counter == 3) {
                    result = true;
                    break;
                }
                l++;
            }
        }

        for (int i = 0; i < values.length; i++) {
            int counter = 0;
            int l = 0;
            while (l < values.length) {
                if (values[i][l] != 0) {
                    counter = 0;
                } else {
                    counter++;
                }
                if (counter == 3) {
                    result = true;
                    break;
                }
                l++;
            }
        }


        for (int i = 0; i < values.length; i++) {
            int counter = 0;
            int l = 0;
            while (l < values.length) {
                if (values[l][i] != 1) {
                    counter = 0;
                } else {
                    counter++;
                }
                if (counter == 3) {
                    result = true;
                    break;
                }
                l++;
            }
        }

        for (int i = 0; i < values.length; i++) {
            int counter = 0;
            int l = 0;
            while (l < values.length) {
                if (values[l][i] != 0) {
                    counter = 0;
                } else {
                    counter++;
                }
                if (counter == 3) {
                    result = true;
                    break;
                }
                l++;
            }
        }

        int d = 0;
        int counter1 = 0;
        while (d < values.length) {

            if (values[d][d] != 1) {
                counter1 = 0;
            } else {
                counter1++;
            }
            if (counter1 == 3) {
                result = true;
                break;
            }
            d++;
        }

        int d2 = 0;
        int counter2 = 0;
        while (d2 < values.length) {
            if (values[d2][d2] != 0) {
                counter2 = 0;
            } else {
                counter2++;
            }
            if (counter2 == 3) {
                result = true;
                break;
            }
            d2++;
        }

        int counter3 = 0;
        for (int i = 0; i < values.length; i++) {
                if (values[i][values.length - 1 - i] != 1) {
                    counter3 = 0;
                } else {
                    counter3++;
                }
                if (counter3 == 3) {
                    result = true;
                    break;
                }
        }

        int counter4 = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i][values.length - 1 - i] != 0) {
                counter4 = 0;
            } else {
                counter4++;
            }
            if (counter4 == 3) {
                result = true;
                break;
            }
        }
        return result;
    }
}
