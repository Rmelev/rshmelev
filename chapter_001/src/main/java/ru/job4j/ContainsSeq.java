package ru.job4j;

/**
 * class for looking for sub string.
 */
public class ContainsSeq {
    /**
     *
     * @param origin - origin string, there we're looking for sub string.
     * @param sub - what we are looking for.
     * @return - true if we have found.
     */
    boolean contains(String origin, String sub) {
        boolean flag = false;
        char[] chAr = origin.toCharArray();
        char[] chArSub = sub.toCharArray();
        for (int i = 0; i < chAr.length; i++) {
            if (chAr[i] == chArSub[0]) {
                for (int j = 1; j < chArSub.length; j++) {
                    if (chAr[i + j] != chArSub[j]) {
                        return flag;
                    }
                }
                flag = true;
            }
        }
        return flag;
    }
}