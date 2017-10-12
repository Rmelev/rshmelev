package ru.job4j;

public class ContainsSeq {
    boolean contains(String origin, String sub) {
        boolean flag = false;
        char[] chAr = origin.toCharArray();
        char[] chArSub = sub.toCharArray();
        for (int i = 0; i < chAr.length; i++) {
            if (chAr[i] == chArSub[0]) {
                for (int j = 1; j < chArSub.length; j++) {
                    if (chAr[i + j] == chArSub[j]);
                    else return flag;
                }
                flag = true;
            }
        }
        return flag;
    }
}