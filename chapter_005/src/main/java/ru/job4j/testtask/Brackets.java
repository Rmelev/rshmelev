package ru.job4j.testtask;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * class for brackets syntactic analyse.
 */
public class Brackets {
    /**
     * base set of brackets, which program consider. If your expression have other brackets,
     * it's necessary to add their in class constructor.
     */
    private Map<Character, Character> baseMap = new HashMap<>();

    /**
     * Constructor with base set of brackets.
     */
    public Brackets() {
        baseMap.put('[', ']');
        baseMap.put('(', ')');
        baseMap.put('{', '}');
    }

    /**
     * scan expression from console.
     * @return - String representation of expression.
     */
    String scanner() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, input expression: ");
        return scanner.nextLine();
    }

    /**
     * check expression for validity. True, if expression have close bracket for
     * open bracket for each type of brackets.
     * @param charArr - char representation of of user input.
     * @return - true, if valid.
     */
    boolean isExpressionValid(char[] charArr) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < charArr.length; i++) {
            boolean flag = false;
            if (charArr[i] == '[' || charArr[i] == ']' || charArr[i] == '('
                    || charArr[i] == ')' || charArr[i] == '{' || charArr[i] == '}') {
                if (i == 0) {
                    stack.addLast(charArr[i]);
                    continue;
                }
                for (Character val : baseMap.keySet()) {
                    if (charArr[i] == baseMap.get(val) && stack.size() != 0) {
                        stack.pollLast();
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    stack.addLast(charArr[i]);
                }
            }
        }
        return stack.size() == 0;
    }

    /**
     * If expression validity, print bracket pairs in order of appearance open bracket of pair.
     */
    void bracketsPositions() {
        char[] charArr = scanner().toCharArray();
        TreeSet<BracketsPair> bracketsPairs = new TreeSet<>();
        ArrayDeque<Character> stack = new ArrayDeque<>();
        ArrayDeque<Integer> stackPos = new ArrayDeque<>();
        if (isExpressionValid(charArr)) {
            for (int i = 0; i < charArr.length; i++) {
                boolean flag = false;
                if (charArr[i] == '[' || charArr[i] == ']' || charArr[i] == '('
                        || charArr[i] == ')' || charArr[i] == '{' || charArr[i] == '}') {
                    if (i == 0) {
                        stack.addLast(charArr[i]);
                        stackPos.addLast(i);
                        continue;
                    }
                    for (Character val : baseMap.keySet()) {
                        if (charArr[i] == baseMap.get(val) && stack.size() != 0) {
                            char[] ch = {val, baseMap.get(val)};
                            String pair = new String(ch);
                            bracketsPairs.add(new BracketsPair(pair, stackPos.peekLast(), i));
                            stackPos.pollLast();
                            stack.pollLast();
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        stack.addLast(charArr[i]);
                        stackPos.addLast(i);
                    }
                }
            }
            for (BracketsPair nextPair : bracketsPairs) {
                System.out.println(nextPair);
            }
        } else {
            System.out.println("Expression is not valid.");
        }
    }

    /**
     * Method main.
     * @param args - args.
     */
    public static void main(String[] args) {
        Brackets br = new Brackets();
        br.bracketsPositions();
    }
}
